package com.irene.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Slf4j
public class Community {
   @TableId(type = IdType.AUTO)
    private Integer communityId;
    @TableField(value = "publish_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private LocalDateTime publishTime;
    private Integer likes;
    @TableField(value = "comment_sum")
    private Integer commentSum;
    @TableField(value = "img_ids")
    private  Long imgIds;
    private String title;
    private String context;
    private Long uid;
    @TableField(exist = false)
    private List<CommunityImg> imgUrl;
    @TableField(exist = false)
    private String name;
}
