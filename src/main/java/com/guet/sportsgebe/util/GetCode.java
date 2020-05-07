package com.guet.sportsgebe.util;

import java.util.UUID;

public class GetCode {
    public static String Get6Code(){
        String code  = UUID.randomUUID().toString().replace("-","").toUpperCase().substring(0,6);
        return code;
    }
}
