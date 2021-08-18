package com.xk.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xk.springcloud.entities.CommonResult;
import com.xk.springcloud.entities.Payment;
import com.xk.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {


    private static final String SERVICE_URL="http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")//没有配置
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")//只配置了fallback
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler")//只配置了blockHandler
    @SentinelResource(value = "fallback",
            blockHandler = "blockHandler",
            fallback = "handlerFallback",
    exceptionsToIgnore = {IllegalArgumentException.class})//同时blockHandler和fallback
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/payment/" + id, CommonResult.class, id);
        if (id==4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法的参数异常");
        }else if (result.getData()==null){
            throw new NullPointerException("NullPointerException,该id没有对应的记录，空指针异常");
        }
        return result;
    }

    public CommonResult handlerFallback(@PathVariable("id") Long id,Throwable e){
        Payment payment = new Payment(id, null);
        return new CommonResult(444,"兜底异常内容handlerFallback,Exception内容"+e.getMessage(),payment);
    }

    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException exception){
        Payment payment = new Payment(id, null);
        return new CommonResult(445,"blockHandler-sentinel限流,无此流水:"+exception.getMessage(),payment);
    }

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymenySQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
