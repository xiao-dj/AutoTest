package com.muke.server;

import com.muke.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post请求")
@RequestMapping("/v1")
public class MyPostMethod {
    private static Cookie cookie;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "接口登陆成功后，成功获取cookies信息")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "password",required = true) String password){
        if(userName.equals("zhangsan")&&password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);

            return "恭喜你登陆成功了！";
        }

        return "用户名或密码错误！";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u){

        User user;
        //获取cookies
        Cookie[] cookies = request.getCookies();

        //验证cookies
        for(Cookie c : cookies){
            if(c.getName().equals("login")
                    && c.getValue().equals("true")
                    && u.getUserName().equals("zhangsan")
                    && u.getPassword().equals("123456")
                    ){

                user = new User();
                user.setUserName("lisi");
                user.setAge("18");
                user.setSex("man");

                return user.toString();

            }
        }
        return "参数不合法";
    }
}
