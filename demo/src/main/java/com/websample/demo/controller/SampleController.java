package com.websample.demo.controller;

import com.websample.demo.dto.ErrorResponse;
import com.websample.demo.exception.ErrorCode;
import com.websample.demo.exception.WebSampleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SampleController {

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException{
        log.info("Get some order" + id);

        if("500".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_BIG_ID_ERROR,
                    "500 is too big orderId.");
        }

        if("3".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_SMALL_ID_ERROR,
                    "3 is too small orderId.");
        }

        return "orderId:" + id + "," + "orderAmount:1000";
    }

    @GetMapping("/order")
    public String getOrderWithRequestParam(
            @RequestParam("orderId") String id,
            @RequestParam("orderAmount") Integer amount) {
        log.info("Get some order" + id + ", amount :" + amount);
        return "orderId:" + id + "," + "orderAmount: " + amount;
    }

    @PostMapping("/order")
    public String createOrder() {
        log.info("Create order");
        return "order created -> orderId:1, orderAmount:1000";
    }
}
