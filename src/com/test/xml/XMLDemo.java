package com.test.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3DomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    // https://junit.org/junit4/
    // http://www.jdom.org/downloads/index.html

    /**
     * JDOM 解析 XML
     * 1. 与 DOM 类似基于树形结构
     * 2. 与 DOM 的区别：
     *   （1）第三方开源的组件
     *   （2）实现使用 JAVA 的 Collections 接口
     *   （3）效率比 DOM 更快
     *
     * @throws JDOMException
     * @throws IOException
     */
    @Test
    public void jdomParseXML() throws JDOMException, IOException {
        // 直接创建 JDOM 解析器
        SAXBuilder builder = new SAXBuilder();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/test/xml/person.xml");
        org.jdom2.Document build = builder.build(is);
        Element rootElement = build.getRootElement();
        List<Person> list = new ArrayList<>();
        Person person = null;
        List<Element> children = rootElement.getChildren();
        for (Element element: children) {
            person = new Person();

            String personid = element.getAttributeValue("personid");
            person.setPersonId(personid);

            List<Element> children1 = element.getChildren();
            for (Element e: children1) {
                String tag = e.getName();
                if ("name".equals(tag)) {
                    person.setName(e.getText());
                } else if ("address".equals(tag)) {
                    person.setAddress(e.getText());
                } else if ("tel".equals(tag)) {
                    person.setTel(e.getText());
                } else if ("fax".equals(tag)) {
                    person.setFax(e.getText());
                } else if ("email".equals(tag)) {
                    person.setEmail(e.getText());
                }
            }

            list.add(person);
        }
        System.out.println("结果：");
        System.out.println(Arrays.toString(list.toArray()));
    }

    // https://dom4j.github.io/
    /**
     * DOM4J 解析 XML
     * 基于树型结构，第三方组件
     * 解析速度快，效率更高，使用的 JAVA 中迭代器实现数据读取，在 Web 框架中使用较多（Hibernate）
     *
     */
    @Test
    public void dom4jParseXML() throws DocumentException {
        // 1. 创建 DOM4J 的解析器对象
        SAXReader reader = new SAXReader();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/test/xml/person.xml");
        org.dom4j.Document doc = reader.read(is);
        org.dom4j.Element rootElement = doc.getRootElement();
        Iterator<org.dom4j.Element> iterator = rootElement.elementIterator();
        ArrayList<Person> persons = new ArrayList<>();
        Person p = null;
        while (iterator.hasNext()) {
            p = new Person();

            org.dom4j.Element e = iterator.next();
            p.setPersonId(e.attributeValue("personid"));

            Iterator<org.dom4j.Element> iterator1 = e.elementIterator();
            while (iterator1.hasNext()) {
                org.dom4j.Element next = iterator1.next();
                String tag = next.getName();
                if ("name".equals(tag)) {
                    p.setName(next.getText());
                } else if ("address".equals(tag)) {
                    p.setAddress(next.getText());
                } else if ("tel".equals(tag)) {
                    p.setTel(next.getText());
                } else if ("fax".equals(tag)) {
                    p.setFax(next.getText());
                } else if ("email".equals(tag)) {
                    p.setEmail(next.getText());
                }
            }
            persons.add(p);
        }
        System.out.println("结果");
        System.out.println(Arrays.toString(persons.toArray()));
    }


    /**
     * 把对象转成 XML 文件写入
     */
    @Test
    public void xmlEncoder() throws FileNotFoundException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("test.xml"));
        XMLEncoder xmlEncoder = new XMLEncoder(bos);
        Person p = new Person();
        p.setPersonId("1212");
        p.setName("chen");
        p.setAddress("beijing");
        p.setEmail("abc@163.com");
        p.setFax("12345678");
        p.setTel("13812341234");

        xmlEncoder.writeObject(p);
        xmlEncoder.close();;
    }

    /**
     * 从 XML 文件中读取对象
     */
    @Test
    public void xmlDecode() throws FileNotFoundException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("test.xml"));
        XMLDecoder xmlDecoder = new XMLDecoder(in);
        Person p = (Person)xmlDecoder.readObject();
        System.out.println(p);
    }


    // https://x-stream.github.io/download.html
    // xstream, xpp, xmlpull
    // https://blog.csdn.net/sun8112133/article/details/90372760

    /**
     * 使用第三方 xstream 组件实现 XML 的解析与生成
     */
    @Test
    public void xStream() {
        Person p = new Person();
        p.setPersonId("1212");
        p.setName("chen");
        p.setAddress("beijing");
        p.setEmail("abc@163.com");
        p.setFax("12345678");
        p.setTel("13812341234");

        XStream xStream = new XStream(new XppDriver());
        xStream.alias("person", Person.class);  // 将 person 作为别名节点
        xStream.useAttributeFor(Person.class, "personId");  // 将 personId 作为属性
        String xml = xStream.toXML(p);
        System.out.println(xml);

        // 解析 XML
        Person person = (Person)xStream.fromXML(xml);
        System.out.println(person);
    }

}
