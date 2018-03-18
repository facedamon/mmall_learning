package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 产品控制器
 *
 * @author facedamon facedamon
 * @create 2018/3/18
 */
@Controller
@RequestMapping("/manage/product")
public class PeoductManageController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;


    @RequestMapping(value = "saveOrUpdateProduct.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse saveOrUpdateProduct(HttpSession session,Product product){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(null == user){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }

        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.saveOrUpdateProduct(product);
        }else{
            return ServerResponse.createByErrorMessage("无操作权限，需要管理员权限");
        }
    }

    @RequestMapping(value = "updateProductStatus.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateProductStatus(HttpSession session,Integer productId,Integer status){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(null == user){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }

        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.updateProductStatus(productId,status);
        }else{
            return ServerResponse.createByErrorMessage("无操作权限，需要管理员权限");
        }
    }

    @RequestMapping(value = "getProductDetail.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getProductDetail(HttpSession session,Integer productId,Integer status){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(null == user){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }

        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.selectProductDetail(productId);
        }else{
            return ServerResponse.createByErrorMessage("无操作权限，需要管理员权限");
        }
    }

    @RequestMapping(value = "getProductListAll.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getProductListAll(HttpSession session,
                                            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(null == user){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }

        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.selectProductListAll(pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无操作权限，需要管理员权限");
        }
    }

    @RequestMapping(value = "serachProduct.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse serachProduct(HttpSession session,Integer productId,String productName,
                                            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(null == user){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }

        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.selectProductList(productId,productName,pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无操作权限，需要管理员权限");
        }
    }
}
