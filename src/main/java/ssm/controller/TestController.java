package ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  //注册为控制器bean
@RequestMapping(value = "/test")    //请求路径
public class TestController {
        @ResponseBody  //返回json数据
        @RequestMapping(value="/hello",produces="application/json;charset=UTF-8")
        public String hello(){
            return "Hello!你好呀，SSM";
        }
}

