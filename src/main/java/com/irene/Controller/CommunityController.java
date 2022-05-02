package com.irene.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.irene.Entity.*;
import com.irene.Mapper.LikeComMapper;
import com.irene.Service.*;
import com.irene.common.Code;
import com.irene.common.Result;
import com.irene.common.SnowFlake;
import com.irene.util.JwtHelper;
import com.irene.util.PageUtils;
import com.irene.util.QiniuUtils;
import com.irene.util.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Controller
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/7 13:22
 * @Description:
 * @since JDK 1.8
 */
@RestController
@Slf4j
public class CommunityController {
    @Autowired
    private CommunityService communityService;
    @Autowired
    private CommunityImgService communityImgService;
    @Autowired
    private QiniuUtils qiniuUtils;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeComService likeComService;
    @GetMapping("/getCommunity")
    public Result getCommunity(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                               @RequestParam(value = "size",required = false,defaultValue = "10") Integer size){
        List<Community> list = communityService.list();
        List<Community> collect = list.stream().map((item) -> {
            if (item.getImgIds() == null) {
                return item;
            }
            LambdaQueryWrapper<CommunityImg> wrapper = new LambdaQueryWrapper();
            wrapper.eq(CommunityImg::getIds, item.getImgIds());
            List<CommunityImg> list1 = communityImgService.list(wrapper);
            item.setImgUrl(list1);
            UserInfo byId = userInfoService.getById(item.getUid());
            item.setName(byId.getNickname());
            return item;
        }).collect(Collectors.toList());
        Page resPages = PageUtils.getPage(page, size, collect);


        return Result.success(Code.GET_OK,collect,"查询成功！");
    }

    //发帖子
    @SneakyThrows
    @PostMapping("/sendCommunity")
    public Result sendCommunity(Community community, String token,  MultipartFile[] files){
        community.setUid(new JwtHelper().getTokenId(token));
        Long ids = new SnowFlake(5L).next();

       List<CommunityImg> list = new ArrayList<>();
        for (MultipartFile file: files) {
            System.out.println(file.getOriginalFilename());
            String imgName1 = StringUtils.getImgName(file.getOriginalFilename());
            String upload = qiniuUtils.upload((FileInputStream) file.getInputStream(), imgName1);
            System.out.println(upload);
            CommunityImg communityImg = new CommunityImg();
            communityImg.setIds(ids);
            communityImg.setUrl(upload);
            list.add(communityImg);
            communityImgService.save(communityImg);
        }
        community.setImgUrl(list);
        community.setImgIds(ids);
        communityService.save(community);
        return Result.success(Code.SAVE_OK,"发布成功！");
}

    //点赞
    @PostMapping("/likePost")
    @Transactional(rollbackFor=Exception.class)
    public Result likePost(Integer id,String token){
        if(id==null){return  Result.error(Code.SAVE_ERR,"id不能为空！");}
        Long tokenId = new JwtHelper().getTokenId(token);
        LambdaQueryWrapper<LikeCom> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LikeCom::getUid,tokenId).eq(LikeCom::getComId,id);
        LikeCom likeCom = likeComService.getOne(wrapper);
        if(likeCom==null){
            Community community= communityService.getById(id);
            community.setLikes(community.getLikes()+1);
            communityService.updateById(community);
            LikeCom likeCom1 = new LikeCom();
            likeCom1.setComId(id);
            likeCom1.setUid(tokenId);
            likeComService.save(likeCom1);
            return Result.success(Code.SAVE_OK,"点赞成功！");
        }

        return Result.error(Code.SAVE_ERR,"你已经点过赞了！");
    }

    //评论
    @PostMapping("/comment")
    public Result comment(Integer id,String context,String token){
        if(id==null||context==null){return Result.error(Code.SAVE_ERR,"参数不能为空");}

        if (communityService.getById(id)==null){ return Result.error(Code.SAVE_ERR,"贴子不存在");}
        Long tokenId = new JwtHelper().getTokenId(token);
        Comment comment = new Comment();
        comment.setContext(context);
        comment.setCid(id);
        comment.setUserId(tokenId);
        commentService.save(comment);

        return Result.success(Code.SAVE_OK,"评论成功！");
    }

    //获取帖子所有评论
    @GetMapping("/getAllComment")
    public Result getAllComment(Integer id){
        Community tar = communityService.getById(id);
        if(tar==null){return Result.error(Code.GET_ERR,"没有该帖子！");}
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getCid,id);
        List<Comment> list = commentService.list(wrapper);
        System.out.println(list);
        List<Comment> collect = list.stream().map((item) -> {
            System.out.println((item.getUserId() + "sdasdasdasdasda"));
            UserInfo userInfo = userInfoService.getById(item.getUserId());
            item.setNickname(userInfo.getNickname());
            return item;
        }).collect(Collectors.toList());
        return Result.success(Code.GET_OK,collect,"查询成功！");
    }

    //删除帖子
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteCommunity")
    public Result deleteCommunity(Integer id,String token){
        Long tokenId = new JwtHelper().getTokenId(token);
        LambdaQueryWrapper<Community> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Community::getCommunityId,id).eq(Community::getUid,tokenId);
        Community one = communityService.getOne(wrapper);
        Long imgIds = one.getImgIds();
        LambdaQueryWrapper<CommunityImg> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(CommunityImg::getIds, imgIds);
        List<CommunityImg> list = communityImgService.list(wrapper1);
        boolean remove = communityService.remove(wrapper);
        LambdaQueryWrapper<Comment> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(Comment::getCid,id);
        if(remove){
            commentService.remove(wrapper2);
            for (CommunityImg communityImg:list) {
                String[] split = communityImg.getUrl().split("https://soe.icemomo.com/");
                qiniuUtils.delete(split[1]);
                communityImgService.removeById(communityImg.getComImgId());
            }
            commentService.removeById(id);

            return Result.success(Code.DELETE_OK,"删除成功！");}
        return  Result.error(Code.DELETE_ERR,"删除失败！");


    }

    //我的帖子
    @PostMapping("/myCommunity")
    public Result myCommunity(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                              @RequestParam(value = "size",required = false,defaultValue = "10") Integer size,String token){
        Long tokenId = new JwtHelper().getTokenId(token);
        LambdaQueryWrapper<Community> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Community::getUid,tokenId);
        List<Community> list = communityService.list(wrapper1);
        if (list==null){return  Result.success(Code.GET_OK,"你还没有帖子");}
        List<Community> collect = list.stream().map((item) -> {
            if (item.getImgIds() == null) {
                return item;
            }
            LambdaQueryWrapper<CommunityImg> wrapper = new LambdaQueryWrapper();
            wrapper.eq(CommunityImg::getIds, item.getImgIds());
            List<CommunityImg> list1 = communityImgService.list(wrapper);
            item.setImgUrl(list1);
            UserInfo byId = userInfoService.getById(item.getUid());
            item.setName(byId.getNickname());
            return item;
        }).collect(Collectors.toList());
        Page resPages = PageUtils.getPage(page, size, collect);
        return Result.success(Code.GET_OK,resPages,"查询成功");
    }


}