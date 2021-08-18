package com.xk.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xk.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return  new CommonResult(4444,"我自己定义的异常，global Excption......1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return  new CommonResult(4444,"我自己定义的异常，global Excption......2");
    }
}

