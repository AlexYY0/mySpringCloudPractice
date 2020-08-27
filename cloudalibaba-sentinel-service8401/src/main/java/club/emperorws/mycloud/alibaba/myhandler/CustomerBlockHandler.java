package club.emperorws.mycloud.alibaba.myhandler;

import club.emperorws.mycloud.entities.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/19 17:22
 * @Description:
 **/
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----1");
    }
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----2");
    }
}
