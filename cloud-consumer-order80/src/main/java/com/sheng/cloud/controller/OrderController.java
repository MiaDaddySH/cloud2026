package com.sheng.cloud.controller;

import com.sheng.cloud.entities.PayDTO;
import com.sheng.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer/pay")
public class OrderController {
	@Value("${payment.service.url:http://localhost:8001}")
	private String paymentServiceUrl;

	@Resource
	private RestTemplate restTemplate;

	@PostMapping
	public ResultData addOrder(@RequestBody PayDTO payDTO) {
		return restTemplate.postForObject(paymentServiceUrl + "/pay", payDTO, ResultData.class);
	}

	@GetMapping("/{id}")
	public ResultData getPay(@PathVariable("id") Long id) {
		return restTemplate.getForObject(paymentServiceUrl + "/pay/{id}", ResultData.class, id);
	}

	@DeleteMapping("/{id}")
	public ResultData deletePay(@PathVariable("id") Long id) {
		ResponseEntity<ResultData> response = restTemplate.exchange(
				paymentServiceUrl + "/pay/{id}",
				HttpMethod.DELETE,
				HttpEntity.EMPTY,
				ResultData.class,
				id
		);

		return response.getBody();
	}

	@PutMapping
	public ResultData updatePay(@RequestBody PayDTO payDTO) {
		HttpEntity<PayDTO> requestEntity = new HttpEntity<>(payDTO);

		ResponseEntity<ResultData> response = restTemplate.exchange(
				paymentServiceUrl + "/pay",
				HttpMethod.PUT,
				requestEntity,
				ResultData.class
		);

		return response.getBody();
	}
}
