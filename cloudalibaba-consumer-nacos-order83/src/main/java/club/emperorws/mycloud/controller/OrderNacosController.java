package club.emperorws.mycloud.controller;

import club.emperorws.mycloud.entities.CommonResult;
import club.emperorws.mycloud.entities.Payment;
import club.emperorws.mycloud.service.PaymentService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/16 10:53
 * @Description:
 **/
@RestController
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    @SentinelResource(value = "fallback")
    public String paymentInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(serverURL + "/payment/nacos/" + id, String.class);
    }

    //==================OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/payment/feign/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return paymentService.getPayment(id);
    }
}
