package javaBean;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunning on 2016/12/3.
 */
public class SetPaperBean {
    private int selallmark, selmark;
    private int filallmark, filmark;
    private int desallmark, desmark;
    private int judallmark, judmark;

    private String classNum;
    double selnum, filnum, desnum, judnum;
    int random;
    int[] allRandom = null;

    private String separator = File.separator;
    public String id;

    //TODO:策略文件变量声明
    private String tacticsFilePath;
    private File tacticsFile;
    Document tacticsDoc;
    Element tacticsRoot;

    XMLWriter writer = null; //  声明写XML的对象
    SAXReader reader = new SAXReader();
    File qstnFile = null;
    File examFile = null;

    Document allQstnDoc = null;
    Document examQstnDoc = null;
    Element allQstnRoot = null;
    Element examQstnRoot = null;

    String allQstnFilePath = separator + "Users" + separator +
            "sunning" + separator + "Desktop" + separator + "QA" + separator;
    String examQstnFilePath = separator + "Users" + separator +
            "sunning" + separator + "Desktop" + separator + "QA" + separator + "exam" + separator;

    public SetPaperBean() {
    }

    public SetPaperBean(String classNum, String selallmark, String selmark, String filallmark, String filmark, String desallmark, String desmark, String judallmark, String judmark) {
        this.classNum = classNum;
        this.selallmark = Integer.valueOf(selallmark);
        this.selmark = Integer.valueOf(selmark);
        this.filallmark = Integer.valueOf(filallmark);
        this.filmark = Integer.valueOf(filmark);
        this.desallmark = Integer.valueOf(desallmark);
        this.desmark = Integer.valueOf(desmark);
        this.judallmark = Integer.valueOf(judallmark);
        this.judmark = Integer.valueOf(judmark);
        this.selnum = this.selallmark / this.selmark;
        this.filnum = this.filallmark / this.filmark;
        this.desnum = this.desallmark / this.desmark;
        this.judnum = this.judallmark / this.judmark;
    }

    public void saveTactics() throws DocumentException, IOException {
        tacticsFilePath = "/Users/sunning/Documents/web_jetbrains/web/" + "cls" + classNum + ".xml";
        tacticsFile = new File(tacticsFilePath);
        if (tacticsFile.exists()) {
            reader = new SAXReader();
            tacticsDoc = reader.read(tacticsFile);
            tacticsRoot = tacticsDoc.getRootElement();
        } else {
            tacticsDoc = DocumentHelper.createDocument();
            tacticsRoot = tacticsDoc.addElement("class");
            addNode(tacticsRoot, "classnum", classNum);
            addNode(tacticsRoot, "selallmark", String.valueOf(selallmark));
            addNode(tacticsRoot, "selmark", String.valueOf(selmark));
            addNode(tacticsRoot, "filallmark", String.valueOf(filallmark));
            addNode(tacticsRoot, "filmark", String.valueOf(filmark));
            addNode(tacticsRoot, "judallmark", String.valueOf(judallmark));
            addNode(tacticsRoot, "judmark", String.valueOf(judmark));
            addNode(tacticsRoot, "desallmark", String.valueOf(desallmark));
            addNode(tacticsRoot, "desmark", String.valueOf(desmark));
        }
        end(tacticsDoc, tacticsFile);
    }

    public void readTactics(String url) throws DocumentException {
        tacticsFile = new File(url);
        if (tacticsFile.exists()) {
            SAXReader reader = new SAXReader();
            tacticsDoc = reader.read(tacticsFile);
            tacticsRoot = tacticsDoc.getRootElement();
            this.selallmark = Integer.valueOf(tacticsRoot.element("selallmark").getText());
            this.selmark = Integer.valueOf(tacticsRoot.element("selmark").getText());
            this.filallmark = Integer.valueOf(tacticsRoot.element("filallmark").getText());
            this.filmark = Integer.valueOf(tacticsRoot.element("filmark").getText());
            this.judallmark = Integer.valueOf(tacticsRoot.element("judallmark").getText());
            this.judmark = Integer.valueOf(tacticsRoot.element("judmark").getText());
            this.desallmark = Integer.valueOf(tacticsRoot.element("desallmark").getText());
            this.desmark = Integer.valueOf(tacticsRoot.element("desmark").getText());
            this.selnum = this.selallmark / this.selmark;
            this.filnum = this.filallmark / this.filmark;
            this.desnum = this.desallmark / this.desmark;
            this.judnum = this.judallmark / this.judmark;
        } else {
            System.out.println("file is not exists");
        }
    }

    public void setPaper(String classNum, String id) throws DocumentException, IOException {
        examQstnFilePath = "/Users/sunning/Documents/web_jetbrains/web/" + classNum + id + ".xml";
        examFile = new File(examQstnFilePath);
        if (examFile.exists()) {
            SAXReader reader = new SAXReader();
            examQstnDoc = reader.read(examFile);
            examQstnRoot = examQstnDoc.getRootElement();
        } else {
            examQstnDoc = DocumentHelper.createDocument();
            examQstnRoot = examQstnDoc.addElement("qamaitain");
            allQstnFilePath += "test.xml";
            qstnFile = new File(allQstnFilePath);
            SAXReader reader = new SAXReader();
            this.allQstnDoc = reader.read(qstnFile);
//        System.out.println(allQstnDoc.getText());
            allQstnRoot = allQstnDoc.getRootElement();

            System.out.println(examQstnRoot.getName());

            List<Element> allSelQstn = null, allFilQstn = null, allJudQstn = null, allDesQstn = null;
            List<Element> allQstn = allQstnRoot.elements("question");
            allSelQstn = new ArrayList<>();
            allDesQstn = new ArrayList<>();
            allFilQstn = new ArrayList<>();
            allJudQstn = new ArrayList<>();

            for (Element ele : allQstn) {
                String type = ele.attributeValue("type");
                switch (type) {
                    case "select":
                        allSelQstn.add(ele);
                        break;
                    case "fill":
                        allFilQstn.add(ele);
                        break;
                    case "judge":
                        allJudQstn.add(ele);
                        break;
                    case "describe":
                        allDesQstn.add(ele);
                        break;
                    default:
                        break;
                }
            }
            examSel(allSelQstn, allQstn);
            examFil(allFilQstn, allQstn);
            examJud(allJudQstn, allQstn);
            examDes(allDesQstn, allQstn);

            end(examQstnDoc, examFile);
            end(allQstnDoc, qstnFile);
        }
    }

