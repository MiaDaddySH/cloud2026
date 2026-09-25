package com.sheng.cloud.config.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * cloud-payment-service 专用的负载均衡配置。
 *
 * <p>该类由 {@code @LoadBalancerClient} 加载，不添加 {@code @Configuration}，
 * 避免被应用主上下文扫描成所有服务共享的全局配置。</p>
 */
public class PaymentLoadBalancerConfiguration {

	@Bean
	ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(
			Environment environment,
			LoadBalancerClientFactory loadBalancerClientFactory) {
		String serviceId = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		return new RandomLoadBalancer(
				loadBalancerClientFactory.getLazyProvider(
						serviceId, ServiceInstanceListSupplier.class),
				serviceId);
	}
}
