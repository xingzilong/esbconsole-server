package org.talend.esbconsole.server.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.XML;

import java.io.StringReader;
import java.util.Iterator;

public class Test {

    @org.junit.Test
    public void test() {
        String soapXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://www.talend.org/service/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:ws_helloOperationRequest>\n" +
                "            <name>aa</name>\n" +
                "            <age>12</age>\n" +
                "            <city>City</city>\n" +
                "            <country>Country</country>\n" +
                "      </ser:ws_helloOperationRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try {
            String bodyXML = extractBodyXml(soapXml);
            String operationXML = extractChildXml(bodyXML);
            String realXML = extractChildXml(operationXML);
            System.out.println("================================");
            System.out.println(bodyXML);
            System.out.println("================================");
            System.out.println(operationXML);
            System.out.println("================================");
            System.out.println(XML.toJSONObject(realXML));
            System.out.println(XML.toString("{\"persons\":[{\"name\":\"aa\",\"age\":12},{\"name\":\"bb\",\"age\":20}],\"address\":{\"country\":\"Country\",\"city\":\"City\"}}"));

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static String extractBodyXml(String soapXml) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(soapXml));
        Element bodyElement = document.getRootElement().element("Body");
        return bodyElement.asXML();

    }

    public static String extractChildXml(String xml) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(xml));
        Element bodyElement = document.getRootElement();
        return extractChildElements(bodyElement);
    }

    private static String extractChildElements(Element element) {
        StringBuilder innerXmlBuilder = new StringBuilder();
        for (Iterator<Element> it = element.elementIterator(); it.hasNext(); ) {
            Element child = it.next();
            innerXmlBuilder.append(child.asXML());
        }
        return innerXmlBuilder.toString();
    }
}
