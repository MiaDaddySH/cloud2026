package com.sheng.cloud.controller;

import com.sheng.cloud.entities.Pay;
import com.sheng.cloud.entities.PayDTO;
import com.sheng.cloud.resp.ResultData;
import com.sheng.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/pay")
@Tag(name = "支付管理", description = "支付记录的新增、删除、修改和查询")
public class PayController {
	@Resource
	PayService payService;

	@PostMapping
	@Operation(summary = "新增支付记录", description = "新增一条支付记录。id、删除标志和时间字段由数据库自动生成")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "新增成功或新增失败"),
			@ApiResponse(responseCode = "400", description = "请求参数格式错误", content = @Content)
	})
	public ResultData<String> addPay(@RequestBody PayDTO payDTO){
		Pay pay = new Pay();
		BeanUtils.copyProperties(payDTO, pay);
		log.debug("pay: {}", pay);
		int result = payService.add(pay);
		return ResultData.success("支付记录新增成功, 返回值:" + result);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除支付记录", description = "根据支付记录主键 ID 删除记录，返回受影响的行数")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "删除请求执行成功"),
			@ApiResponse(responseCode = "400", description = "ID 格式错误", content = @Content)
	})
	public ResultData<Integer> deletePay(
			@Parameter(description = "支付记录 ID", required = true, example = "1")
			@PathVariable Integer id) {
		int result = payService.delete(id);
		return ResultData.success(result);
	}

	@PutMapping
	@Operation(summary = "修改支付记录", description = "根据请求体中的 ID 修改非空字段")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "修改成功或修改失败"),
			@ApiResponse(responseCode = "400", description = "请求参数格式错误", content = @Content)
	})
	public ResultData<Integer> updatePay(@RequestBody PayDTO payDTO) {
		Pay pay = new Pay();
		BeanUtils.copyProperties(payDTO, pay);
		int result = payService.update(pay);
		return ResultData.success(result);
	}

	@GetMapping("/{id}")
	@Operation(summary = "查询支付记录", description = "根据支付记录主键 ID 查询单条记录")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "查询成功",
					content = @Content(schema = @Schema(implementation = Pay.class))),
			@ApiResponse(responseCode = "400", description = "ID 格式错误", content = @Content)
	})
	public ResultData<Pay> getPay(
			@Parameter(description = "支付记录 ID", required = true, example = "1")
			@PathVariable Integer id) {
		try {
			TimeUnit.SECONDS.sleep(62);
		} catch (InterruptedException e) {
			log.error("InterruptedException: {}", e.getMessage());
		}
		return ResultData.success(payService.getById(id));
	}

	@GetMapping
	@Operation(summary = "查询全部支付记录", description = "查询支付表中的全部记录")
	@ApiResponse(responseCode = "200", description = "查询成功")
	public ResultData<List<Pay>> getAll() {
		return ResultData.success(payService.getAll());
	}

	@Value("${server.port}")
	private String port;
	@GetMapping("/info")
	public String getInfoByConsul(@Value("${sheng.info}") String info) {
		return "consul info: " + info + ", port: " + port;
	}
}
