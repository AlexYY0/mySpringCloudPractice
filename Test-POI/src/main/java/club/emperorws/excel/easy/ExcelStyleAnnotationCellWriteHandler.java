package club.emperorws.excel.easy;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * @Author: EmperorWS
 * @Date: 2020/7/30 9:31
 * @Description:
 **/
public class ExcelStyleAnnotationCellWriteHandler extends HorizontalCellStyleStrategy {
    public static final Logger logger = LoggerFactory.getLogger(ExcelStyleAnnotationCellWriteHandler.class);

    ExcelStyleAnnotationCellWriteHandler(WriteCellStyle headWriteCellStyle, WriteCellStyle contentWriteCellStyle) {
        super(headWriteCellStyle, contentWriteCellStyle);
    }

    @Override
    protected void setHeadCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        if (cell.getColumnIndex()==0){
            Workbook wb = cell.getSheet().getWorkbook();
            CellStyle cellStyle = wb.createCellStyle();
            Font font = wb.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 11);
            cellStyle.setFont(font);
            cellStyle.setWrapText(true);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
            cell.setCellStyle(cellStyle);
        } else if("试验任务书、试验大纲、试验件数模、成型工装数模、试验夹具数模、试验过程图像".equals(cell.getStringCellValue())){
            Workbook wb = cell.getSheet().getWorkbook();
            CellStyle cellStyle = wb.createCellStyle();
            Font font = wb.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 11);
            cellStyle.setFont(font);
            cellStyle.setWrapText(true);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.index);
            cell.setCellStyle(cellStyle);
        } else {
            super.setHeadCellStyle(cell, head, relativeRowIndex);
        }
    }

    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        if ("".equals(cell.getStringCellValue())) {
            Workbook wb = cell.getSheet().getWorkbook();
            CellStyle cellStyle = wb.createCellStyle();
            Font font = wb.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 11);
            cellStyle.setFont(font);
            cellStyle.setWrapText(true);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
            cell.setCellStyle(cellStyle);

        }else {
            super.setContentCellStyle(cell,head,relativeRowIndex);
        }
    }
}
