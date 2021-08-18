package com.xk.springcloud.service;

import com.xk.springcloud.entities.CommonResult;
import com.xk.springcloud.entities.Payment;
import com.xk.springcloud.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
