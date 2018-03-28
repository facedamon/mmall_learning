package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategory(Integer categoryId,String categoryName);
    ServerResponse<List<Category>> getChildernParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> getCategoryAndChildernById(Integer categoryId);
}
