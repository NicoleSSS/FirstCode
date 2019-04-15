package com.example.first.ui.nine;

/**
 * @ClassName: Msg
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/5 9:02
 * @Version: 1.0
 */
public class Msg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private int type;

    public Msg(String content,int type){
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
