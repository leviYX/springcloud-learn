package com.lxy.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.Properties;

public class TestNacosApi {

    private static final String SERVICE_ADDR = "127.0.0.1:8848";
    private static final String GROUP_NAME = "DEFAULT_GROUP";


    public static void main(String[] args) throws Exception {

        // 创建NamingService实例,其实就是nacos的服务端信息
        Properties properties = new Properties();
        properties.put("serverAddr", SERVICE_ADDR);
        properties.put("groupName", GROUP_NAME);
        NamingService namingService = NamingFactory.createNamingService(properties);

        // 注册客户端。user服务
        Instance userInstance = new Instance();
        userInstance.setIp("127.0.0.1");
        userInstance.setPort(8080);

        namingService.registerInstance("user-service",userInstance);

        System.in.read();
    }
}
