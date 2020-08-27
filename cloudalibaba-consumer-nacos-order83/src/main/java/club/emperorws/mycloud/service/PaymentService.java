package club.emperorws.mycloud.service;

import club.emperorws.mycloud.entities.CommonResult;
import club.emperorws.mycloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/19 18:30
 * @Description:
 **/
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping(value = "/payment/nacos/{id}")
    String getPayment(@PathVariable("id") Integer id);
}
