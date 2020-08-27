package club.emperorws.mycloud.service;

import club.emperorws.mycloud.entities.CommonResult;
import club.emperorws.mycloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/19 18:34
 * @Description:
 **/
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public String getPayment(Integer id) {
        return "服务降级返回,---PaymentFallbackService";
    }
}
