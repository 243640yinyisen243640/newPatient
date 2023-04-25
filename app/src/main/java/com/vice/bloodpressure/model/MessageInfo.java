package com.vice.bloodpressure.model;

import java.io.Serializable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MessageInfo implements Serializable {
    private String content;
    private String title;
    private String time;


    private String num = "";
    /**
     *消息类型
     */
    private String msgType;
    /**
     * 消息数据
     */
    private String msgData;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public MessageInfo(String content, String title, String time) {
        this.content = content;
        this.title = title;
        this.time = time;
    }

    public MessageInfo(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgData() {
        return msgData;
    }

    public void setMsgData(String msgData) {
        this.msgData = msgData;
    }
}
