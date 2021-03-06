package com.xk.springcloud;

import com.xk.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("********** result:" +result);
        return result ;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String PaymentInfo_TimeOut(@PathVariable Integer id){
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("********** result:" +result);
        return  result;
    }

    @GetMapping(value = "/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("********** result:" +result);
        return  result;
    }
}
