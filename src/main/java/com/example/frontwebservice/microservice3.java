package com.example.frontwebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class microservice3 {
    @Autowired
    private LoadBalancerClient loadBalancer;

    @GetMapping(value = "/load")
    public void method() {
        ServiceInstance serviceInstance = loadBalancer.choose("webservice");
        System.out.println(serviceInstance.getUri());
    }
}
