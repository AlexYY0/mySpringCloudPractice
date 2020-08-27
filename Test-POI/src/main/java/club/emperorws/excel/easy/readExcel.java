package club.emperorws.excel.easy;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/20 9:46
 * @Description:
 **/
public class readExcel {
    public static final Logger logger = LoggerFactory.getLogger(readExcel.class);

    String PATH = "uploadFiles/";
    String fileName = PATH + "01 标准试验数据库模板.xlsx";
    String easyFileName = PATH + "Specimen_RawData_2.csv";

    @Test
    public void readExcelTest() {
        /*ExcelReaderBuilder readWorkBook = EasyExcel.read(fileName,new ExcelListener());
        ExcelReader excelReader = readWorkBook.build();
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
        for(ReadSheet sheet:sheets){
            logger.info("sheet name:{}",sheet.getSheetName());
            excelReader.read(sheet);
        }*/
        EasyExcel.read(fileName, new ExcelListener()).sheet().headRowNumber(0).doRead();
    }

    @Test
    public void readCSVTest(){
        List<Map<String,Object>> list = new ArrayList<>();
        CsvReader reader = CsvUtil.getReader();
        //设置文件读取的分隔符
        //reader.setFieldSeparator(separator.toCharArray()[0]);
        //根据特定的编码方式读取File的内容
        CsvData csvData = reader.read(Paths.get(easyFileName), Charset.forName("GBK"));
        //读取文件中的所有的行数据
        List<CsvRow> rows = csvData.getRows();
        //若有标题，则获取首行标题
        List<String> firstRawList = rows.get(0).getRawList();
        for (int i = 2;i< rows.size();i++){
                Map<String, Object> map = new HashMap<>();
                for(int j=0;j<rows.get(i).size();j++){
                    map.put(firstRawList.get(j), rows.get(i).get(j));
                }
                list.add(map);
        }
        logger.info(firstRawList.toString());
        logger.info(list.toString());
    }
}
