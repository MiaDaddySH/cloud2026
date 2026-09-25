package com.sheng.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

//一般而言，调用者不应该获悉服务提供者的entity资源并指导表结构关系，所以服务提供方给出的接口文档都应该称为DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO implements Serializable
{
	private Integer id;

	private String payNo;

	private String orderNo;

	private Integer userId;

	private BigDecimal amount;
}
