package com.mmall.controller.portal;


import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 *@author facedamon
 *@description 用户控制器
 *@param
 *@create 2018/3/6
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     *@author facedamon
     *@description 用户登陆
     *@param username
     *@param password
     *@param session 用来存储用户登陆信息
     *@create 2018/3/6
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username,password);
        if(response != null && response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }

        return response;
    }

    /**
     *@author facedamon
     *@description 用户登出
     *@param session
     *@create 2018/3/6
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     *@author facedamon
     *@description 用户注册
     *@param user 用户提交的form表单映射类
     *@create 2018/3/6
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user){
       return iUserService.register(user);
    }

    /**
     *@author facedamon
     *@description 校验用户 防止前台恶意使用接口登陆
     *@param str 参数名
     *@param type  参数类型
     *@create 2018/3/6
     */
    @RequestMapping(value = "checkValid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str,String type){
        return iUserService.checkValid(str,type);
    }

    /**
     *@author facedamon
     *@description 获取用户登陆信息
     *@param session 用户session
     *@create 2018/3/6
     */
    @RequestMapping(value = "getUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(null != user){
            return ServerResponse.createBySuccess(user);
        }

        return ServerResponse.createByErrorMessage("无法获取用户登陆信息");
    }

    /**
     *@author facedamon
     *@description 忘记密码，获取密码问题
     *@param username
     *@create 2018/3/6
     */
    @RequestMapping(value = "forgetGetQuestion.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }

    /**
     *@author facedamon
     *@description 校验问题答案
     *@param username
     *@param question
     *@param answer
     *@create 2018/3/6
     */
    @RequestMapping(value = "checkAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username,question,answer);
    }

    /**
     *@author facedamon
     *@description 忘记密码，重置密码
     *@param username
     *@param passwordNew
     *@param token
     *@create 2018/3/7
     */
    @RequestMapping(value = "forgetResetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username,String passwordNew,String token){
        return iUserService.forgetResetPassword(username,passwordNew,token);
    }

    /**
     *@author facedamon
     *@description 登陆状态重置密码
     *@param session
     *@param passwordOld
     *@param passwordNew
     *@create 2018/3/8
     */
    @RequestMapping(value = "resetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(null == user){
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }

    /**
     *@author facedamon
     *@description 登陆状态更新用户
     *@param session
     *@param user
     *@create 2018/3/8
     */
    @RequestMapping(value = "updateInformation.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(HttpSession session,User user){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if(null == currentUser){
            return ServerResponse.createByErrorMessage("用户未登陆");
        }

        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());

        ServerResponse<User> response = iUserService.updateInformation(user);
        if(null != response && response.isSuccess()){
            response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "getInformation.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInformation(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(null == user){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登陆、强制登陆");
        }
        return iUserService.getInformation(user.getId());
    }

}
