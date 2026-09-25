# cloud2026

一个基于 Java 17、Spring Boot 3.2.4 和 Maven 的多模块 Spring Cloud 学习项目，演示订单服务通过 Consul 和 Spring Cloud LoadBalancer 调用支付服务，并使用 MyBatis 访问 MySQL。

## 模块

| 模块 | 说明 | 默认端口 |
| --- | --- | --- |
| `cloud-api-commons` | 公共 DTO、响应对象和异常处理 | - |
| `cloud-provider-payment8001` | 支付服务，提供支付记录 CRUD API | 8001 |
| `cloud-consumer-order80` | 订单服务，转发请求到支付服务 | 80 |
| `mybatis_generator2026` | MyBatis Generator 配置 | - |

## 环境要求

- JDK 17 或更高版本
- Maven 3.9+
- MySQL 8.x
- Consul

## 本地运行

1. 初始化数据库：

   ```bash
   mysql -u root -p < database/init.sql
   ```

2. 如有需要，通过环境变量覆盖数据库连接。未设置时使用本地测试默认值：

   ```bash
   export DB_URL='jdbc:mysql://localhost:3306/db2024?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true'
   export DB_USERNAME='root'
   export DB_PASSWORD='123456'
   ```

3. 构建项目：

   ```bash
   mvn clean verify
   ```

4. 启动 Consul，然后启动第一个支付服务实例：

   ```bash
   java -jar cloud-provider-payment8001/target/cloud-provider-payment8001-1.0-SNAPSHOT.jar
   ```

5. 如需验证负载均衡，在另一个终端使用同一个 JAR 启动 8002 实例，无需复制模块：

   ```bash
   java -jar cloud-provider-payment8001/target/cloud-provider-payment8001-1.0-SNAPSHOT.jar --server.port=8002
   ```

   两个实例都会以 `cloud-payment-service` 注册到 Consul，并分别使用
   `cloud-payment-service-8001` 和 `cloud-payment-service-8002` 作为实例 ID。

6. 启动订单服务。如果端口 80 不可用，可以指定其他端口：

   ```bash
   java -jar cloud-consumer-order80/target/cloud-consumer-order80-1.0-SNAPSHOT.jar --server.port=8080
   ```

## 接口

- 支付服务 Swagger UI：<http://localhost:8001/swagger-ui/index.html>
- 查询支付记录：`GET http://localhost:8001/pay/{id}`
- 查询全部支付记录：`GET http://localhost:8001/pay`
- 新增支付记录：`POST http://localhost:8001/pay`
- 修改支付记录：`PUT http://localhost:8001/pay`
- 删除支付记录：`DELETE http://localhost:8001/pay/{id}`
- 订单服务代理接口：`http://localhost/consumer/pay`

新增支付记录示例：

```json
{
  "payNo": "pay202609250001",
  "orderNo": "order202609250001",
  "userId": 1,
  "amount": 9.99
}
```
