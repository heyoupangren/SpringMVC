package com.cwh.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Cwh
 * CreateTime 2019/7/16 22:09
 */
@Controller
public class HelloWorld {


    /**
     *
     * 1.使用 @RequestMapping 注解来映射请求的 URL
     * 2.返回值会通过视图解析器解析为实际的物理视图，对于 InternalResourceViewResolver 视图解析器会做 如下的解析：
     * 通过 prefix + returnVal + suffix 这样的方式得到实际的物理视图，然后做转发操作
     * /WEB-INF/views/success.jsp
     *
     */
    @RequestMapping("/helloworld")
    public String hello(){
        System.out.println("HelloWrold!!!");
        return "success";
    }

}
