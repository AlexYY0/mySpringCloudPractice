package club.emperorws.mycloud.mapper;

import club.emperorws.mycloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/15 16:37
 * @Description:
 **/
public interface PaymentMapper {
    int create(Payment payment);

    Payment getPaymentById(@Param("id")Integer id);
}
