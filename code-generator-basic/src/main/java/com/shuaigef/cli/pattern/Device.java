package com.shuaigef.cli.pattern;

/**
 * 电视机类
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/30 12:24
 */
public class Device {

    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + "设备打开");
    }

    public void turnOff() {
        System.out.println(name + "设备关闭");
    }


}
