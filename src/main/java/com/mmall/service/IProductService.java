package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse<String> updateProductStatus(Integer productId,Integer status);
    ServerResponse<ProductDetailVo> selectProductDetail(Integer productId);
    ServerResponse<PageInfo> selectProductListAll(Integer pageNum, Integer pageSize);
    ServerResponse<PageInfo> selectProductList(Integer productId,String productName,Integer pageNum,Integer pageSize);
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);
    ServerResponse<PageInfo> getProductByKeywordAndCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy);
}
