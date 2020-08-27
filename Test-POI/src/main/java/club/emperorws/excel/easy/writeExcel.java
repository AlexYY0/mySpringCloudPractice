package club.emperorws.excel.easy;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.merge.OnceAbsoluteMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/29 1:02
 * @Description:
 **/
public class writeExcel {
    String PATH = "uploadFiles/";
    String fileName = PATH + "Test模板.xlsx";

    @Test
    public void writeExcelTest() {
        ExcelWriter excelWriter = null;

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)11);
        headWriteFont.setFontName("宋体");
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setWrapped(true);
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headWriteCellStyle.setBorderTop(BorderStyle.THIN);
        headWriteCellStyle.setBorderRight(BorderStyle.THIN);
        headWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        headWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)11);
        contentWriteFont.setFontName("宋体");
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        contentWriteCellStyle.setWrapped(true);
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        ExcelStyleAnnotationCellWriteHandler horizontalCellStyleStrategy =
                new ExcelStyleAnnotationCellWriteHandler(headWriteCellStyle, contentWriteCellStyle);
        OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy = new OnceAbsoluteMergeStrategy(0, 25, 0, 0);

        try {
            //设置列宽 设置每列的宽度
            excelWriter = EasyExcel.write(fileName).registerWriteHandler(horizontalCellStyleStrategy).registerWriteHandler(onceAbsoluteMergeStrategy).build();
            // 把sheet设置为不需要头 不然会输出sheet的头 这样看起来第一个table 就有2个头了
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").needHead(Boolean.FALSE).build();
            // 这里必须指定需要头，table 会继承sheet的配置，sheet配置了不需要，table 默认也是不需要
            for(int i=0;i<8;i++){
                WriteTable writeTable = null;
                if(i!=7){
                    writeTable = EasyExcel.writerTable(i).head(head(i)).relativeHeadRowIndex(0).needHead(Boolean.TRUE).build();
                }else {
                    writeTable = EasyExcel.writerTable(i).head(head(i)).relativeHeadRowIndex(10).needHead(Boolean.TRUE).build();
                }
                switch (i){
                    case 2:excelWriter.write(dataList().subList(i, i+2), writeSheet, writeTable);
                        break;
                    case 3:
                    case 4:excelWriter.write(dataList().subList(i+1, i+2), writeSheet, writeTable);
                        break;
                    case 5:excelWriter.write(null, writeSheet, writeTable);
                        break;
                    default:excelWriter.write(dataList().subList(i, i+1), writeSheet, writeTable);
                        break;
                }
            }
            /*WriteTable writeTable0 = EasyExcel.writerTable(0).head(head0()).relativeHeadRowIndex(0).needHead(Boolean.TRUE).build();
            WriteTable writeTable1 = EasyExcel.writerTable(1).head(head1()).relativeHeadRowIndex(0).needHead(Boolean.TRUE).build();
            WriteTable writeTable2 = EasyExcel.writerTable(2).head(head2()).relativeHeadRowIndex(0).needHead(Boolean.TRUE).build();
            WriteTable writeTable3 = EasyExcel.writerTable(3).head(head3()).relativeHeadRowIndex(0).needHead(Boolean.TRUE).build();
            WriteTable writeTable4 = EasyExcel.writerTable(4).head(head4()).relativeHeadRowIndex(0).needHead(Boolean.TRUE).build();
            WriteTable writeTable5 = EasyExcel.writerTable(5).head(head5()).relativeHeadRowIndex(0).needHead(Boolean.TRUE).build();
            WriteTable writeTable6 = EasyExcel.writerTable(6).head(head6()).relativeHeadRowIndex(0).needHead(Boolean.TRUE).build();
            WriteTable writeTable7 = EasyExcel.writerTable(7).head(head7()).relativeHeadRowIndex(10).needHead(Boolean.TRUE).build();
            // 第一次写入会创建头
            excelWriter.write(dataList().subList(0, 1), writeSheet, writeTable0);
            excelWriter.write(dataList().subList(1, 2), writeSheet, writeTable1);
            excelWriter.write(dataList().subList(2, 4), writeSheet, writeTable2);
            excelWriter.write(dataList().subList(4, 5), writeSheet, writeTable3);
            excelWriter.write(dataList().subList(5, 6), writeSheet, writeTable4);
            excelWriter.write(dataList().subList(6, 6), writeSheet, writeTable5);
            excelWriter.write(dataList().subList(6, 7), writeSheet, writeTable6);
            excelWriter.write(dataList().subList(7, 8), writeSheet, writeTable7);*/
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        //EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(null);
    }

    private List<List<String>> head(int i){
        switch (i){
            case 0:return head0();
            case 1:return head1();
            case 2:return head2();
            case 3:return head3();
            case 4:return head4();
            case 5:return head5();
            case 6:return head6();
            case 7:return head7();
        }
        return null;
    }

    private List<List<String>> head0() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        List<String> head1 = new ArrayList<String>();
        head1.add("试验信息");
        List<String> head2 = new ArrayList<String>();
        head2.add("试验信息");
        List<String> head3 = new ArrayList<String>();
        head3.add("试验信息");
        List<String> head4 = new ArrayList<String>();
        head4.add("试验信息");
        List<String> head5 = new ArrayList<String>();
        head5.add("试验信息");
        List<String> head6 = new ArrayList<String>();
        head6.add("试验信息");
        List<String> head7 = new ArrayList<String>();
        head7.add("试验信息");
        List<String> head8 = new ArrayList<String>();
        head8.add("试验信息");
        List<String> head9 = new ArrayList<String>();
        head9.add("试验信息");
        List<String> head10 = new ArrayList<String>();
        head10.add("试验信息");
        List<String> head11 = new ArrayList<String>();
        head11.add("试验信息");
        List<String> head12 = new ArrayList<String>();
        head12.add("试验信息");
        List<String> head13 = new ArrayList<String>();
        head13.add("试验信息");
        List<String> head14 = new ArrayList<String>();
        head14.add("试验信息");
        List<String> head15 = new ArrayList<String>();
        head15.add("试验信息");
        List<String> head16 = new ArrayList<String>();
        head16.add("试验信息");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        list.add(head11);
        list.add(head12);
        list.add(head13);
        list.add(head14);
        list.add(head15);
        list.add(head16);
        return list;
    }

    private List<List<String>> head1() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        List<String> head1 = new ArrayList<String>();
        head1.add("试验承担方");
        List<String> head2 = new ArrayList<String>();
        head2.add("试验承担方");
        List<String> head3 = new ArrayList<String>();
        head3.add("试验承担方");
        List<String> head4 = new ArrayList<String>();
        head4.add("试验承担方");
        List<String> head5 = new ArrayList<String>();
        head5.add("试验承担方");
        List<String> head6 = new ArrayList<String>();
        head6.add("试验承担方");
        List<String> head7 = new ArrayList<String>();
        head7.add("试验承担方");
        List<String> head8 = new ArrayList<String>();
        head8.add("试验承担方");
        List<String> head9 = new ArrayList<String>();
        head9.add("试验承担方");
        List<String> head10 = new ArrayList<String>();
        head10.add("试验承担方");
        List<String> head11 = new ArrayList<String>();
        head11.add("试验承担方");
        List<String> head12 = new ArrayList<String>();
        head12.add("试验承担方");
        List<String> head13 = new ArrayList<String>();
        head13.add("试验承担方");
        List<String> head14 = new ArrayList<String>();
        head14.add("试验承担方");
        List<String> head15 = new ArrayList<String>();
        head15.add("试验承担方");
        List<String> head16 = new ArrayList<String>();
        head16.add("试验承担方");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        list.add(head11);
        list.add(head12);
        list.add(head13);
        list.add(head14);
        list.add(head15);
        list.add(head16);
        return list;
    }

    private List<List<String>> head2() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        List<String> head1 = new ArrayList<String>();
        head1.add("试验件信息");
        List<String> head2 = new ArrayList<String>();
        head2.add("试验件信息");
        List<String> head3 = new ArrayList<String>();
        head3.add("试验件信息");
        List<String> head4 = new ArrayList<String>();
        head4.add("试验件信息");
        List<String> head5 = new ArrayList<String>();
        head5.add("试验件信息");
        List<String> head6 = new ArrayList<String>();
        head6.add("试验件信息");
        List<String> head7 = new ArrayList<String>();
        head7.add("试验件信息");
        List<String> head8 = new ArrayList<String>();
        head8.add("试验件信息");
        List<String> head9 = new ArrayList<String>();
        head9.add("试验件信息");
        List<String> head10 = new ArrayList<String>();
        head10.add("试验件信息");
        List<String> head11 = new ArrayList<String>();
        head11.add("试验件信息");
        List<String> head12 = new ArrayList<String>();
        head12.add("试验件信息");
        List<String> head13 = new ArrayList<String>();
        head13.add("试验件信息");
        List<String> head14 = new ArrayList<String>();
        head14.add("试验件信息");
        List<String> head15 = new ArrayList<String>();
        head15.add("试验件信息");
        List<String> head16 = new ArrayList<String>();
        head16.add("试验件信息");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        list.add(head11);
        list.add(head12);
        list.add(head13);
        list.add(head14);
        list.add(head15);
        list.add(head16);
        return list;
    }

    private List<List<String>> head3() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        List<String> head1 = new ArrayList<String>();
        head1.add("加载条件");
        List<String> head2 = new ArrayList<String>();
        head2.add("加载条件");
        List<String> head3 = new ArrayList<String>();
        head3.add("加载条件");
        List<String> head4 = new ArrayList<String>();
        head4.add("加载条件");
        List<String> head5 = new ArrayList<String>();
        head5.add("加载条件");
        List<String> head6 = new ArrayList<String>();
        head6.add("加载条件");
        List<String> head7 = new ArrayList<String>();
        head7.add("加载条件");
        List<String> head8 = new ArrayList<String>();
        head8.add("加载条件");
        List<String> head9 = new ArrayList<String>();
        head9.add("加载条件");
        List<String> head10 = new ArrayList<String>();
        head10.add("加载条件");
        List<String> head11 = new ArrayList<String>();
        head11.add("加载条件");
        List<String> head12 = new ArrayList<String>();
        head12.add("加载条件");
        List<String> head13 = new ArrayList<String>();
        head13.add("加载条件");
        List<String> head14 = new ArrayList<String>();
        head14.add("加载条件");
        List<String> head15 = new ArrayList<String>();
        head15.add("加载条件");
        List<String> head16 = new ArrayList<String>();
        head16.add("加载条件");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        list.add(head11);
        list.add(head12);
        list.add(head13);
        list.add(head14);
        list.add(head15);
        list.add(head16);
        return list;
    }

    private List<List<String>> head4() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        List<String> head1 = new ArrayList<String>();
        head1.add("数据测量");
        List<String> head2 = new ArrayList<String>();
        head2.add("数据测量");
        List<String> head3 = new ArrayList<String>();
        head3.add("数据测量");
        List<String> head4 = new ArrayList<String>();
        head4.add("数据测量");
        List<String> head5 = new ArrayList<String>();
        head5.add("数据测量");
        List<String> head6 = new ArrayList<String>();
        head6.add("数据测量");
        List<String> head7 = new ArrayList<String>();
        head7.add("数据测量");
        List<String> head8 = new ArrayList<String>();
        head8.add("数据测量");
        List<String> head9 = new ArrayList<String>();
        head9.add("数据测量");
        List<String> head10 = new ArrayList<String>();
        head10.add("数据测量");
        List<String> head11 = new ArrayList<String>();
        head11.add("数据测量");
        List<String> head12 = new ArrayList<String>();
        head12.add("数据测量");
        List<String> head13 = new ArrayList<String>();
        head13.add("数据测量");
        List<String> head14 = new ArrayList<String>();
        head14.add("数据测量");
        List<String> head15 = new ArrayList<String>();
        head15.add("数据测量");
        List<String> head16 = new ArrayList<String>();
        head16.add("数据测量");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        list.add(head11);
        list.add(head12);
        list.add(head13);
        list.add(head14);
        list.add(head15);
        list.add(head16);
        return list;
    }

    private List<List<String>> head5() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        List<String> head1 = new ArrayList<String>();
        head1.add("上传附件");
        List<String> head2 = new ArrayList<String>();
        head2.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head3 = new ArrayList<String>();
        head3.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head4 = new ArrayList<String>();
        head4.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head5 = new ArrayList<String>();
        head5.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head6 = new ArrayList<String>();
        head6.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head7 = new ArrayList<String>();
        head7.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head8 = new ArrayList<String>();
        head8.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head9 = new ArrayList<String>();
        head9.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head10 = new ArrayList<String>();
        head10.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head11 = new ArrayList<String>();
        head11.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head12 = new ArrayList<String>();
        head12.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head13 = new ArrayList<String>();
        head13.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head14 = new ArrayList<String>();
        head14.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head15 = new ArrayList<String>();
        head15.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        List<String> head16 = new ArrayList<String>();
        head16.add("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        list.add(head11);
        list.add(head12);
        list.add(head13);
        list.add(head14);
        list.add(head15);
        list.add(head16);
        return list;
    }

    private List<List<String>> head6() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        List<String> head1 = new ArrayList<String>();
        head1.add("试验参数记录");
        List<String> head2 = new ArrayList<String>();
        head2.add("试验参数记录");
        List<String> head3 = new ArrayList<String>();
        head3.add("试验参数记录");
        List<String> head4 = new ArrayList<String>();
        head4.add("试验参数记录");
        List<String> head5 = new ArrayList<String>();
        head5.add("试验参数记录");
        List<String> head6 = new ArrayList<String>();
        head6.add("试验参数记录");
        List<String> head7 = new ArrayList<String>();
        head7.add("试验参数记录");
        List<String> head8 = new ArrayList<String>();
        head8.add("试验参数记录");
        List<String> head9 = new ArrayList<String>();
        head9.add("试验参数记录");
        List<String> head10 = new ArrayList<String>();
        head10.add("试验参数记录");
        List<String> head11 = new ArrayList<String>();
        head11.add("试验参数记录");
        List<String> head12 = new ArrayList<String>();
        head12.add("试验参数记录");
        List<String> head13 = new ArrayList<String>();
        head13.add("试验参数记录");
        List<String> head14 = new ArrayList<String>();
        head14.add("试验参数记录");
        List<String> head15 = new ArrayList<String>();
        head15.add("试验参数记录");
        List<String> head16 = new ArrayList<String>();
        head16.add("试验参数记录");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        list.add(head11);
        list.add(head12);
        list.add(head13);
        list.add(head14);
        list.add(head15);
        list.add(head16);
        return list;
    }

    private List<List<String>> head7() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        List<String> head1 = new ArrayList<String>();
        head1.add("试验公式");
        List<String> head2 = new ArrayList<String>();
        head2.add("试验公式");
        List<String> head3 = new ArrayList<String>();
        head3.add("试验公式");
        List<String> head4 = new ArrayList<String>();
        head4.add("试验公式");
        List<String> head5 = new ArrayList<String>();
        head5.add("试验公式");
        List<String> head6 = new ArrayList<String>();
        head6.add("试验公式");
        List<String> head7 = new ArrayList<String>();
        head7.add("试验公式");
        List<String> head8 = new ArrayList<String>();
        head8.add("试验公式");
        List<String> head9 = new ArrayList<String>();
        head9.add("试验公式");
        List<String> head10 = new ArrayList<String>();
        head10.add("试验公式");
        List<String> head11 = new ArrayList<String>();
        head11.add("试验公式");
        List<String> head12 = new ArrayList<String>();
        head12.add("试验公式");
        List<String> head13 = new ArrayList<String>();
        head13.add("试验公式");
        List<String> head14 = new ArrayList<String>();
        head14.add("试验公式");
        List<String> head15 = new ArrayList<String>();
        head15.add("试验公式");
        List<String> head16 = new ArrayList<String>();
        head16.add("试验公式");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        list.add(head11);
        list.add(head12);
        list.add(head13);
        list.add(head14);
        list.add(head15);
        list.add(head16);
        return list;
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        List<Object> data1 = new ArrayList<Object>();
        data1.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        data1.add("型号");
        data1.add("");
        data1.add("项目团队");
        data1.add("");
        data1.add("试验类型");
        data1.add("");
        data1.add("试验层级");
        data1.add("");
        data1.add("试验名称");
        data1.add("");
        data1.add("试验标准");
        data1.add("");
        data1.add("试验报告");
        List<Object> data2 = new ArrayList<Object>();
        data2.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        data2.add("试验单位");
        data2.add("");
        data2.add("试验设备名称");
        data2.add("");
        data2.add("试验方案");
        data2.add("");
        data2.add("试验夹具");
        data2.add("");
        data2.add("试验室温度");
        data2.add("");
        data2.add("试验室湿度");
        data2.add("");
        data2.add("试验日期");
        List<Object> data3 = new ArrayList<Object>();
        data3.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        data3.add("材料规范（型类级）");
        data3.add("");
        data3.add("材料批号");
        data3.add("");
        data3.add("试验件制造工艺");
        data3.add("");
        data3.add("铺层顺序");
        data3.add("");
        data3.add("理论尺寸（mm*mm*mm）");
        data3.add("");
        data3.add("试样数量");
        data3.add("");
        data3.add("试验件制造方");
        data3.add("");
        data3.add("试验件制造FO");
        List<Object> data4 = new ArrayList<Object>();
        data4.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        data4.add("加强片材料");
        data4.add("");
        data4.add("加强片厚度");
        data4.add("");
        data4.add("加强片长度");
        data4.add("");
        data4.add("胶黏剂种类");
        data4.add("");
        data4.add("烘干方式");
        List<Object> data5 = new ArrayList<Object>();
        data5.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        data5.add("加载速率（mm/min)");
        data5.add("");
        data5.add("试验件夹持方式");
        data5.add("");
        data5.add("载荷类型");
        List<Object> data6 = new ArrayList<Object>();
        data6.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        data6.add("应变测量方式");
        data6.add("");
        data6.add("应变片/引伸计型号");
        data6.add("");
        data6.add("应变片粘贴位置");
        List<Object> data7 = new ArrayList<Object>();
        data7.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        data7.add("试样编号/序号");
        data7.add("试样长度L");
        data7.add("试样宽度b");
        data7.add("试样厚度h");
        data7.add("破坏前最大力Pmax（kN）");
        data7.add("引伸计位移δi（mm）");
        data7.add("引伸计标距Lg(mm)");
        data7.add("试样破坏模式");
        data7.add("极限拉伸强度Ftu(MPa)");
        data7.add("拉伸应力σi(MPa)");
        data7.add("极限拉伸应变εi");
        data7.add("拉伸弹性模量E");
        data7.add("泊松比");
        data7.add("载荷-位移曲线");
        List<Object> data8 = new ArrayList<Object>();
        data8.add("层板拉伸试验（聚合物基复合材料拉伸性能标准试验方法）");
        data8.add("平均值");
        data8.add("");
        data8.add("标准差");
        data8.add("");
        data8.add("离散系数");
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        list.add(data6);
        list.add(data7);
        list.add(data8);
        return list;
    }

    @Test
    public void deleteFile(){
        File file = new File(fileName);
        file.delete();
    }
}
