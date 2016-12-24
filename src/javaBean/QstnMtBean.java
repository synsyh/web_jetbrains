package javaBean;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;

import static java.io.File.separator;

/**
 * Created by sunning on 2016/12/18.
 */
public class QstnMtBean {
    Document examQstnDoc = null;

    String allExamFilePath = separator + "Users" + separator +
            "sunning" + separator + "Desktop" + separator + "QA" + separator + "test.xml";

    public QstnMtBean() {

    }

    public Document getExamQstnDoc() throws DocumentException {
        File file;
        SAXReader reader;

        file=new File(allExamFilePath);
        if (file.exists()) {
            reader = new SAXReader();
            examQstnDoc = reader.read(file);
        } else {
            System.out.println("file is not exists");
        }
        return examQstnDoc;
    }

}
