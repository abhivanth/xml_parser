package de.itdesign.application;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static List<ExtractedCityRow> parseData(String fileName) throws ParserConfigurationException, IOException, SAXException {
        List<ExtractedCityRow> rows = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File(fileName));
        NodeList nList = xmlDocument.getElementsByTagName("city");

        for (int i = 0; i < nList.getLength(); i++) {
            ExtractedCityRow row = new ExtractedCityRow();

            for (int j = 0; j < nList.item(i).getChildNodes().getLength(); j++) {
                Node node = nList.item(i).getChildNodes().item(j);
                node.getTextContent();
                if (node.getTextContent().strip().length() > 0) {

                    row.childNodes.put(node.getNodeName(), node.getTextContent());
                }
            }
            for (int k = 0; k < nList.item(i).getAttributes().getLength(); k++) {
                Node node = nList.item(i).getAttributes().item(k);
                row.attributes.put(node.getNodeName(), node.getTextContent());
            }
            rows.add(row);
        }
        return rows;
    }

    public static List<ExtractedOperationRow> parseOperation(String fileName) throws ParserConfigurationException, IOException, SAXException {
        List<ExtractedOperationRow> rows = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File(fileName));
        NodeList nList = xmlDocument.getElementsByTagName("operation");

        for (int i = 0; i < nList.getLength(); i++) {
            ExtractedOperationRow row = new ExtractedOperationRow();
            for (int j = 0; j < nList.item(i).getAttributes().getLength(); j++) {
                Node node = nList.item(i).getAttributes().item(j);
                switch (node.getNodeName()) {
                    case "name":
                        row.setName(node.getTextContent());
                        break;
                    case "type":
                        row.setType(node.getTextContent());
                        break;
                    case "func":
                        row.setFunction(node.getTextContent());
                        break;
                    case "attrib":
                        row.setAttribute(node.getTextContent());
                        break;
                    case "filter":
                        row.setFilter(node.getTextContent());
                        break;
                }
            }
            rows.add(row);

        }
        return rows;
    }

}
