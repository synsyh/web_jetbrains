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
 * Created by sunning on 2016/12/19.
 */
public class AddAswBean {
    private String classNum;
    private String id;
    private ArrayList<String[]> resultlist;
    XMLWriter writer = null;
    File file;
    SAXReader reader;
    Document doc;
    private String filePath;
    Element root;

    public AddAswBean(String classNum, String id, ArrayList<String[]> resultlist) {
        this.classNum = classNum;
        this.id = id;
        this.resultlist = resultlist;
        filePath = "/Users/sunning/Documents/web_jetbrains/web/" + classNum + id + ".xml";
        try {
            addAsw();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void addAsw() throws IOException, DocumentException {
        root = start(filePath, "qamaitain");
        List<Element> questions = root.elements("question");
        Element question;
        String i;
        for (String[] tmpString : resultlist) {
            i = tmpString[0];
            for (Element tmp : questions) {
                if (tmp.element("id").getText().equals(i)) {
                    tmp.element("stu_answer").setText(tmpString[1]);
                }
            }
        }
        writer = new XMLWriter(new FileWriter(file), this.setformat());
        writer.write(doc);
        writer.close();
    }

    public Element start(String filePath, String rootName) throws IOException, DocumentException {
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
    }

    public static void main(String[] args) {
        ArrayList<String[]> resultlist = null;
        AddAswBean addAswBean = new AddAswBean("2014001", "10001", resultlist);
        try {
            addAswBean.addAsw();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public OutputFormat setformat() {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("gb2312");
        return format;
    }

}
