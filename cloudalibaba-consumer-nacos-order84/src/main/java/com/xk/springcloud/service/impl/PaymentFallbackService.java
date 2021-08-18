package com.xk.springcloud.service.impl;

import com.xk.springcloud.entities.CommonResult;
import com.xk.springcloud.entities.Payment;
import com.xk.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回，.....paymentfallbackservice",new Payment(id,"errorSerial"));
    }
}
