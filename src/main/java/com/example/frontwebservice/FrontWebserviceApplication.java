package com.example.frontwebservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringCloudApplication
@RestController
//@EnableCircuitBreaker
public class FrontWebserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FrontWebserviceApplication.class, args);
	}


	@HystrixCommand(fallbackMethod = "defaultMessage")
	@GetMapping(value="/hello")
	public String hello() {
		return "Salut 1 !";
	}
	public String defaultMessage() {
		return "Salut 2 !";
	}

	@RestController
	public class Microservice3 {
			@Autowired
			private LoadBalancerClient loadBalancer;

			@GetMapping(value = "/test")
			public void method() {
				ServiceInstance serviceInstance = loadBalancer.choose("webservice");
				System.out.println(serviceInstance.getUri());
			}
		}


	}
