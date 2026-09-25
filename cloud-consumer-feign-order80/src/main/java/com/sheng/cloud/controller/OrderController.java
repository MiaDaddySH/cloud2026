package com.sheng.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.sheng.cloud.apis.PayFeignApi;
import com.sheng.cloud.entities.PayDTO;
import com.sheng.cloud.resp.ResultData;
import com.sheng.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer/feign/pay")
public class OrderController {
	@Resource
	private PayFeignApi payFeignApi;
	@PostMapping
	public ResultData<String> addOrder(@RequestBody PayDTO payDTO) {
		return payFeignApi.addPay(payDTO);
	}

	@DeleteMapping("/{id}")
	public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
		return payFeignApi.deletePay(id);
	}

	@PutMapping
	public ResultData<Integer> updatePay(@RequestBody PayDTO payDTO) {
		return payFeignApi.updatePay(payDTO);
	}


	@GetMapping("/{id}")
	public ResultData<PayDTO> getPay(@PathVariable("id") Integer id) {
		System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
		ResultData resultData = null;
		try
		{
			System.out.println("调用开始-----:" + DateUtil.now());
			resultData = payFeignApi.getPay(id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("调用结束-----:"+DateUtil.now());
			ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
		}
		return resultData;

//		return payFeignApi.getPay(id);
	}

	@GetMapping
	public ResultData<List<PayDTO>> getAll() {
		return payFeignApi.getAll();
	}

	@GetMapping("/info")
	private String getInfoByConsul() {
		return payFeignApi.getInfoByConsul();
	}
}
