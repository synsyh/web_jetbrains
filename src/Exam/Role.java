package Exam;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Role {
    private String title;
    private String journalistName;
    private String journalistID;
    private String editorName;
    private String EditorID;
    private String ChiefID = "10111";
    private String ChiefName = "Yangliu";

    private String time;
    private String content;
    //private String ID;

    public void setTitle(String title) {
        this.title = title;
    }
    //set Character informations
    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }
    public void setJournalistName(String journalistName) {
        this.journalistName = journalistName;
    }
    public void setChiefname(String ChiefName) {
        this.ChiefName = ChiefName;
    }
    //set ID
    public void setChiefID(String ChiefID) {
        this.ChiefID = ChiefID;
    }
    public void setEditorID(String EditorID) {
        this.EditorID = EditorID;
    }
    public void setJournalistID(String JID) {
        this.journalistID = JID;
    }
    //set content
    public void setContent(String content) {
        this.content = content;
    }




    XMLWriter writer  =   null ; //  声明写XML的对象
    SAXReader reader  =   new  SAXReader();

    //Get ID
    public String getJournalistID(){return journalistID;}
    public String getEditorID(){return EditorID;}
    public String getChiefID(){return ChiefID;}
    //Get name
    public String getTitle() {
        return title;
    }
    public String getEditorName(){return editorName;}
    public String getJournalistName() {
        return journalistName;
    }
    public String getChiefName(){return ChiefName;}
    public String getContent() {
        return content;
    }


    //Create document
    public void createXml(String news_type,String news_name) throws IOException
    {
        String separator = File.separator;
        String filePath  =   separator+"home"+separator+
                "sunning"+separator+"桌面"+separator+"News"+separator+news_type
                +separator+news_name;
        File file  =   new  File(filePath);
        Document document  =  DocumentHelper.createDocument();
        Element root  =  document.addElement("Root");
        addNode(root,"type",news_type);
        addNode(root,"Characters"," ");
        addNode(root,"News"," ");
        addNode(root,"History"," ");
        Element History = root.element("History");
        Element Characters = root.element("Characters");
        Element News = root.element("News");
        addNode(News,"Title",news_name);
        addNode(Characters,"Journalist"," ");
        addNode(Characters,"Editor"," ");
        addNode(Characters,"Chief"," ");
        addNode(News,"content"," ");
        addNode(News,"Time"," ");
        addNode(History,"Journalist"," ");
        addNode(History,"Editor"," ");
        addNode(History,"Chief"," ");

        writer  =   new  XMLWriter( new  FileWriter(file), this.setformat());
        writer.write(document);
        writer.close();
    }
    //To judge: whether the document is exist
    public boolean exists(String news_type,String news_name)
    {
        String separator = File.separator;
        String filePath  =   separator+"home"+separator+
                "huozongsheng"+separator+"Desktop"+separator+"News"+separator+news_type
                +separator+news_name;
        File file  =   new  File(filePath);
        if(file.exists()) return true;
        else
            return false;
    }

    public String getTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(new Date().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = dateFormat.format(c.getTime());
        return time;
    }


    public OutputFormat setformat()
    {
        OutputFormat format  =  OutputFormat.createPrettyPrint();
        format.setEncoding("gb2312");
        return format;
    }

    public void Agree (String news_type,String news_name,String YN,String charac) throws IOException, DocumentException {
        String separator = File.separator;
        String filePath  =   separator+"home"+separator+
                "sunning"+separator+"桌面"+separator+"News"+separator+news_type
                +separator+news_name;


        File file  =   new  File(filePath);
        SAXReader reader = new SAXReader();
        Document doc  =  reader.read(file);

        Element Root = doc.getRootElement();
        Element Characters = Root.element("Characters");
        //Element Editor = Characters.element(charac);
        //editAttribute(Characters,"Journalist",YN);
        editAttribute(Characters,charac,YN);

        writer  =   new  XMLWriter( new  FileWriter(file), this.setformat());
        writer.write(doc);
        writer.close();
    }
    public void addCharacters (String news_type,String news_name) throws DocumentException, IOException {
        String separator = File.separator;
        String filePath  =   separator+"home"+separator+
                "sunning"+separator+"桌面"+separator+"News"+separator+news_type
                +separator+news_name;


        File file  =   new  File(filePath);
        SAXReader reader = new SAXReader();
        Document doc  =  reader.read(file);

        Element Root = doc.getRootElement();
        Element Characters = Root.element("Characters");
        Element Journalist = Characters.element("Journalist");
        Element Editor = Characters.element("Editor");
        Element Chief = Characters.element("Chief");

        // add information
        addNode(Journalist,"name",this.getJournalistName());
        addNode(Editor,"name",this.getEditorName());
        addNode(Chief,"name",this.getChiefName());
        addNode(Journalist,"ID",this.getJournalistID());
        addNode(Editor,"ID",this.getEditorID());
        addNode(Chief,"ID",this.getChiefID());
        // write to document
        writer  =   new  XMLWriter( new  FileWriter(file), this.setformat());
        writer.write(doc);
        writer.close();


    }

    public void addNode (Element node,String nodeName,String text)
    {
        Element newNode = node.addElement(nodeName);
        newNode.setText(text);
    }
    public void settxt (Element node,String text)
    {
        node.setText(text);
    }

    public void editAttribute(Element root,String nodeName,String YN)
    {
        Element node = root.element(nodeName);
        node.addAttribute("Agree",YN);
    }
    public void write_news(String news_type,String news_name,String news_content) throws DocumentException, IOException {
        String separator = File.separator;
        String filePath  =   separator+"home"+separator+
                "sunning"+separator+"桌面"+separator+"News"+separator+news_type
                +separator+news_name;


        File file  =   new  File(filePath);
        SAXReader reader = new SAXReader();
        Document doc  =  reader.read(file);
        // set news content
        Element Root = doc.getRootElement();
        Element News = Root.element("News");
        Element content = News.element("content");
        Element Time = News.element("Time");
        settxt(content,news_content);
        settxt(Time,getTime());
        // write to document
        writer  =   new  XMLWriter( new  FileWriter(file), this.setformat());
        writer.write(doc);
        writer.close();
    }
    public void History(String news_type,String news_name,String chrac,String news_history) throws DocumentException, IOException {
        String separator = File.separator;
        String filePath  =   separator+"home"+separator+
                "sunning"+separator+"桌面"+separator+"News"+separator+news_type
                +separator+news_name;


        File file  =   new  File(filePath);
        SAXReader reader = new SAXReader();
        Document doc  =  reader.read(file);
        // set news content
        Element Root = doc.getRootElement();
        Element History = Root.element("History");
        Element temp = History.element(chrac);

        settxt(temp,news_history);
        temp.addAttribute("Time",this.getTime());

        // write to document
        writer  =   new  XMLWriter( new  FileWriter(file), this.setformat());
        writer.write(doc);
        writer.close();
    }


}