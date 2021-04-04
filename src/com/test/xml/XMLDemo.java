package com.test.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XMLDemo {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        // saxParseXML();
        domParseXML();
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

    /**
     * DOM 解析 XML
     * 1. 基于树型结构，通过解析一次性把文档加载到内存中，所以会比较占用内存，可以随机访问
     *    更加灵活，更适合在 Web 开发中使用
     *
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void domParseXML() throws ParserConfigurationException, IOException, SAXException {
        // 1. 创建一个 DOM 解析器工厂对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 2. 通过工厂对象创建解析器对象
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        // 3. 解析文档
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/test/xml/person.xml");
        Document doc = documentBuilder.parse(is);  // 此代码完成后，整个 XML 文档被加载到内存中，以树状形式存储
       // 4. 从内存中读取数据
        NodeList personNodeList = doc.getElementsByTagName("person");  // 获取节点名称为 person 的所有节点，返回节点集合
        ArrayList<Person> persons = new ArrayList<>();
        Person p = null;
        for (int i = 0; i < personNodeList.getLength(); i++) {
            Node personNode = personNodeList.item(i);
            p = new Person();
            String personid = personNode.getAttributes().getNamedItem("personid").getNodeValue();  // 获取节点的属性值
            p.setPersonId(personid);
            NodeList childNodes = personNode.getChildNodes(); // 获取当前节点的所有子节点
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node item = childNodes.item(j);
                String nodeName = item.getNodeName();
                if ("name".equals(nodeName)) {
                    p.setName(item.getFirstChild().getNodeValue());
                } else if ("address".equals(nodeName)) {
                    p.setAddress(item.getFirstChild().getNodeValue());
                } else if ("tel".equals(nodeName)) {
                    p.setTel(item.getFirstChild().getNodeValue());
                } else if ("fax".equals(nodeName)) {
                    p.setFax(item.getFirstChild().getNodeValue());
                } else if ("email".equals(nodeName)) {
                    p.setEmail(item.getFirstChild().getNodeValue());
                }
            }
            persons.add(p);
        }

        System.out.println("结果");
        System.out.println(Arrays.toString(persons.toArray()));
    }



}
