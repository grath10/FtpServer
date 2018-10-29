package com.zqtech;

import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws FtpException {
        FtpServerFactory serverFactory = new FtpServerFactory();

        ListenerFactory factory = new ListenerFactory();
        // 设置监听端口
        factory.setPort(2121);
        // 替换默认监听
        serverFactory.addListener("default", factory.createListener());

        BaseUser user = new BaseUser();
        user.setName("test");
        user.setPassword("123456");
        // 用户主目录
        user.setHomeDirectory("E:/Ftp");

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);

        // 增加用户
        serverFactory.getUserManager().save(user);

        org.apache.ftpserver.FtpServer server = serverFactory.createServer();
        server.start();
    }
}