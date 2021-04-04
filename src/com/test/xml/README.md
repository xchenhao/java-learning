### XML 解析的几种方法
- SAXParser
- DOM Parser
- JDOM
  + http://www.jdom.org/downloads/index.html
- DOM4J
  + https://dom4j.github.io/

### 比较
- JDOM 和 DOM 在性能测试时表现不佳，在测试 10M 文档时内存溢出
- SAX 表现较好，这要依赖于它特定的解析方式。一个 SAX 检测即将到来的 XML 流，但并没有载入到内存（当然当 XML 流被读入时，会有部分文档暂时隐藏在内存中）。
- DOM4J 是这场测试的获胜者，目前许多开源项目中大量采用 DOM4J，例如 Hibernate 用 DOM4 来读取 XML 配置文件。

xstream 实现 XML 的转换
