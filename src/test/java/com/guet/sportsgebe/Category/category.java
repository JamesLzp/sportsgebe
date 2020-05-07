package com.guet.sportsgebe.Category;

import com.alibaba.fastjson.JSON;
import com.guet.sportsgebe.entity.Category;
import com.guet.sportsgebe.service.CategoryService;
import com.guet.sportsgebe.util.GetCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class category {

    @Resource
    private CategoryService categoryService;

    @Test
    void saveCate(){
        String Categories = "运动/极限/跑步/活动/户外";
        String[] cateArray = Categories.split("/");

        //类别名存到 list 中
        List list = new ArrayList();
        for(int i = 0; i < cateArray.length; i++){
            list.add(cateArray[i]);
        }
        //类别名存在于数据库的就取出来
        List<Category> tempList = new ArrayList<Category>();
        tempList = categoryService.queryByInNames(list);

        for(Category temp : tempList){
            if(list.contains(temp.getCatename())){
                list.remove(temp.getCatename());
            }
        }
    }
}
