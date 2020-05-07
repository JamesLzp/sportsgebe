package com.guet.sportsgebe.util;

import java.io.*;

public class File {

    /**
     * 保存流文件
     * @param inputStream       文件流
     * @param newFilePathName   新文件路径
     * @return
     * @throws IOException
     */
    public static String saveStreamFile(InputStream inputStream, String newFilePathName) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(new java.io.File(newFilePathName));
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
        String resultFlag;
        if (fileOut.getFD().valid() == true) {
            resultFlag = "success";
        } else {
            resultFlag = "failed";
        }
        fileOut.flush();
        return resultFlag;
    }

    /**
     * 保存TXT文件，在文件的最后加入内容
     * @param str
     * @param pathName
     * @return
     */
    public static String saveTXTFile(String str, String pathName){
        java.io.File writename = new java.io.File(pathName); // 相对路径，如果没有则要建立一个新的output。txt文件
        try {
            writename.createNewFile(); // 创建新文件
            //append不为true，则不在原来的基础上插入文章
            //BufferedWriter out = new BufferedWriter(new FileWriter(writename,true));
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(str); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static String getTXTFile(String pathName){
        String AllLine = "";
        try (FileReader reader = new FileReader(pathName);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                AllLine+=line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AllLine;
    }

}
