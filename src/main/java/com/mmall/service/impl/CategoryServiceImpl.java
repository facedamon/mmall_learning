package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 品类service
 *
 * @author facedamon facedamon
 * @create 2018/3/18
 */
@Service()
public class CategoryServiceImpl implements ICategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse addCategory(String categoryName,Integer parentId){
        if(parentId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }
        //添加品类
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);

        int rowCount = categoryMapper.insert(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("添加品类成功");
        }

        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    @Override
    public ServerResponse updateCategory(Integer categoryId,String categoryName){
        if(categoryId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新品类成功");
        }

        return ServerResponse.createByErrorMessage("更新品类失败");
    }

    @Override
    public ServerResponse<List<Category>> getChildernParallelCategory(Integer categoryId){
        List<Category> categoryList = categoryMapper.selectCategoryChildernByParentId(categoryId);
        if(CollectionUtils.isEmpty(categoryList)){
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    @Override
    public ServerResponse<List<Integer>> getCategoryAndChildernById(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildernCategory(categoryId,categorySet);

        List<Integer> categoryIdList = Lists.newArrayList();
        for(Category categoryItem : categorySet){
            categoryIdList.add(categoryItem.getId());
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    private Set<Category> findChildernCategory(Integer categoryId,Set<Category> categorySet){
        //当前节点
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(null != category){
            categorySet.add(category);
        }
        //查找子节点，递归算法一定要有一个退出条件
        List<Category> categoryList = categoryMapper.selectCategoryChildernByParentId(categoryId);
        for(Category categoryItem : categoryList){
            findChildernCategory(categoryItem.getId(),categorySet);
        }
        return categorySet;
    }
}
