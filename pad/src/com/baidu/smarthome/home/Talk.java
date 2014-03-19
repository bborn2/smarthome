package com.baidu.smarthome.home;

public class Talk {
    String content = "";
    String from = "";
    boolean isLeft = false;
    
    public Talk(String fr, String ct) {
        content = ct;
        from = fr;
    }
}
