package javaBean;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by sunning on 2016/12/6.
 */
public class QstnDelBean {
    private String separator = File.separator;
    public String id;

    private File file = null;
    private XMLWriter writer = null; //  声明写XML的对象
    private SAXReader reader = new SAXReader();
    private Document doc = null;
    private Element root = null;

    String filePath = "/Users/sunning/Desktop/QA/";

    public void start() throws IOException, DocumentException {
        filePath += "test.xml";
        file = new File(filePath);
        SAXReader reader = new SAXReader();
        this.doc = reader.read(file);
        root = doc.getRootElement();
    }

    public QstnDelBean(String id) throws IOException, DocumentException {
        start();
        qstnDel(id);
        end();
    }

    public void qstnDel(String id) {
        int qstnID = Integer.valueOf(id);
        List<Element> qstn = root.elements("question");
        qstn.remove(qstn.get(qstnID));
    }

    public void end() throws IOException, DocumentException {
        writer = new XMLWriter(new FileWriter(file), this.setformat());
        writer.write(doc);
        writer.close();
        writer.flush();
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
