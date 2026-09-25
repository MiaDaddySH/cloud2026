package com.sheng.cloud;

import com.sheng.cloud.apis.PayFeignApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {PayFeignApi.class})
@EnableDiscoveryClient
public class MainOpenFeign80
{
	public static void main(String[] args)
	{
		SpringApplication.run(MainOpenFeign80.class,args);
	}
}
