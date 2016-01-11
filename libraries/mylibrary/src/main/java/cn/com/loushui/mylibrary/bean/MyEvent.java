package cn.com.loushui.mylibrary.bean;

/**
 * EventBus 消息实体
 */
public class MyEvent {
    private int id;
    private String msg;
    private Object obj;


    public MyEvent() {
    }

    public MyEvent(int id, String msg, Object obj) {
        this.id = id;
        this.msg = msg;
        this.obj = obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
