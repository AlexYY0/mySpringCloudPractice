package club.emperorws.mycloud.controller;

import club.emperorws.mycloud.entities.CommonResult;
import club.emperorws.mycloud.entities.Payment;
import club.emperorws.mycloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/15 16:37
 * @Description:
 **/
@RestController
public class PaymentController {
    public static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String serverPort;

    @Resource
    PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        logger.info("*****插入结果：{}",result);
        if(result>0){
            return new CommonResult(200,"sucess",result);
        }else {
            return new CommonResult(404,"error",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Integer id) {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null) {
            return new CommonResult(200,"查询成功",payment);
        }else{
            return new CommonResult(404,"没有对应记录,查询ID: "+id,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
