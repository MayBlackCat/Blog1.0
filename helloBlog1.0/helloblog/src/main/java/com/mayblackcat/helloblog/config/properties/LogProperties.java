package com.mayblackcat.helloblog.config.properties;

public class LogProperties {

    //请求URL
    private String url;
    //访问ip
    private String ip;
    //调用方法
    private String classMethod;
    //返回的参数
    private Object[] arg;

    public LogProperties(String url, String ip, String classMethod, Object[] arg) {
        this.url = url;
        this.ip = ip;
        this.classMethod = classMethod;
        this.arg = arg;
    }
}
