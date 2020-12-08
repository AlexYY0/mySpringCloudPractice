package client;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author: EmperorWS
 * @Date: 2020/8/13 15:29
 * @Description:
 **/
@Slf4j
public class exportPDFTest {
    String PATH = "uploadFiles/";
    String fileName = PATH + "testPDF.pdf";

    /*@Test
    public void exportPDF(){
        BaseFont bf;
        Font font = null;
        Font font2 = null;
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//创建字体
            font = new Font(bf, 12);//使用字体
            font2 = new Font(bf, 12, Font.BOLD);//使用字体
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            Paragraph elements = new Paragraph("常州武进1区飞行报告", font2);
            elements.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(elements);
            *//*Image png = Image.getInstance("E:\\Learning\\threadLocal.png");
            png.setAlignment(Image.ALIGN_CENTER);
            document.add(png);*//*
            document.add(new Paragraph("任务编号：20190701        开始日期：20190701", font));
            document.add(new Paragraph("任务名称：常州武进1区     结束日期：20190701", font));
            document.add(new Paragraph("平均飞行高度：100m        平均飞行速度：100km/h", font));
            document.add(new Paragraph("任务面积：1000㎡      结束日期：20190701", font));
            document.add(new Paragraph("飞行总时长：1000㎡", font));
            document.addCreationDate();
            document.close();
        } catch (Exception e) {
            System.out.println("file create exception");
        }
    }*/

    public static Table createTable() throws IOException {
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light",
                "UniGB-UCS2-H", false);
        Table table = new Table(new UnitValue[]{
                UnitValue.createPercentValue(35),
                UnitValue.createPercentValue(35),
                UnitValue.createPercentValue(15),
                UnitValue.createPercentValue(15),
        });//生成一个四列的表格
        table.setWidth(UnitValue.createPercentValue(100));
        table.setHeight(UnitValue.createPercentValue(100));
        Cell cell;
        int hsize = 30;
        cell = new Cell(2, 2).add(new Paragraph("材料接收试验").setFont(sysFont).setTextAlignment(TextAlignment.CENTER));
        //cell.setHeight(hsize*2);
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);//设置水平居中
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph("规范: NASM 7839").setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1, 1).add(new Paragraph("日期: 2020-01-14").setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1, 1).add(new Paragraph("版次: B").setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(3, 2);
        cell.add(new Paragraph("材料说明:").setFont(sysFont).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.TOP).setFontSize(8));
        cell.add(new Paragraph("NAS514 螺钉").setFont(sysFont).setTextAlignment(TextAlignment.CENTER));
        cell.setHeight(hsize * 10);
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph("材料工程: 黄裳").setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph("理化计量中心: 张三").setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph("质保工艺控制: 李四").setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1, 4).add(new Paragraph("注1：").setFont(sysFont));
        table.addCell(cell);
        return table;
    }

    @Test
    public void createTablePDF() throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(fileName);
        PdfDocument pdf = new PdfDocument(writer);
        try (Document document = new Document(pdf, PageSize.A4)) {
            //document.open();
            Table table = createTable();
            document.add(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
