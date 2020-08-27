package club.emperorws.mycloud.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/16 14:45
 * @Description:
 **/
@Component
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    public static final Logger logger = LoggerFactory.getLogger(MyLogGateWayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("***********come in MyLogGateWayFilter:  "+new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(uname==null){
            logger.info("*******用户名为null，非法用户，o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
