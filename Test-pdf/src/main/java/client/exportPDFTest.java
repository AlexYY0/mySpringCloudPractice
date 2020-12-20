package client;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.FontSet;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

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

    public static FontProvider getFontProvider(){
        FontProvider fontProvider = new FontProvider();
        /*String pathFonts = fontProvider.getClass().getClassLoader().getResource("font").getPath();
        fontProvider.addDirectory(pathFonts);*/
        /*fontProvider.addFont(fontProvider.getClass().getClassLoader().getResource("font/simsun.ttc").getPath());
        fontProvider.addFont(fontProvider.getClass().getClassLoader().getResource("font/SourceHanSansCN-Regular.ttf").getPath());
        fontProvider.addStandardPdfFonts();*/
        fontProvider.addFont("E:\\Java从零开始\\JavaWeb\\SpringCloud\\mySpringCloudPractice\\Test-pdf\\src\\main\\resources\\font\\STSONG.TTF"
        ,PdfEncodings.IDENTITY_H);
        return fontProvider;
    }

    private java.util.List<IElement> getFixContent(String content, ConverterProperties proper) {
        if (content.startsWith("<div>")) {
            content = content.replaceAll("<div>", "<div style='line-height:18pt;font-size:16px;'>");
        } else {
            content = "<div style='line-height:18pt;font-size:16px;'>" + content + "</div>";
        }
        //content = "<html><body style='font-family:SimSun;'>" + content + "</body></html>";
        return HtmlConverter.convertToElements(content, proper);
    }

    private void addParagraphStyleCircle(Style style, java.util.List<IElement> children) {
        for (IElement child : children) {
            if (child instanceof Paragraph) {
                Paragraph element = (Paragraph) child;
                element.addStyle(style);
                java.util.List<IElement> children1 = element.getChildren();
                this.addParagraphStyleCircle(style, children1);
            }
            if (child instanceof Div) {
                Div div = (Div) child;
                java.util.List<IElement> children1 = div.getChildren();
                this.addParagraphStyleCircle(style, children1);
            }
            if (child instanceof Text) {
                Text text = (Text) child;
                text.addStyle(style);
            }
        }
    }

    public Table createTable() throws IOException {
        /*PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light",
                "UniGB-UCS2-H", false);*/
        FontProgram fp = FontProgramFactory.createFont("E:\\Java从零开始\\JavaWeb\\SpringCloud\\mySpringCloudPractice\\Test-pdf\\src\\main\\resources\\font\\simfang.ttf");
        PdfFont sysFont = PdfFontFactory.createFont(fp, PdfEncodings.IDENTITY_H,true);
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
        table.addHeaderCell(cell);
//        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph("规范: NASM 7839").setFont(sysFont));
        //cell.setHeight(hsize);
        table.addHeaderCell(cell);
//        table.addCell(cell);
        cell = new Cell(1, 1).add(new Paragraph("日期: 2020-01-14").setFont(sysFont));
        //cell.setHeight(hsize);
        table.addHeaderCell(cell);
//        table.addCell(cell);
        cell = new Cell(1, 1).add(new Paragraph("版次: B").setFont(sysFont));
        //cell.setHeight(hsize);
        table.addHeaderCell(cell);
//        table.addCell(cell);
        cell = new Cell(3, 2);
        cell.add(new Paragraph("材料说明:").setFont(sysFont).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.TOP).setFontSize(8));
        cell.add(new Paragraph("NAS514 螺钉312313         123123").setFont(sysFont).setTextAlignment(TextAlignment.CENTER));
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

        /*ConverterProperties proper = new ConverterProperties();
        FontProgram fp = FontProgramFactory.createFont("STSongStd-Light", true);
        FontProvider fontProvider = new FontProvider();
        fontProvider.addFont(fp, "UniGB-UCS2-H");
        proper.setFontProvider(fontProvider);*/

        FontProvider fontProvider = getFontProvider();
        ConverterProperties proper = new ConverterProperties();
        proper.setFontProvider(fontProvider);
        proper.setCharset("UTF-8");
        String oldcontent = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "<title>HTML报表测试</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<!-- 通过一个HTML链接来导出目标报表模版的PDF格式报表 -->\n" +
                "<a href=\"<%=request.getContextPath() %>/ureport/pdf?_u=file:demo.ureport.xml\">导出PDF格式报表</a>\n" +
                "<p></p>\n" +
                "<style type=\"text/css\">\n" +
                "._A1{width:320pt;text-align:center;vertical-align:middle;font-size:10pt;border-left:solid 1px rgb(0,0,0);border-right:solid 1px rgb(0,0,0);border-top:solid 1px rgb(0,0,0);border-bottom:solid 1px rgb(0,0,0);}\n" +
                "</style>\n" +
                "<table border='0' style='margin:auto;border-collapse:collapse;width:320pt'><tr style=\"height:18pt\"><td rowspan=\"3\" colspan=\"4\" class='_A1'  >\n" +
                "<h1>一列：</h1>\n" +
                "CO<sub>2</sub></td></tr><tr style=\"height:18pt\"></tr><tr style=\"height:18pt\"></tr></table>\n" +
                "</body>\n" +
                "</html>";
        Div overall = new Div();
        java.util.List<IElement> iElements = getFixContent(oldcontent, proper);
        for (IElement iElement : iElements) {
            Style style = new Style();
            style.setFontSize(10);
            style.setCharacterSpacing(0.7f);
            if (iElement instanceof Div) {
                Div div = (Div) iElement;
                java.util.List<IElement> children = div.getChildren();
                // 全部段落改成相同样式
                this.addParagraphStyleCircle(style, children);
                overall.add(div);
            } else if (iElement instanceof Paragraph) {
                Paragraph element = (Paragraph) iElement;
                overall.add(element.addStyle(style));
            }
        }
        cell = new Cell(1, 4).add(overall);
        table.addCell(cell);
        cell = new Cell(1, 4).add(new Paragraph("重复表尾测试").setFont(sysFont));
        table.addFooterCell(cell);
        return table;
    }

    /**
     * 字符串公式执行
     */
    @Test
    public void calculate() {
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        String str = "Math.sqrt(a,b)";
        jse.put("a", 4);
        jse.put("b", 2);
        // 判断这个脚本引擎是否支持编译功能
        if (jse instanceof Compilable) {
            Compilable compEngine = (Compilable) jse;
            try {
                // 进行编译
                CompiledScript script = compEngine.compile(str);
                System.out.println("结果：" + script.eval());
            } catch (ScriptException e) {
                log.error(e.getMessage());
            }
        } else {
            String.format("这个脚本引擎不支持编译!");
        }
    }
}
