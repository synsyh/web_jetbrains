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

/**
 * Created by sunning on 2016/11/29.
 */
public class QstnAdder {
    private String separator = File.separator;
    public String id;

    File file = null;
    XMLWriter writer = null; //  声明写XML的对象
    SAXReader reader = new SAXReader();
    Document doc = null;
    Element root = null;

    String filePath = separator + "Users" + separator +
            "sunning" + separator + "Desktop" + separator + "QA" + separator;

    //TODO:select

    public QstnAdder(String content, String[] option, String[] answer) throws IOException, DocumentException {
        start();
        this.addQstn(content, option, answer);
    }

    //TODO:fillblank

    public QstnAdder(String content, boolean isFill) throws IOException, DocumentException {
        start();
        String tmp = "\\(____\\)";

        String[] fillcontent = content.split(tmp);
        this.addQstn(fillcontent);

    }

    //TODO:judge
    public QstnAdder(String content, String[] answer) throws IOException, DocumentException {
        start();

        this.addQstn(content, answer);
    }

    //TODO:descrip
    public QstnAdder(String content) throws IOException, DocumentException {
        start();

        this.addQstn(content);
    }

    //TODO:add descrip

    public void addQstn(String content) throws IOException, DocumentException {
        Element question = root.addElement("question");
        question.addAttribute("type", "describe");

        addNode(question, "id", String.valueOf(id));
        addNode(question, "content", content);
        end();
    }

    //TODO:add judge

    public void addQstn(String content, String[] answer) throws DocumentException, IOException {
        Element question = root.addElement("question");
        question.addAttribute("type", "judge");

        addNode(question, "id", String.valueOf(id));
        addNode(question, "content", content);
        for (int i = 0; i < answer.length; i++) {
            addNode(question, "answer", answer[i]);
        }
        end();
    }

    //TODO:add fillblank
    public void addQstn(String[] content) throws DocumentException, IOException {
        Element question = root.addElement("question");
        question.addAttribute("type", "fill");

        addNode(question, "id", String.valueOf(id));
        addNode(question, "content", content[0].trim());

        for (int i = 1; i < content.length; i++) {
            addNode(question, "blank", "");
            addNode(question, "content", content[i].trim());
        }
        end();
    }

    //TODO:add select
    public void addQstn(String content, String[] option, String[] answer) throws DocumentException, IOException {
        Element question = root.addElement("question");
        question.addAttribute("type", "select");

        addNode(question, "id", String.valueOf(id));
        addNode(question, "content", content);
        addNode(question, "option", option[0], "num", "A");
        addNode(question, "option", option[1], "num", "B");
        addNode(question, "option", option[2], "num", "C");
        addNode(question, "option", option[3], "num", "D");

        for (int i = 0; i < answer.length; i++) {
            addNode(question, "answer", answer[i]);

        }
        end();
    }

    //TODO:添加根节点qamaintain

    public void start() throws IOException, DocumentException {
        filePath += "test.xml";
        file = new File(filePath);
        SAXReader reader = new SAXReader();
        this.doc = reader.read(file);
        root = doc.getRootElement();
        Element count = root.element("count");
        this.id = count.getText();
//        document.addElement("qamaitain");
//        writer = new XMLWriter(new FileWriter(file), this.setformat());
//        writer.write(document);
//        writer.close();
    }

    public void end() throws IOException {
        root.element("count").setText(String.valueOf(Integer.valueOf(id) + 1));
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
