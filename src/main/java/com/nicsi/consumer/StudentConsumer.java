package com.nicsi.consumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

public class StudentConsumer {

	@Autowired
	private DiscoveryClient client;
	@GetMapping("/consume")
	public String consumeData() 
	{
		RestTemplate rt = new RestTemplate();
		List<ServiceInstance>list=client.getInstances("STUDENT-PROVIDER");
		ResponseEntity<String>resp =rt.getForEntity(list.get(0).getUri()+"/provider/show", String.class);
		return"FROM CONSUMER=>" +resp.getBody();
	}
} 

