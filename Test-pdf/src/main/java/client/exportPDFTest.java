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

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        String a = "注1：1949 年，新中国成立； 1978 年，改革开放政策实施； 2015 年，\n" +
                "一带一路成立。新中国成立初期，国内经济和生产力发展并不理想，\n" +
                "国民生活水平也较低。彼时国内领导人力求改变、大胆尝试， 开始在\n" +
                "国内农村实行改革，在广东和福建两省的对外经济活动中实行特殊政\n" +
                "策。自此，中国经济飞速发展，到 2010 年，中国已超越日本成为世\n" +
                "界第二大经济体。 2015 年， 国家决定与相关 49 个国家开展“一带一\n" +
                "路”合作，中国经济再一次找到了新的增长点。\n" +
                "新中国初期， 中国经济发展一直坎坷，人民生活水平几乎原地踏\n" +
                "步，大部分人还远落后于贫困线。 如今，中国经济早已举足轻重，辐\n" +
                "射全球，全面脱贫攻坚战也将在 2020 收官。短短七十年间，中国从\n" +
                "成立时的一贫如洗和四面楚歌，到如今的崛起典范和“天下谁人不识\n" +
                "君”。翻天覆地的变化不仅是为国家繁荣昌盛、为民众幸福生活带来\n" +
                "的本质的改变，也成了其他国家的多领域合作伙伴和崛起榜样。\n" +
                "新的中国\n" +
                "我国从成立之初，就明确了军事和科技的地位，为维护国家主权和保障国家利益提供了最有力的武器。 1964 年，中国第一颗原子弹\n" +
                "爆炸成功，加强了我国的国防力量，扩大了我国在世界上的话语权，\n" +
                "打破了原有的核垄断，间接为维护世界和平贡献了一份力量。 [1]\n" +
                "1955 年，亚非会议召开。新中国的成立以及反帝任务的完成受\n" +
                "到各国瞩目。二战后，亚非地区民族解放运动受到新中国成立的刺激\n" +
                "空前高涨，中国也在会议中分享了自己的运动经验。然而仅仅 5 年后，\n" +
                "我国刚进入发展阶段时期，老大哥前苏联就跟我们决裂。在冷战背景\n" +
                "下，又失去先进技术支援的中国，面对几乎全面封锁的情况下，生生\n" +
                "地在自家田地里种出了“蘑菇”。中国凭借这一颗原子弹打破了帝国\n" +
                "主义的核垄断和核讹诈， 开启了由弱变强的篇章，也让其他第三世界\n" +
                "国家重拾信心。\n" +
                "自此，中国的发展进程受到了广泛关注，尤其是周边亟待寻求发\n" +
                "展方向的第三世界国家，甚至是模仿中国的政策。我国的地位从此开\n" +
                "始提高，为第三世界国家的发展提供了指导意义。\n" +
                "然而，中国的经济发展缺并不理想。“大跃进”不仅没有带来工\n" +
                "业上的预期进步，甚至使这个农业大国明显减产，带来了严重的饥荒。\n" +
                "直到 1978 年，中央下定决心，将家庭联产承包责任制正式写入文件，\n" +
                "对内改革正式开始；次年正式批准广东、福建两省在对外经济活动中\n" +
                "实行特殊政策。中国开始了具有探索性的变革，将马克思主义普遍真\n" +
                "理与中国具体实际相结合，在生产力和经济的发展上取得了决定性的\n" +
                "突破，也开始走上了自己的道路。 1982 年，邓小平在十二大开幕词\n" +
                "中明确提出中国要建设中国特色的社会主义。改革开放\n" +
                "改革开放，将国外资本和商品引入了国内，同时也将更多中国非\n" +
                "特色商品出口到世界范围内。 在初期，我们用非常优惠的政策吸引外\n" +
                "资入驻，同时也采用商品低价销售和廉价劳动力积极创汇，此时的中\n" +
                "国在世界经济舞台上刚刚崭露头角。中国的武器出口也因此开始由原\n" +
                "来的无偿援助改为有偿的交易。 1979 年，歼 6 出口到埃及成为了中\n" +
                "国军售的第一单。 [2]此次交易，买卖双方在交易前都在寻找有意向的\n" +
                "交易对象，平等公平的完成了此次交易，也让其他国家了解了中国的\n" +
                "军事实力。 而后两伊战争的爆发，为中国带来了巨大的军武订单，仅\n" +
                "坦克就超过 3000 辆，价值达十几亿。中国坦克凭借操作简单和价格\n" +
                "便宜，成为了交战双方的首选。规模大， 产量高，如此军事力量震慑\n" +
                "了周边一些宵小国家，也为国家的发展谋得了更多的安稳时间。\n" +
                "虽然此时的中国不再是当初的一无所有，军工体系和工业体系\n" +
                "也逐渐完善，但是面对两大巨头毫无抵抗能力。苏联解体后，两极化\n" +
                "格局演变为一超多强，美国也愈发肆无忌惮。 1999 年，美国使用三\n" +
                "枚精确制导导弹精准击中了中华人民共和国驻南斯拉夫联盟大使馆。\n" +
                "赤裸裸的挑衅激起了国内民愤，国内却只是发表谴责，因为不敢打也\n" +
                "不能打。直到 2 年后的 911 事件发生，美国才把炮口调离了中国，中\n" +
                "国再一次绝处逢生。\n" +
                "新时代2017 年，十九大报告中提出了中国发展新的历史方位——中国\n" +
                "特色社会主义进入了新时代。回顾二十年的快车道发展，中国的各行\n" +
                "各业都取得了飞跃式的发展。在今天，我们的生活有了概念级的变化。\n" +
                "交通便利、 无线通讯、 快捷办公、 智能手机， 完全颠覆了人们的生活\n" +
                "习惯和行为方式。社会也在进步，各种规则逐渐完善，民众监督力度\n" +
                "逐渐提升，法律法规不断修订增设，社会秩序不断提升。国家底蕴和\n" +
                "能力快速提升，军工水平稳定提升，军武出口多个国家，顶尖军事力\n" +
                "量逐渐完善。近些天，航母的命名终于落下帷幕，确认山东舰。我国\n" +
                "在 80 年代就已经认识了航母，但受制于国内经济发展，一直难以立\n" +
                "项。而今天， 40 年的时间里， 7 年前，辽宁舰解决了“有没有”的问\n" +
                "题；今年，山东舰解决了“能不能造”的问题。国内经济和军事上的\n" +
                "高速和高层次的发展有目共睹，我国在国际上也拥有了更多的话语\n" +
                "权。\n" +
                "2013 年，一带一路正式提出。中国经济开始软着陆，国内商品\n" +
                "得以大规模、有计划的外销，解决了产能严重过剩的问题。同时开始\n" +
                "供给侧改革，优化产业结构，对一带一路沿线国家进行投资。中国经\n" +
                "济在全球经济低迷的情况下找到了新的经济增长点，也为沿线的亚非\n" +
                "国家带来了宝贵的基建投资和工作岗位，将我国物美价廉的商品销往\n" +
                "欧洲。\n" +
                "然而，好景不长，中美贸易摩擦演变为争端并在 2018 年进入了\n" +
                "实质性的阶段。当美国的对华 2000 亿制裁清单公布后，中国在短时\n" +
                "间内作出反击，公布了针对美国的 600 亿清单。中国作为大国，今天已经对各种争端或突发事件有了预案和强有力的解决方案，愈发成熟\n" +
                "和稳健。直至今天，中美贸易战并未再激进一步，之前的一些制裁比\n" +
                "如封锁华为似乎也是一拖再拖。各种利益交织在一起，中美双方的贸\n" +
                "易争端影响在全球化的背景下必定会被放大和扩散，全球经济经不起\n" +
                "折腾，中美双方也在慎重对待。 中国已经不再是砧板上的鱼肉，而是\n" +
                "世界上的头部玩家。\n" +
                "国家的整体力量得到显著提升， 每个民众的情况也没有忽视。早\n" +
                "在 1986 年，国务院扶贫开发领导小组就已成立。经过大规模有针对\n" +
                "性扶贫计划（1986-1993）、“八七扶贫攻坚计划”（1994-2000）和农\n" +
                "村扶贫开发纲要三个阶段，我国脱贫攻坚工作力度不断加大，开始进\n" +
                "入收获阶段。 2011 年，国家颁布了最后的十年计划纲领《中国农村\n" +
                "扶贫开发纲要（2011-2020 年）》。 [3]近几年，扶贫工作以各种方式开\n" +
                "展。不论是现在机关干部精准扶贫某个区域，还是打通运输开展扶贫\n" +
                "电商帮助农牧产品销售，扶贫从政务机构的工作内容中走出到全民可\n" +
                "参与的项目。每一份扶贫产品的售出，真正的帮助了贫困户自力更生。\n" +
                "另外还有依托于国家建档立卡大数据库的“社会扶贫”软件，不同于\n" +
                "某滴筹的混杂求助，真实的贫困户和国家干部的审核让每一分钱帮扶\n" +
                "到正确的项目， 不消费和浪费捐赠人的爱心。 2020，我国决胜脱贫攻\n" +
                "坚。\n" +
                "奋斗新时代\n" +
                "七十年， 中国从任人欺凌发展到今天的举足轻重；从一无所有到自给自足。 90 后的我，见证了后 20 年的飞速发展，见证了国家的崛\n" +
                "起和民族复兴。 国家在初级阶段已经发展许久， 我们现在也不再为吃\n" +
                "穿发愁， 但我们不能懈怠， 更要保持不懈奋斗和自强不息的精神。 即\n" +
                "使是作为一个普通高校生， 我们也应当努力， 认真学习知识和技能，\n" +
                "不应在娱乐上浪费大量时间。 新的时代已经来临， 我们应不忘初心，\n" +
                "牢记使命， 踏步向前。";
        cell = new Cell(1, 4).add(new Paragraph(a).setFont(sysFont));
        table.addCell(cell);
        cell = new Cell(1, 4).add(new Paragraph("卧槽泥马勒戈壁").setFont(sysFont));
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
