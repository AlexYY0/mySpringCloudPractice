package club.emperorws.mycloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/15 16:14
 * @Description:
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "club.emperorws.mycloud.mapper")
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
