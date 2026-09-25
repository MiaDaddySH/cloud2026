package com.sheng.cloud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：t_pay
 * 表注释：支付交易表
*/
@Data
@Table(name = "t_pay")
@Schema(name = "Pay", description = "支付交易记录")
public class Pay {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Schema(description = "支付记录 ID，由数据库自动生成", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    /**
     * 支付流水号
     */
    @Column(name = "pay_no")
    @Schema(description = "支付流水号", example = "pay202609240001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String payNo;

    /**
     * 订单流水号
     */
    @Column(name = "order_no")
    @Schema(description = "订单流水号", example = "order202609240001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderNo;

    /**
     * 用户账号ID
     */
    @Column(name = "user_id")
    @Schema(description = "用户账号 ID；不传时使用数据库默认值 1", example = "1")
    private Integer userId;

    /**
     * 交易金额
     */
    @Schema(description = "交易金额；不传时使用数据库默认值 9.90", example = "9.99")
    private BigDecimal amount;

    /**
     * 删除标志，默认0不删除，1删除
     */
    @Schema(description = "删除标志：0 未删除，1 已删除", type = "integer", format = "int32",
            example = "0", allowableValues = {"0", "1"}, accessMode = Schema.AccessMode.READ_ONLY)
    private Byte deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间，由数据库自动生成", example = "2026-09-24T11:30:12", accessMode = Schema.AccessMode.READ_ONLY)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间，由数据库自动维护", example = "2026-09-24T11:30:12", accessMode = Schema.AccessMode.READ_ONLY)
    private Date updateTime;
}
