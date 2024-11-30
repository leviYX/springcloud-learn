package com.lxy.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.Properties;

public class TestNacosApi {

    private static final String SERVICE_ADDR = "127.0.0.1:8848";
    private static final String GROUP_NAME = "DEFAULT_GROUP";


    public static void main(String[] args) throws Exception {

        String userServiceName = "user-service";

       // 注册服务
        serverRegister(userServiceName);

        // 发现服务
        List<Instance> instances = serverDiscover(userServiceName);
        for (Instance instance : instances) {
            System.out.println(instance.toString());
        }


        System.in.read();
    }

    // 注册服务
    private static void serverRegister(String instanceName) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", SERVICE_ADDR);
        properties.put("groupName", GROUP_NAME);
        NamingService namingService = NamingFactory.createNamingService(properties);

        // 注册客户端。user服务
        Instance userInstance = new Instance();
        userInstance.setIp("127.0.0.1");
        userInstance.setPort(8080);

        namingService.registerInstance(instanceName,userInstance);
    }

    // 发现服务
    private static List<Instance> serverDiscover(String instanceName) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", SERVICE_ADDR);
        properties.put("groupName", GROUP_NAME);
        NamingService namingService = NamingFactory.createNamingService(properties);

        // 发现实例服务所有的实例，因为可能是分布式集群，所以返回的是一个列表
        return namingService.getAllInstances(instanceName);
    }


}
