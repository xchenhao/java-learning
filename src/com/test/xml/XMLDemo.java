package com.test.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XMLDemo {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        saxParseXML();
    }

    public static void saxParseXML() throws ParserConfigurationException, SAXException, IOException {
        // 1. 创建一个 SAX 解析器工厂对象
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        // 2. 通过工厂对象创建 SAX 解析器
        SAXParser saxParser = saxParserFactory.newSAXParser();
        // 3. 创建一个数据处理器（需要我们自己来编写）
        PersonHandler personHandler = new PersonHandler();
        // 4. 开始解析
        InputStream is = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("com/test/xml/person.xml");
        saxParser.parse(is, personHandler);
        List<Person> persons = personHandler.getPersons();
        for (Person p: persons) {
            System.out.println(p);
        }


    }



}
