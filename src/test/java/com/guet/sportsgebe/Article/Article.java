package com.guet.sportsgebe.Article;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guet.sportsgebe.util.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Article {

    @Value("${web.upload-path}")
    private String path;

    @Test
    void getActDes(){
        String Description = File.getTXTFile(path + "Des/DA90DA-Des.txt");
        System.out.println(Description);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("actdescription",Description);

        System.out.println(JSON.toJSONString(jsonObject));
    }

    @Test
    void GetOneDes(){
        String ActId = "DA90DA";
        String Description = File.getTXTFile(path + "Des/" + ActId + "-Des.txt");
        String str = Description;
        int len = 1000;

        if (Description != null){
            byte[] bt = Description.getBytes();
            if(bt.length > len){
                if(bt[len - 1] < 0){
                    str = new String(bt, 0, len-1);
                }else {
                    str = new String(bt,0,len);
                }
            }
        }
        System.out.println(str);
    }
}
