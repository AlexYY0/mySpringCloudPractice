package club.emperorws.mycloud.controller;

import club.emperorws.mycloud.entities.CommonResult;
import club.emperorws.mycloud.entities.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/15 22:35
 * @Description:
 **/
@RestController
public class OrderController {
    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

//    public static final String PAYMENT_URL = "http://localhost:8001";

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(serverURL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Integer id){
        return restTemplate.getForObject(serverURL + "/payment/get/" + id, CommonResult.class);
    }
}
