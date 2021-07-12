package com.xk.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xk.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_Fallback")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentFeignService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
//    @HystrixCommand//采用全局fallback
    public String getPayment(@PathVariable("id") Integer id) {
        String result = paymentFeignService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod"/*指定善后方法名*/, commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand//采用全局fallback
    public String getPaymentFeignTimeout(@PathVariable("id") Integer id) {
        String result = paymentFeignService.paymentInfo_Timeout(id);
        return result;
    }

    //善后方法
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    public String payment_Global_Fallback(){
        return  "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
