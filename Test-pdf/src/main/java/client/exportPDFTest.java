package client;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfFileAttachmentAnnotation;
import com.itextpdf.kernel.pdf.filespec.PdfFileSpec;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.python.core.Py;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import javax.script.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Test
    public void createTablePDF() throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(fileName);
        PdfDocument pdf = new PdfDocument(writer);
        try (Document document = new Document(pdf, PageSize.A4)) {
            //document.open();
            document.showTextAligned(new Paragraph("SAMC 51-28 (2009-11)").setFontSize(8), 35, 806, TextAlignment.LEFT);
            document.showTextAligned(new Paragraph("MAT NO.88").setFontSize(8), 559, 806, TextAlignment.RIGHT);
            //Add attachment
            //1.
            /*PdfDictionary parameters = new PdfDictionary();
            parameters.put(PdfName.ModDate, new PdfDate().getPdfObject());
            PdfFileSpec fileSpec = PdfFileSpec.createEmbeddedFileSpec(
                    pdf, Files.readAllBytes(Paths.get("uploadFiles","Specimen_RawData_2.csv")), "Specimen_RawData_2.csv",
                    "Specimen_RawData_2.csv", new PdfName("text/csv"), parameters,
                    PdfName.Data);
            fileSpec.put(new PdfName("AFRelationship"), new PdfName("Data"));
            pdf.addFileAttachment("Specimen_RawData_2.csv", fileSpec);
            PdfArray array = new PdfArray();
            array.add(fileSpec.getPdfObject().getIndirectReference());
            pdf.getCatalog().put(new PdfName("AF"), array);*/
            //2。注释的形式
            /*Rectangle rect = new Rectangle(36, 700, 100, 100);
            PdfFileSpec attachment = PdfFileSpec.createEmbeddedFileSpec(pdf, "C:\\Users\\EmperorWS\\Desktop\\历史数据兼容方案.docx", "display附件123", null);
            PdfAnnotation pdfAnnotation = new PdfFileAttachmentAnnotation(rect, attachment).setContents("Click me");
            pdf.getLastPage().addAnnotation(pdfAnnotation);*/
            //3.
            PdfFileSpec attachment1 = PdfFileSpec.createEmbeddedFileSpec(pdf, "C:\\Users\\EmperorWS\\Desktop\\命令大全.txt", "命令大全.txt", null);
            pdf.addFileAttachment("attachment1", attachment1);
            Table table = createTable(pdf);
            document.add(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private java.util.List<IElement> getFixContent(String content, ConverterProperties converterProperties) {
        if (content.startsWith("<div>")) {
            content = content.replaceAll("<div>", "<div style='line-height:18pt;font-size:16px;'>");
        } else {
            content = "<div style='line-height:18pt;font-size:16px;'>" + content + "</div>";
        }
        return HtmlConverter.convertToElements(content, converterProperties);
    }

    public Table createTable(PdfDocument pdf) throws IOException {
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
        cell = new Cell(1, 4).add(new Paragraph("Repeat Title").setFont(sysFont));
        table.addHeaderCell(cell);
        int hsize = 30;
        Rectangle rect = new Rectangle(36, 700, 10, 10);
        PdfFileSpec attachment = PdfFileSpec.createEmbeddedFileSpec(pdf, "C:\\Users\\EmperorWS\\Desktop\\123.png", "display附件11331323", null);
        PdfAnnotation fileAnnotation = new PdfFileAttachmentAnnotation(rect, attachment).setContents("点击打开附件");
        cell = new Cell(2, 2).add(new Paragraph("材料接收试验 goto PdfAnnotation：")
                .setAction(PdfAction.createNamed(PdfName.LastPage))
                .setFont(sysFont).setTextAlignment(TextAlignment.CENTER));
        //4pdf.getFirstPage().addAnnotation(fileAnnotation);
        //cell.setHeight(hsize*2);
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);//设置水平居中
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);//设置垂直居中
        //重复表头table.addHeaderCell(cell);
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph("规范Url: ")
                .setAction(PdfAction.createURI("http://www.baidu.com"))
                .setFont(sysFont)
                .add("NASM 7839").setFont(sysFont));
        //cell.add(new Paragraph("NASM 7839").setTextAlignment(TextAlignment.CENTER).setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1, 1);
        //打开外部文件
        PdfObject attachment1 = pdf.getCatalog().getNameTree(PdfName.EmbeddedFiles).getNames().get("attachment1");
        cell.add(new Paragraph("日期goto other pdf: ")
                .setAction(PdfAction.createLaunch(PdfFileSpec.wrapFileSpecObject(attachment1)))
                .setTextAlignment(TextAlignment.LEFT).setFont(sysFont));
        cell.add(new Paragraph("2020-01-14").setTextAlignment(TextAlignment.CENTER).setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1, 1);
        cell.add(new Paragraph("版次goto current pdf: ")
                .setAction(PdfAction.createGoTo(PdfExplicitDestination.createFit(/*pdf.getPage(2)*/2)))
                .setTextAlignment(TextAlignment.LEFT)
                .setFont(sysFont));
        cell.add(new Paragraph("B").setTextAlignment(TextAlignment.CENTER).setFont(sysFont));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(3, 2);
        cell.add(new Paragraph("材料说明:").setFont(sysFont).setRelativePosition(0, 0, 0, 32).setTextAlignment(TextAlignment.LEFT).setFontSize(8));
        cell.add(new Paragraph("NAS514 螺钉                                                         24234").setFont(sysFont).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE);
        //cell.setHeight(hsize*10);
        table.addCell(cell);
        cell = new Cell(1, 2);
        cell.add(new Paragraph("材料工程: ").setTextAlignment(TextAlignment.LEFT).setFont(sysFont).add(new Paragraph("黄裳").setFont(sysFont)));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph("理化计量中心: ").setTextAlignment(TextAlignment.LEFT).setFont(sysFont).add(new Paragraph("张三").setFont(sysFont)));
        //cell.setHeight(hsize);
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph("质保工艺控制:").setTextAlignment(TextAlignment.LEFT).setFont(sysFont).add(new Paragraph("李四").setFont(sysFont)));
        //cell.setHeight(hsize);
        table.addCell(cell);

        //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        String oldContent = "<p>测试好文网为大家准备了关于大一计算机论文3000字范文,好文网里面收集了五十多篇关于好大一计算机论文3000字好文,希望可以帮助大家。更多关于大一计算机论文3000字内容请关注好文网\n" +
                "\n" +
                "　　关于规范大一学生使用计算机的思考\n" +
                "\n" +
                "　　摘　要：大学计算机基础教学课程是大学教育的重要教学内容,对于培养学生应用计算机能力,促进学生全面发展具有重要作用。但是我国大学计算机基础课程的教学现状并不理想,存在很多问题。因此,高校应该根据大学计算机的教学现状,对大学计算机基础课程进行有效的改革,从而提高大学计算机教学水平。更多计算机论文相关范文尽在top期刊论文网。\n" +
                "\n" +
                "　　关键词：计算机论文\n" +
                "\n" +
                "　　一、前言\n" +
                "\n" +
                "　　如今一切经济活动都离不开信息，以计算机技术为基础的高新技术的广泛应用，正改变着人们的生产方式、工作方式、生活方式和学习方式。近几年全国各高校都努力推广计算机基础教育，提高大学生整体的计算机应用水平，计算机导论、程序设计等基础课程已成为了各专业学生的必修课程。同时，由于现代信息技术已在教育中广泛应用，计算机也成为了一种教学载体，被应用在各学科的教学之中。教师提供的课件资料是电子文档，部分教材的课后练习附在配套光盘里，学生的作业通过网络上交。可见，大学生的学习越来越离不开计算机。\n" +
                "\n" +
                "　　但是，随着大学生拥有个人计算机的比例大幅上升，大学生因沉迷游戏、沉溺网络虚拟世界而导致学业荒废，进而被迫退学的情况越发严重。上海某高校某一学年退学试读和转学的237名学生中，80%以上是因为沉迷于电脑游戏和上网[1]。可见，如何让学生合理地使用计算机已成为各高校必须面对的难题。\n" +
                "\n" +
                "　　俗话说：“学好三年，学坏三天。”在大学生的四年校园生活中，第一年是最为重要的。大一学生非常有特点，也最需要引导。他们既保持一定的高中阶段的严谨和拼搏精神，又大都存在考上大学后“松绑”的懈怠心理，同时对新环境的适应非常迷茫[2]。因此，对大学生计算机使用的规范和引导，大一学年是最佳时机。\n" +
                "\n" +
                "　　二、计算机使用的本质\n" +
                "\n" +
                "　　要处理好大学生计算机使用的问题，首先需要让学生对计算机使用的本质有清晰认识。不能因为出现有学生因沉迷电脑游戏而影响学业，就把计算机定性为学生荒废学业的根源;也不能因为肯定了计算机使用的必要性，而对学生使用计算机放任不管。总之，高校对学生使用计算机的问题加强引导与管理。\n" +
                "\n" +
                "　　(一)计算机使用的误区\n" +
                "\n" +
                "　　1.学习目的与内容不明确\n" +
                "\n" +
                "　　大学生在使用计算机的过程中，最大的误区是学习目的与内容不明确。这体现在学生拥有计算机前期望值高，下决心要掌握计算机的应用;但当拥有计算机后，对计算机知识的学习只属于扫盲性质，不能有效地深入学习，有的还误入歧途，最终成了“聊天高手”、“游戏专家”[3]。\n" +
                "\n" +
                "　　华中科技大学于2008年对在校全日制本科生作了关于“学生购买计算机的主要用途”问卷调查。结果显示：购买初衷用于学习的占52.2%，而购买后实际用于学习的却只占24.5%。相反，购买初衷用于娱乐的占30.5%，而购买后升至55.3%。另外，“对已购计算机同学的影响”，与他人交流减少，学习成绩下降，课外活动减少，逃课增多的占64.7%[4]。\n" +
                "\n" +
                "　　可见，大部分学生对计算机的使用感到迷茫，不知所学，不知所用，将计算机过多地应用在娱乐上。\n" +
                "\n" +
                "　　2.缺乏正确的引导和监督\n" +
                "\n" +
                "　　大部分学生在对使用计算机感到迷茫时，他们没有得到正确的引导。甚至少数学生在沉溺网络、误入歧途时，也得不到及时的监督管理。\n" +
                "\n" +
                "　　因此，教师一方面要抓住学生“意愿大、行动少”的特点，肯定学生使用计算机的主动性，教育学生正确认识大学的学习方式，强调自学。除了课堂学习的相关应用外，教师要鼓励学生积极参加计算机等级考试，强化计算机应用能力，为学生指明学习方向。另一方面要理解学生休闲娱乐、缓解压力的需要，让其得到合理的宣泄，同时也要注意学生的惰性心理，适时给予提醒，尤其需要重视网络对大学生的影响。网络虚拟世界为学生提供了一个交往与锻炼的平台，但是也提供了一个逃避现实的空间，加上大学生具有回归群体和被群体认同的需要，但缺乏自身对群体行为的判断[5]，所以，往往部分学生沉溺游戏后，网络就会发展成几个宿舍的群体行为，进而影响班风。因此，加强对大学生在宿舍内使用计算机的监督管理是必不可少的。\n" +
                "\n" +
                "　　(二)计算机使用的内涵\n" +
                "\n" +
                "　　计算机是一种工具。如何学习掌握相应的计算机知识，并把其应用在相关的学习生活及以后工作中，是计算机使用的内涵所在。\n" +
                "\n" +
                "　　就计算机的应用而言，计算机的学习可分为以下几个层次：1.计算机的基础操作使用。包括文字输入、表格制作、图像处理与应用，等等。2.计算机软硬件知识。软件部分包括网络应用，应用软件的下载、安装与删除等;硬件部分包括计算机的零配件知识及计算机组装。3.计算机程序设计语言。简单的高级语言如VC、VB、Java等。4.与专业领域相关的软件应用。不同专业的学生在各自领域都会使用特定的应用软件。如Eviews、Protel、Pro/E等。\n" +
                "\n" +
                "　　可见，计算机使用与日常学习密切相关。从阅读课件、使用教材配套光盘内的软件辅助学习、以电子文档方式缴交作业、上网交流等这些简单的操作，到上网检索文献资料、精通专业办公应用软件、编写程序开发系统等这些复杂的操作，无不需要在计算机上实现。\n" +
                "\n" +
                "　　因此，大学生需要清楚计算机使用的内涵，在了解计算机相关应用的基础上为自己找到合适的定位，决定未来学习的发展方向，并持之以恒追求此目标，这样才能把计算机的效用充分发挥出来。\n" +
                "\n" +
                "　　三、大一学生的特点\n" +
                "\n" +
                "　　大一学生的特点非常鲜明。相对其他年级的学生，他们干劲十足，充满理想与憧憬，但是也好奇迷茫，容易在各种诱惑中迷失自我。\n" +
                "\n" +
                "　　(一)积极好学\n" +
                "\n" +
                "　　他们保持着高中时期刻苦拼搏的精神，同时对大学的学习生活充满好奇，学习主动性强。由于计算机在高校里已经成为教学的主要载体，因此大一学生对使用计算机辅助学习具有强烈的愿望。\n" +
                "\n" +
                "　　(二)可塑性强\n" +
                "\n" +
                "　　他们对大学生活充满期待，希望尽快成长为真正的大学生。他们的学习生活会因为得到积极有效的引导教育而受到重大影响。相关引导教育能规范他们对计算机的使用，进而提高使用效率。\n" +
                "\n" +
                "　　(三)自控力不强\n" +
                "\n" +
                "　　他们在高中时已习惯被教师和家长看管，每天被近乎无法完成的任务压着;进入大学后，摆脱了教师和家长看管，课后作业减少，个人的支配时间增多。他们突然感到“松绑”，使用计算机的时间和用途也能自我做主，这便导致大量精力投入到上网聊天和打游戏。\n" +
                "\n" +
                "　　(四)判断力不强\n" +
                "\n" +
                "　　他们从众心理突出，为融入同学们的生活圈，会盲目地模仿跟从大家的行为。当个别学生沉迷计算机游戏后，其他学生也陆续沉迷，像流行病毒蔓延一样。所以学生误入计算机使用的歧途往往以宿舍为单位。\n" +
                "\n" +
                "　　因此，综合以上大一学生的特点，各高校要找到相应的对策，防范于未然，好的方面要注重鼓励，加强引导;不足的方面也要注重培养锻炼，予以纠正，同时加强监督管理，抓住大一学年这个最佳时机，规范大学生的计算机使用。\n" +
                "\n" +
                "　　四、关于规范大一学生使用计算机的思考\n" +
                "\n" +
                "　　近来，不少高校已意识到规范大学生计算机使用的重要性，特别是大一学生。部分高校根据自身情况制定了相关规章制度，取得了良好效果。本文就两种不同情况，对规范大一学生使用计算机的管理提出以下见解：\n" +
                "\n" +
                "　　(一)允许大一学生带计算机回宿舍\n" +
                "\n" +
                "　　允许大一学生带计算机回宿舍，能为学生的日常学习提供诸多便利。从管理的角度看，特别是缺乏公共计算机实验室资源的学校，让学生在宿舍里使用计算机比让学生到校外网吧更好。但是，学校必须做好以下措施，保证使用计算机的效果能利大于弊。\n" +
                "\n" +
                "　　1.对学生的思想教育工作要到位。大一学生进校后，就应抓思想工作，让学生清楚使用计算机的真正目的，并深刻认识到滥用计算机的后果。如举一些沉溺网络的前车之鉴提醒新生。\n" +
                "\n" +
                "　　2.学生带计算机需办理申请手续，由班主任和辅导员签名，并完成系内登记和签订管理协议等步骤。若学生拥有计算机后，生活作息不规律、学习效率低下甚至成绩明显下降，则班主任强制限定其使用用途，并责令其调整使用时间段，如不改正则要求其将计算机由系内管理或搬回家。\n" +
                "\n" +
                "　　3.制定完备的宿舍管理和用电制度，并通过对上网时间和供电时间的控制加强管理计算机使用的效果，比如晚上准时拉闸熄灯，除风扇按钮保留外，其他插座不通电。\n" +
                "\n" +
                "　　只有在各方面加强管理，才能保证大一学生使用计算机更适当更有效。\n" +
                "\n" +
                "　　(二)不允许大一学生带计算机回宿舍\n" +
                "\n" +
                "　　不允许大一学生带计算机回宿舍，是基于大一学生特点而制定的措施。笔者更认同这种管理方式。\n" +
                "\n" +
                "　　首先，大一学生课程虽多，但多为基础课程，学习难度不大。学生对计算机的使用多限于基础的文档制作，此类任务完全可以在学校的公共计算机实验室内完成，无需单独为个人配置计算机。\n" +
                "\n" +
                "　　其次，大一学生保持着高中阶段的勤奋与好学。应该鼓励他们走出宿舍，到图书馆、自习室、电子阅览室去，培养他们的自学能力，尽早适应大学的学习方式。在宿舍内配置计算机并不是必需的。\n" +
                "\n" +
                "　　再次，对高校而言，虽然有辅导员、班主任、学生会共同管理学生，但学生人数众多，监督和管理并不能面面俱到。鉴于大一学生自控能力差，不允许他们在宿舍内配置计算机，更有利于在宿舍营造良好的学习生活环境。\n" +
                "\n" +
                "　　最后，学生来自五湖四海，一方面贫富差异明显，另一方面也导致大部分学生的计算机需在当地购买。虽然计算机的价格不断下降，但是对大部分学生而言，仍属贵重物品。此时若不对他们进行引导与管理，就很容易出现盲目跟风购买、攀比等不良现象，不利于班级团结。同时，财产安全的保障也是问题。\n" +
                "\n" +
                "　　然而，不允许大一学生带计算机回宿舍并不意味着要让他们远离计算机。相反，高校要为他们提供充足的公共资源，让学生很方便地使用计算机及网络资源。\n" +
                "\n" +
                "　　一份对广州地区大学生上网情况的问卷调查显示，接近一半的学生每周上网7―14小时。另外，在宿舍上网比重最大(占51.7%)，学校公共场所次之(22.4%)，再次是网吧(占14.8%)。该调查中的大一学生占调查人数的30.5%[6]。可见，使用计算机上网已成为大学生日常生活不可缺少的一部分，若大一学生不能在宿舍上网，他们就会往学校的公共场所或者网吧分流。\n" +
                "\n" +
                "　　因此，加强高校公共资源的建设和利用显得尤为重要。公共资源包括多个方面：公共计算机实验室，为学生提供阅读课件、完成作业、学习软件的场所;电子阅览室，能给学生提供上网交流、资料搜索、文献阅读的条件;语音学习室，为学生提供外语文献阅读、视频听力学习的环境;专业计算机实验室，让学生学习使用本专业的应用软件，有可能的话，还配备相应专业的上机辅导老师。\n" +
                "\n" +
                "　　只有一切从学生的实际需求出发，切实做好公共资源的建设，才能配合对大一学生的计算机管理工作，提高公共\n" +
                "\n" +
                "　　资源的利用，处理好大一学生计算机使用的问题。\n" +
                "\n" +
                "　　五、结语\n" +
                "\n" +
                "　　综上所述，要处理好大一学生计算机使用的问题，首要任务是加强高校的公共资源建设。在学校公共资源充足的情况下，可以根据大一学生的特点，暂不允许他们带计算机回宿舍。我们要在思想上对他们进行引导教育，在生活上进行管理监督，让他们尽快适应大学的学习方式，树立对计算机使用的正确认识。这样不但能充分发挥使用计算机辅助学习的优势，而且能尽量减少大学生沉迷游戏、沉溺网络现象的出现，更能为学生构建美好的大学生活奠定坚实的基础。\n" +
                "\n" +
                "　　参考文献：\n" +
                "\n" +
                "　　[1]洪超云.上网对大学生学习影响的调查[J].机械工业高教研究，2002，1：74-77.\n" +
                "\n" +
                "　　[2]张岩等.大一学生上网情况的调查与思考[J].沈阳工程学院学报(社会科学版)，2008.10：585-588.\n" +
                "\n" +
                "　　[3]张平松等.大学生计算机学习和利用的定位与思考[J].黑龙江教育(高教研究与评估)，2006，9：45-47.\n" +
                "\n" +
                "　　[4]徐楚楚.它与我们的生活――大学生电脑消费与使用现状调查分析报告[J].商场现代化，2008，2：210-211.\n" +
                "\n" +
                "　　[5]刘元元等.浅析大学生“宅一族”的几个相关问题[J].网络财富，2008.11：231-232.\n" +
                "\n" +
                "　　[6]罗贤甲.对大学生网络行为责任现状的审视――以广州地区大学生为例[J].广东青年干部学院学报，2007.5：75-79.\n" +
                "\n" +
                "　　作者：陈润华 , 陈姗\n" +
                "\n" +
                "　　计算机论文范文文献资源参考：http:///tecpaper/jishu/\n" +
                "\n" +
                "特别说明：本网站内容都来源于互联网，供浏览者学习、欣赏，使用原则非商业性或非盈利性用途，使用者不得侵犯本网站及相关权利人的合法权利。此外，使用者如对本网站内容用于其他用途时，须征得本网站及相关权力人的许可。\n" +
                "\n" +
                "本网站内容原作者如不愿意在本网站刊登内容，请及时通知本站，予以删除。测试2</p>";
        String content = oldContent.replaceAll("&nbsp;", "\t");
        ConverterProperties converterProperties;
        FontProvider fontProvider = new FontProvider();
        fontProvider.addFont(sysFont.getFontProgram(), "UniGB-UCS2-H");
        converterProperties = new ConverterProperties();
        converterProperties.setFontProvider(fontProvider);

        Div overall = new Div();
        java.util.List<IElement> iElements = getFixContent(oldContent, converterProperties);
        for (IElement iElement : iElements) {
            Style style = new Style();
            style.setFontSize(10);
            style.setCharacterSpacing(0.7f);
            //style.setFont(sysFont);
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
        //overall.setRotationAngle(Math.PI/2);
        table.addCell(cell);
        cell = new Cell(1, 4).add(new Paragraph("Repeat Footer").setFont(sysFont));
        table.addFooterCell(cell);
        return table;
    }

    private void addParagraphStyleCircle(Style style, java.util.List<IElement> children) throws IOException {
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light",
                "UniGB-UCS2-H", false);
        for (IElement child : children) {
            if (child instanceof Div) {
                Div div = (Div) child;
                java.util.List<IElement> children1 = div.getChildren();
                this.addParagraphStyleCircle(style, children1);
            } else if (child instanceof Paragraph) {
                Paragraph element = (Paragraph) child;
                element.addStyle(style);
                element.setFont(sysFont);
                java.util.List<IElement> children1 = element.getChildren();
                this.addParagraphStyleCircle(style, children1);
            } else if (child instanceof Table) {
                Table element = (Table) child;
                java.util.List<IElement> children1 = element.getChildren();
                this.addParagraphStyleCircle(style, children1);
            } else if (child instanceof Cell) {
                Cell element = (Cell) child;
                java.util.List<IElement> children1 = element.getChildren();
                this.addParagraphStyleCircle(style, children1);
            } else if (child instanceof Text) {
                Text text = (Text) child;
                text.setFont(sysFont);
                text.addStyle(style);
            }
        }
    }

    @Test
    public void testFor() {
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            for (int j = 0; j < 100; j++) {
                //System.out.println(i+","+j);
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("此处消耗了（s）: " + (end1 - start1));

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 1000000000; j++) {
                //System.out.println(i+","+j);
            }
        }
        long end2 = System.currentTimeMillis();
        System.out.println("此处消耗了（s）: " + (end2 - start2));
    }

    @Test
    public void creatPdf() throws IOException {
        String src = "C:\\Users\\EmperorWS\\Desktop\\test.pdf";
        String dest = "C:\\Users\\EmperorWS\\Desktop\\test2.pdf";

        //处理中文问题
        PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        PdfDocument pdf = new PdfDocument(new PdfReader(src), new PdfWriter(dest));

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
        Map<String, PdfFormField> fields = form.getFormFields();
        fields.get("testName").setValue("EmperorWS");
        fields.get("fabricationChargeBy").setValue("Saber");

        Table table = new Table(2);

        //表格 一行数据是一个list
        List<String> list = new ArrayList<>();
        list.add("日期");
        list.add("金额");

        List<String> list2 = new ArrayList<>();
        list2.add("2018-01-01");
        list2.add("100");

        List<List<String>> List = new ArrayList<>();
        List.add(list);
        List.add(list2);

        //表格数据填写
        for (int i = 0; i < 2; i++) {
            List<String> list0 = List.get(i);
            for (int j = 0; j < 2; j++) {
                Paragraph paragraph = new Paragraph(String.valueOf(list0.get(j)));
                Cell cell = new Cell();
                cell.add(paragraph)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)
                        .setFont(font);
                table.addCell(cell);
            }
        }

        pdf.close();
    }

    /**
     * Java自带的字符串公式计算
     */
    @Test
    public void javaCalculate() {
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        /*String str = "result = Math.sqrt(calc[1].a,calc[1].b)," +
                "result2 = Math.pow(calc[1].a,calc[1].b)";*/
        String str = "for(var i in calc) { calc[i].c = calc[i].a+calc[i].b }";
        List<Map<String, Object>> param = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>(1);
        m1.put("a", 0.7);
        m1.put("b", 0.1);
        m1.put("c", 100);
        param.add(m1);
        Map<String, Object> m2 = new HashMap<>(1);
        m2.put("a", 0.1);
        m2.put("b", 0.3);
        m2.put("c", 100);
        param.add(m2);
        jse.put("result", 200);
        jse.put("calc", param);
        // 判断这个脚本引擎是否支持编译功能
        if (jse instanceof Compilable) {
            Compilable compEngine = (Compilable) jse;
            try {
                // 进行编译
                CompiledScript script = compEngine.compile(str);
                //执行脚本
                script.eval();
                ScriptEngine engine = script.getEngine();
                Map<String, Object> test = engine.getBindings(ScriptContext.ENGINE_SCOPE);
                log.info("结果：result = {}", test.get("calc"));
            } catch (ScriptException e) {
                log.error(e.getMessage());
            }
        } else {
            log.error("这个脚本引擎不支持编译!");
        }
    }

    @Test
    public void evalEx() {
        /*String str = "import java.math.RoundingMode; for(Map<String,Objects> i in calc) {" +
                "i.c = ((BigDecimal)i.a+(BigDecimal)i.b).setScale(5,RoundingMode.HALF_DOWN).doubleValue()}";*/
        String str = "import java.math.RoundingMode; for(Map<String,Objects> i in calc) {" +
                "BigDecimal a = new BigDecimal(i.a.toString());" +
                "BigDecimal b = new BigDecimal(i.b.toString());" +
                "i.c = a+b" +
                "}";
        Map<String, Object> allParam = new HashMap<>(1);
        List<Map<String, Object>> param = new ArrayList<>(2);
        Map<String, Object> m1 = new HashMap<>(1);
        m1.put("a", 0.7);
        m1.put("b", 0.1);
        m1.put("c", 100);
        param.add(m1);
        Map<String, Object> m2 = new HashMap<>(1);
        m2.put("a", 0.1);
        m2.put("b", 0.3);
        m2.put("c", 100);
        param.add(m2);
        allParam.put("calc", param);
        Binding binding = new Binding(allParam);
        binding.setVariable("language", "Groovy");
        GroovyShell shell = new GroovyShell(binding);
        shell.evaluate(str);
        System.out.println(binding.getVariable("calc"));
    }

    @Test
    public void abCalculate() {
        Properties props = new Properties();
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(props, preprops, new String[]{});
        String[] path = new String[]{"C:\\Users\\EmperorWS\\Desktop\\calculate", "D:\\Python\\Lib\\site-packages\\numpy", "D:\\Python\\Lib\\site-packages\\scipy"};
        String path0 = "C:\\Users\\EmperorWS\\Desktop\\calculate";
        String path1 = "D:\\Python\\Lib\\site-packages";
        //String path2 = "D:\\Python\\Lib\\site-packages\\scipy";
        PySystemState sys = Py.getSystemState();
        sys.path.add(path0);
        sys.path.add(path1);
        //sys.path.add(path2);
        System.out.println(sys.path.toString());

        // 实例化环境和代码执行
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("C:\\Users\\EmperorWS\\Desktop\\calculate\\callABcompute.py");
    }

    @Test
    public void abCalculate1() {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("D:/Python/python.exe C:/Users/EmperorWS/Desktop/calculate/callABcompute.py");
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void boolTest() {
        boolean b = Boolean.parseBoolean("Y");
        System.out.println(b);
        String s = "test" + Double.parseDouble("3.0") * Double.parseDouble("2.0") + "Y";
        System.out.println(s);
    }

    @Test
    public void formateData() throws ParseException {
        String dateTime1 = "2020-01-13T16:00:00.000 EDT";
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = format1.parse(dateTime1);
        String result1 = defaultFormat1.format(time1);
        System.out.println(result1);

        String dateTime2 = "2020-01-13T16:00:00.000 UTC";
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z");
        SimpleDateFormat defaultFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time2 = format2.parse(dateTime2);
        String result2 = defaultFormat2.format(time2);
        System.out.println(result2);

        Date one = new Date();
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        String result3 = format3.format(time2);
        System.out.println(result3);
    }

    @Test
    public void threadPoolTest() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(31);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>()
                , new CustomerThreadFactory(), (r, executor) -> {
            if (!executor.isShutdown()) {
                new Thread(r, "other thread").start();
            }
        });
        for (long i = 0; i < 310_000_000; i++) {
            long finalI = i;
            /*if (i == 15) {
                threadPool.execute(() -> {
                    throw new RuntimeException(Thread.currentThread().getName() + ":" + "Error" + finalI);
                });
            }*/
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + finalI);
                count.countDown();
            });
        }
        count.await();
        System.out.println("CountDownLatch is over");
        while (!threadPool.isShutdown()) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("threadPool is over?");
        }
        System.out.println("threadPool is over.");
    }
}

/**
 * 自定义线程创建工厂类
 */
class CustomerThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    CustomerThreadFactory() {
        namePrefix = "myPool-" +
                poolNumber.getAndIncrement() +
                "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r,
                namePrefix + threadNumber.getAndIncrement());
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
