package club.emperorws.mycloud.service;

import club.emperorws.mycloud.entities.Payment;
import club.emperorws.mycloud.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/15 16:59
 * @Description:
 **/
@Service
public class PaymentService {
    @Resource
    PaymentMapper paymentMapper;

    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    public Payment getPaymentById(Integer id) {
        return paymentMapper.getPaymentById(id);
    }
}
