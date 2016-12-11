package Exam;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by MISAKA on 2016/11/26.
 */
public class ExamList {
    private ArrayList<String> examlist = new ArrayList<>();
    private ArrayList<String> teachername = new ArrayList<>();
    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> course = new ArrayList<>();
    private String separator = File.separator;
    String filePath = "C:\\Users\\MISAKA\\Desktop\\LiHao\\web_jetbrains\\src\\Exam\\exam.xml";
    public ExamList(){
    }
    public void addExam(){

    }
    public void deleteExam(){

    }
    public ArrayList<String> getExamlist () throws DocumentException {
        Document doc  = read_Document();
        Traversing(doc.getRootElement(),"name",examlist);
        return examlist;
    }
    public ArrayList<String> getTeachername() throws DocumentException {
        Document doc  = read_Document();
        Traversing(doc.getRootElement(),"name",teachername);
        return teachername;
    }
    public ArrayList<String> getTime () throws DocumentException {
        Document doc  = read_Document();
        Traversing(doc.getRootElement(),"name",time);
        return time;
    }
    public ArrayList<String> getCourse () throws DocumentException {
        Document doc  = read_Document();
        Traversing(doc.getRootElement(),"name",course);
        return course;
    }
    private Document read_Document() throws DocumentException {
        File file  =   new  File(filePath);
        SAXReader reader = new SAXReader();
        Document doc  =  reader.read(file);
        return doc;
    }
    public ArrayList<String> Traversing(Element root,String element_name, ArrayList<String> temp) {
        for (Iterator i = root.elementIterator("exam"); i.hasNext(); ) {//这里不是从根节点开始的，而是根节点下的第一个节点
            Element element = (Element) i.next();
            // do something
            temp.add(element.elementText(element_name));
        }
        return temp;
    }

}