    public Document getFile() throws DocumentException {
//        System.out.println(tmp);
        return examQstnDoc;
    }

    public void examSel(List<Element> allSelQstn, List<Element> allQstn) {
        for (int i = 0; i < selnum; i++) {
            allRandom = new int[(int) selnum];
            boolean flag = true;
            random = (int) Math.round(Math.random() * (allSelQstn.size() - 1));
            for (int j = 0; j < selnum; j++) {
                if (random == allRandom[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(random);
                allRandom[i] = random;
            }
        }
        for (int i : allRandom) {
            Element ele = allSelQstn.get(i);
            Element eleClone = (Element) ele.clone();
            Element eleMark = eleClone.addElement("mark");
            eleMark.setText(String.valueOf(selmark));
            examQstnRoot.add(eleClone);
        }
    }

    public void examJud(List<Element> allJudQstn, List<Element> allQstn) {
        for (int i = 0; i < judnum; i++) {
            allRandom = new int[(int) judnum];
            boolean flag = true;
            random = (int) (Math.random() * (allJudQstn.size() - 1));
            for (int j = 0; j < judnum; j++) {
                if (random == allRandom[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(random);
                allRandom[i] = random;
            }
        }
        for (int i : allRandom) {
            Element ele = allJudQstn.get(i);
            Element eleClone = (Element) ele.clone();
            Element eleMark = eleClone.addElement("mark");
            eleMark.setText(String.valueOf(judmark));
            examQstnRoot.add(eleClone);
        }
    }

    public void examFil(List<Element> allFilQstn, List<Element> allQstn) {
        for (int i = 0; i < filnum; i++) {
            allRandom = new int[(int) filnum];
            boolean flag = true;
            random = (int) (Math.random() * (allFilQstn.size() - 1));
            for (int j = 0; j < filnum; j++) {
                if (random == allRandom[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(random);
                allRandom[i] = random;
            }
        }
        for (int i : allRandom) {
            Element ele = allFilQstn.get(i);
            Element eleClone = (Element) ele.clone();
            Element eleMark = eleClone.addElement("mark");
            eleMark.setText(String.valueOf(filmark));
            examQstnRoot.add(eleClone);
        }
    }

    public void examDes(List<Element> allDesQstn, List<Element> allQstn) {
        for (int i = 0; i < desnum; i++) {
            allRandom = new int[(int) desnum];
            boolean flag = true;
            random = (int) (Math.random() * (allDesQstn.size() - 1));
            for (int j = 0; j < desnum; j++) {
                if (random == allRandom[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(random);
                allRandom[i] = random;
            }
        }
        for (int i : allRandom) {
            Element ele = allDesQstn.get(i);
            Element eleClone = (Element) ele.clone();
            Element eleMark = eleClone.addElement("mark");
            eleMark.setText(String.valueOf(desmark));
            examQstnRoot.add(eleClone);
        }
    }

    //TODO:添加修改xml文件
    public Element start(String filePath, String rootName) throws IOException, DocumentException {
        File file;
        SAXReader reader;
        Document doc;
        Element root;

        file = new File(filePath);
        if (file.exists()) {
            reader = new SAXReader();
            doc = reader.read(file);
            root = doc.getRootElement();
        } else {
            doc = DocumentHelper.createDocument();
            root = doc.addElement(rootName);
        }
        return root;
//        examQstnFilePath += className + ".xml";
//        examFile = new File(examQstnFilePath);
//        if (examFile.exists()) {
//            SAXReader reader = new SAXReader();
//            examQstnDoc = reader.read(examFile);
//            examQstnRoot = examQstnDoc.getRootElement();
//        } else {
//            examQstnDoc = DocumentHelper.createDocument();
//            examQstnRoot = examQstnDoc.addElement("qamaintain");
//        }
//        allQstnFilePath += "test.xml";
//        qstnFile = new File(allQstnFilePath);
//        SAXReader reader = new SAXReader();
//        this.allQstnDoc = reader.read(qstnFile);
//        allQstnRoot = allQstnDoc.getRootElement();
    }

    public void end(Document doc, File file) throws IOException {
        writer = new XMLWriter(new FileWriter(file), this.setformat());
        writer.write(doc);
        writer.close();
    }

    //TODO:添加节点和格式化操作

    public void addNode(Element node, String nodeName, String text) {
        Element newNode = node.addElement(nodeName);
        newNode.setText(text);
    }

    public void addNode(Element node, String nodeName, String text, String attribute, String value) {
        Element newNode = node.addElement(nodeName);
        newNode.setText(text);
        newNode.addAttribute(attribute, value);
    }

    public OutputFormat setformat() {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("gb2312");
        return format;
    }
}
