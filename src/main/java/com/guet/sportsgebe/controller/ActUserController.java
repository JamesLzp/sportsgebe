package com.guet.sportsgebe.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.sportsgebe.entity.ActUser;
import com.guet.sportsgebe.entity.Activity;
import com.guet.sportsgebe.entity.Users;
import com.guet.sportsgebe.service.ActUserService;
import com.guet.sportsgebe.service.ActivityService;
import com.guet.sportsgebe.service.UsersService;
import com.guet.sportsgebe.util.CreateExcel;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * (ActUser)表控制层
 *
 * @author makejava
 * @since 2020-05-01 17:16:54
 */
@Controller
@RequestMapping("/ActUser")
public class ActUserController {
    /**
     * 服务对象
     */
    @Resource
    private ActUserService actUserService;
    @Resource
    private UsersService usersService;
    @Resource
    private ActivityService activityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ActUser selectOne(String id) {
        return this.actUserService.queryById(id);
    }



    /**
     * 【方法功能描述】活动成员名单导出
     *
     * @author huanglei
     * @date 2018-09-06
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, HttpServletRequest request){
        String ActId = (String) request.getSession().getAttribute("ActId");
        Activity activity = activityService.queryById(ActId);
        String fileName = activity.getActname() + "-成员名单.xls";

        ActUser actUser = new ActUser();
        actUser.setActid(ActId);
        List<ActUser> actUsers = new ArrayList<ActUser>();
        actUsers = actUserService.queryAll(actUser);

        List<ActUser> actUserList = new ArrayList<ActUser>();
        for (ActUser temp: actUsers){
            Users user = usersService.queryById(temp.getUserid());
            temp.setActid(activity.getActname());
            temp.setUserid(user.getUserrealname());
            actUserList.add(temp);
        }

        List<Map<String, Object>> list = CreateExcel.createExcelRecord(actUserList);
        String columnNames[] = { "用户名", "活动名", "报名时间", "支付金额", "参加状态", "是否领队"};// 列名
        String keys[] = { "id", "actId", "enlistTime", "amountPaid", "attendState", "isLeader"};// map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();// 可以捕获内存缓冲区的数据，转换成字节数组。
        try {
            Workbook wb = CreateExcel.createWorkBook(list, keys, columnNames);
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);// 可以将字节数组转化为输入流
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        ServletOutputStream out = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(java.net.URLEncoder.encode((fileName), "UTF-8")));
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
                if (out != null)
                    out.close();
            }
             catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}