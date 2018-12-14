package com.zq.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class zuulTokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //过滤器类型 枚举值： pre表示路由之前，
        // routing表示路由当中，
        // post表示路由之后，
        // error表示路由发生错误。
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 执行时序， 值是0,1,2....N等自然数。 0的优先级最高，即最先执行。
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行run 方法
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //判断请求是否包含token
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        System.out.println(String.format("%s %s", request.getMethod(), request.getRequestURL().toString()));

        String accessToken = request.getHeader("token");
        if(accessToken == null) {    //判断是否有token
            System.out.println("accessToken Empty");
            rc.setSendZuulResponse(false);
            rc.setResponseStatusCode(401);
            try {
                rc.getResponse().getWriter().write("accessToken is Empty");
            }catch (Exception e){}
        }
        System.out.println("token ==>" + accessToken);
        return "";
    }

}
