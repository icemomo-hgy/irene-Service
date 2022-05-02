package com.irene.Config;

import com.irene.Excetion.BusinessException;
import com.irene.common.Code;
import com.irene.util.JwtHelper;
import com.irene.util.VerifyToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.Config
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/5 15:37
 * @Description:
 * @since JDK 1.8
 */
@Component
public class ProjectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if(VerifyToken.verifyTokenId(token)){
            return true;
        }else {
            throw  new BusinessException(Code.GET_ERR,"token过期或不合法");
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
