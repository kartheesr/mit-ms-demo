package com.mitosis.msdemo.discovery.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
/*@RestController
@RequestMapping("/checkIns")*/
public class DiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServiceApplication.class, args);
	}
	
	/*@GetMapping("/id")
	public String instance() {
		PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
	    Applications applications = registry.getApplications();

	    applications.getRegisteredApplications().forEach((registeredApplication) -> {
	        registeredApplication.getInstances().forEach((instance) -> {
	            System.out.println(instance.getAppName() + " (" + instance.getInstanceId() + ") : " 	);
	        });
	    });
		return null;
	}*/
}
