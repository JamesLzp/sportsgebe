package com.guet.sportsgebe;

import com.alibaba.fastjson.JSON;
import com.guet.sportsgebe.entity.Image;
import com.guet.sportsgebe.entity.Users;
import com.guet.sportsgebe.service.ImageService;
import com.guet.sportsgebe.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SportsgebeApplicationTests {

    @Autowired
//    private UsersService usersService;
    private ImageService imageService;

    /**
     * 数据库取出数据，转为json格式
     */
    @Test
    void contextLoads() {
        List <Image> imageList = new ArrayList<Image>();
        imageList = imageService.queryAllByLimit(0,3);
        System.out.println(JSON.toJSONString(imageList));
    }

//    @Test
//    void selectOneUser(){
//        Users user = new Users();
//        user = usersService.queryById("8332B");
//        System.out.println(user.getUsername());
//    }

    @Test
    void saveFile() throws IOException {
        // 通过url获取文件
        // File f = new File("D:/20160603143324235166.pdf");
        File f = new File("C:/Users/lzp/Desktop/images/Test1pic.jpg");
        // 获取的文件路径
        // String url = "D:/20160603143324235166.pdf";
        String url = "C:/Users/lzp/Desktop/images/Test1pic.jpg";
        // 取得最后一个/的下标
        int index = url.lastIndexOf("/");
        // 返回字符串，截取最后方的文件的名称类型
        String newString = url.substring(index + 1);
        // 通过inputStream获取文件
        InputStream inputStream = new FileInputStream(f);
        // 定义一个文件名字进行接收获取文件
        FileOutputStream fileOut = new FileOutputStream(new File("C:\\Users\\lzp\\Desktop\\images\\Test\\" + newString));
        byte[] buf = new byte[1024 * 8];
        while (true) {
            int read = 0;
            if (inputStream != null) {
                read = inputStream.read(buf);
            }
            if (read == -1) {
                break;
            }
            fileOut.write(buf, 0, read);
        }
        // 查看文件获取是否成功
        if (fileOut.getFD().valid() == true) {
            System.out.println("获取文件保存成功");
        } else {
            System.out.println("获取文件失败");
        }
        fileOut.flush();
    }

    @Test
    void saveTXTFile(){
        String pathName = "C:\\Users\\lzp\\Desktop\\main\\Test\\test1.txt";
        String str = "\r\nbuadsa<br/>hdiashda<br/>daisdji\r\n";
        System.out.println(com.guet.sportsgebe.util.File.saveTXTFile(str,pathName));

    }

    @Test
    void getTXTFile(){
        String pathName = "C:\\Users\\lzp\\Desktop\\main\\Test\\test1.txt";
        System.out.println(com.guet.sportsgebe.util.File.getTXTFile(pathName));
    }

}
