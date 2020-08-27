package client;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
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
import org.junit.Test;
import com.itextpdf.kernel.pdf.PdfDocument;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author: EmperorWS
 * @Date: 2020/8/13 15:29
 * @Description:
 **/
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

    /*public static PdfPTable createTable(PdfWriter writer) throws DocumentException, IOException{
        Font font = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
        PdfPTable table = new PdfPTable(4);//生成一个四列的表格
        // 设置表格宽度比例为%100
        table.setWidthPercentage(100);
        PdfPCell cell;
        int hsize = 20;
        cell = new PdfPCell(new Phrase("材料接收试验",font));
        cell.setColspan(2);
        cell.setRowspan(2);
        cell.setFixedHeight(hsize*2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("规范: NASM 7839",font));
        cell.setColspan(2);
        cell.setRowspan(1);
        cell.setFixedHeight(hsize);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("日期: 2020-01-14",font));
        cell.setColspan(1);
        cell.setRowspan(1);
        cell.setFixedHeight(hsize);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("版次: B",font));
        cell.setColspan(1);
        cell.setRowspan(1);
        cell.setFixedHeight(hsize);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("材料说明: NAS514 螺钉",font));
        cell.setColspan(2);
        cell.setRowspan(3);
        cell.setFixedHeight(hsize*3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("材料工程: 黄裳",font));
        cell.setColspan(2);
        cell.setRowspan(1);
        cell.setFixedHeight(hsize);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("理化计量中心: 张三",font));
        cell.setColspan(2);
        cell.setRowspan(1);
        cell.setFixedHeight(hsize);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("质保工艺控制: 李四",font));
        cell.setColspan(2);
        cell.setRowspan(1);
        cell.setFixedHeight(hsize);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("注1：",font));
        cell.setColspan(4);
        cell.setRowspan(1);
        table.addCell(cell);
        return table;
    }
    @Test
    public void createTablePDF() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.addTitle("example of PDF");
            document.open();
            PdfPTable table = createTable(writer);
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }*/

    private java.util.List<IElement> getFixContent(String content,ConverterProperties converterProperties) {
        if (content.startsWith("<div>")) {
            content = content.replaceAll("<div>", "<div style='line-height:18pt;font-size:16px;'>");
        } else {
            content = "<div style='line-height:18pt;font-size:16px;'>" + content + "</div>";
        }
        return HtmlConverter.convertToElements(content, converterProperties);
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
        cell = new Cell(2,2).add(new Paragraph("材料接收试验").setFont(sysFont).setTextAlignment(TextAlignment.CENTER));
        //cell.setHeight(hsize*2);
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);//设置水平居中
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph("规范: ").setFont(sysFont)
                .add("NASM 7839").setFont(sysFont));
        //cell.add(new Paragraph("NASM 7839").setTextAlignment(TextAlignment.CENTER).setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1,1);
        cell.add(new Paragraph("日期: ").setTextAlignment(TextAlignment.LEFT).setFont(sysFont));
        cell.add(new Paragraph("2020-01-14").setTextAlignment(TextAlignment.CENTER).setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1,1);
        cell.add(new Paragraph("版次: ").setTextAlignment(TextAlignment.LEFT).setFont(sysFont));
        cell.add(new Paragraph("B").setTextAlignment(TextAlignment.CENTER).setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(3,2);
        cell.add(new Paragraph("材料说明:").setFont(sysFont).setRelativePosition(0,0,0,32).setTextAlignment(TextAlignment.LEFT).setFontSize(8));
        cell.add(new Paragraph("NAS514 螺钉").setFont(sysFont).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE);
        //cell.setHeight(hsize*10);
        table.addCell(cell);
        cell = new Cell(1,2);
        cell.add(new Paragraph("材料工程: ").setTextAlignment(TextAlignment.LEFT).setFont(sysFont).add(new Paragraph("黄裳").setFont(sysFont)));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1,2).add(new Paragraph("理化计量中心: ").setTextAlignment(TextAlignment.LEFT).setFont(sysFont).add(new Paragraph("张三").setFont(sysFont)));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1,2).add(new Paragraph("质保工艺控制: ").setTextAlignment(TextAlignment.LEFT).setFont(sysFont).add(new Paragraph("李四").setFont(sysFont)));
        //cell.setHeight(hsize);
        table.addCell(cell);

        String content = "<div><span style='text-align:left'>注1：夹层长度小</span><span style='text-align:right'>注1：夹层长度小</span></div><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>" +
                "<span style='color:black;background-color:red;font-size:20px'>注1：夹层长度小于3倍公称直径的全螺纹紧固件，不要求拉伸试验；</span><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>" +
                "<span style='color:black;background-color:red;font-size:20px'>注1：夹层长度小于3倍公称直径的全螺纹紧固件，不要求拉伸试验；</span><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>" +
                "<span style='color:black;background-color:red;font-size:20px'>注1：夹层长度小于3倍公称直径的全螺纹紧固件，不要求拉伸试验；</span><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>" +
                "<span style='color:black;background-color:red;font-size:20px'>注1：夹层长度小于3倍公称直径的全螺纹紧固件，不要求拉伸试验；</span><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>" +
                "<span style='color:black;background-color:red;font-size:20px'>注1：夹层长度小于3倍公称直径的全螺纹紧固件，不要求拉伸试验；</span><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>" +
                "<span style='color:black;background-color:red;font-size:20px'>注1：夹层长度小于3倍公称直径的全螺纹紧固件，不要求拉伸试验；</span><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>" +
                "<span style='color:black;background-color:red;font-size:20px'>注1：夹层长度小于3倍公称直径的全螺纹紧固件，不要求拉伸试验；</span><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>" +
                "<span style='color:black;background-color:red;font-size:20px'>注1：夹层长度小于3倍公称直径的全螺纹紧固件，不要求拉伸试验；</span><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>" +
                "<span style='color:black;background-color:red;font-size:20px'>注1：夹层长度小于3倍公称直径的全螺纹紧固件，不要求拉伸试验；</span><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>" +
                "<span style='color:black;background-color:red;font-size:20px'>注1：夹层长度小于3倍公称直径的全螺纹紧固件，不要求拉伸试验；</span><p>注2：验收指标按验收标准或采购规格，当标准与采购规范要求不一致时，以标准要求为准；</p><p>注3：公称直径小于0.19in时，不需要硬度测试；注4：若已进行拉伸试验，不可再测试硬度。</p>";
        ConverterProperties converterProperties;
        FontProvider fontProvider = new FontProvider();
        fontProvider.addFont(sysFont.getFontProgram(), "UniGB-UCS2-H");
        converterProperties = new ConverterProperties();
        converterProperties.setFontProvider(fontProvider);

        Div overall = new Div();
        java.util.List<IElement> iElements = getFixContent(content,converterProperties);
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

        cell = new Cell(1,4).add(overall);
        table.addCell(cell);
        return table;
    }
    @Test
    public void createTablePDF() throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(fileName);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        try {
            //document.open();
            document.showTextAligned(new Paragraph("SAMC 51-28 (2009-11)").setFontSize(8), 35, 806, TextAlignment.LEFT);
            document.showTextAligned(new Paragraph("MAT NO.88").setFontSize(8), 559, 806, TextAlignment.RIGHT);
            Table table = createTable();
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
