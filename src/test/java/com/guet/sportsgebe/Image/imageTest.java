package com.guet.sportsgebe.Image;

import com.guet.sportsgebe.entity.Image;
import com.guet.sportsgebe.service.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class imageTest {

    @Resource
    private ImageService imageService;

    @Test
    void getImgByName(){
        List<Image> list = new ArrayList<Image>();
        list = imageService.selectNameTest("0BFFF0");
        for (Image image : list){
            System.out.println(image.getImagename());
        }
    }

    @Test
    void StrTest(){
        String str = "老总的是-SADQ21";
        System.out.println(str.substring(str.length()-6, str.length()));
    }
}
