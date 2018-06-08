package com.javaee.learning.proxy;

public final class MyCar implements Icar {
    public void pause() {
        System.out.println("暂停");
    }

    @Override
    public String start(String speed, int second) {
        System.out.println("启动：" + speed + "秒" + speed + "km/h");
        return "启动成功";
    }

    @Override
    public void run() {
        System.out.println("奔跑吧");
    }

    @Override
    public void stop() {
        System.out.println("停止");
    }
}
