package de.itdesign.application;

import de.itdesign.application.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class XMLCalculatorTest {

    @Test
    public void test1() throws ParserConfigurationException, IOException, SAXException {
        executeTest("1");
    }
    @Test
    public void test2() throws ParserConfigurationException, IOException, SAXException {
        executeTest("2");
    }
    @Test
    public void test3() throws ParserConfigurationException, IOException, SAXException {
        executeTest("3");
    }

    void executeTest(String testCaseNumber) throws ParserConfigurationException, SAXException, IOException {
        String dataFile = ClassLoader.getSystemResource("testcase" + testCaseNumber + "-data.xml").getPath();
        String operationsFile = ClassLoader.getSystemResource("testcase" + testCaseNumber + "-operations.xml").getPath();
        String expectedOutputFile = ClassLoader.getSystemResource("testcase" + testCaseNumber + "-expected-output.xml").getPath();
        String actualOutputFile = "testcase-output.xml";
        String args[] = new String[]{dataFile, operationsFile, actualOutputFile};
        XMLCalculator.main(args);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocumentExpected = builder.parse(new File(expectedOutputFile));
        Document xmlDocumentActual = builder.parse(new File(actualOutputFile));
        for (int i = 0; i < xmlDocumentExpected.getElementsByTagName("result").getLength(); i++) {
            assertEquals(xmlDocumentExpected.getElementsByTagName("result").item(i).getTextContent(), xmlDocumentActual.getElementsByTagName("result").item(i).getTextContent());
        }
    }

}
