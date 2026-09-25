package com.sheng.cloud.apis;

import com.sheng.cloud.entities.PayDTO;
import com.sheng.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "cloud-payment-service")
public interface PayFeignApi {
	@PostMapping("/pay")
	ResultData<String> addPay(@RequestBody PayDTO payDTO);

	@DeleteMapping("/pay/{id}")
	ResultData<Integer> deletePay(@PathVariable("id") Integer id);

	@PutMapping("/pay")
	ResultData<Integer> updatePay(@RequestBody PayDTO payDTO);

	@GetMapping("/pay")
	ResultData<List<PayDTO>> getAll();

	@GetMapping("/pay/{id}")
	ResultData<PayDTO> getPay(@PathVariable("id") Integer id);

	@GetMapping("/pay/info")
	String getInfoByConsul();
}
