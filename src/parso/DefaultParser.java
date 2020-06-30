package parso;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class DefaultParser {

    public static void main(String[] args) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);

        DocumentBuilder builder;

        Document doc = null;


        try {
            builder = builderFactory.newDocumentBuilder();

            doc = builder.parse("./tvshows.xml");

        }

        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        XPath xpath = XPathFactory.newInstance().newXPath();

        getNodeNameandValue(doc, xpath);


    }

    private static void getNodeNameandValue(Document doc, XPath xpath) {

        XPathExpression expr;

        Object result = null;

        try {
            expr = xpath.compile("//show/name//text()");

            result = expr.evaluate(doc, XPathConstants.NODESET);
        }
        catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        NodeList nodes = (NodeList) result;

        for(int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getParentNode().getNodeName() + " " +
                    nodes.item(i).getNodeValue());
        }
    }

}
