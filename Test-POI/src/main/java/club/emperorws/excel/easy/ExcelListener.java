package club.emperorws.excel.easy;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/27 9:36
 * @Description:
 **/
public class ExcelListener extends AnalysisEventListener<Map<Integer, String>> {
    public static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    List<String> testDataName = new ArrayList<>();
    private Boolean flag = false;

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext analysisContext) {
        logger.info("解析到一条数据:{}", JSON.toJSONString(data));
        if(data.get(1)!=null&&("试验信息".equals(data.get(1))||"试验承担方".equals(data.get(1))||"试验件信息".equals(data.get(1))||"加载条件".equals(data.get(1))||"数据测量".equals(data.get(1))||"上传附件".equals(data.get(1))||"试验参数记录".equals(data.get(1)))){
            return;
        }else if("试验公式".equals(data.get(1))){
            flag = false;
            return;
        }
        if(data.get(1)!=null&&!"试样编号/序号".equals(data.get(1))&& !flag) {
            for (Map.Entry<Integer, String> entry : data.entrySet()) {
                if (entry.getKey() != 0 && entry.getKey() % 2 != 0) {
                    Map<String, String> test = new HashMap<>();
                    test.put(entry.getValue(), data.get(entry.getKey() + 1));
                    list.add(test);
                }
            }
        }else {
            flag = true;
            if (data.get(1) != null && "试样编号/序号".equals(data.get(1))) {
                testDataName.clear();
                for (Map.Entry<Integer, String> entry : data.entrySet()) {
                    if(entry.getKey()!=0){
                        testDataName.add(entry.getValue());
                    }
                }
                logger.info("数据头是{}",testDataName.toString());
                return;
            }
            for (Map.Entry<Integer, String> entry : data.entrySet()) {
                if(entry.getKey()!=0){
                    Map<String, String> test = new HashMap<>();
                    Integer k = entry.getKey();
                    String v = entry.getValue();
                    test.put(testDataName.get(k-1),v);
                    list.add(test);
                }
            }
        }
        if(list.size()>=5){
            saveStringValues();
            list.clear();
        }
    }

    private void saveStringValues(){
        logger.info("最终的数据时{}",list.toString());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveStringValues();
        logger.info("所有数据解析完成");
    }
}
