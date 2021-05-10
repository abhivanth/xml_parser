package de.itdesign.application;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class XMLCalculator {

    private static StringBuilder processAndWriteResultsAsXmlFile(String DATA_FILE, String OPERATIONS_FILE, String OUTPUT_FILE) throws IOException, SAXException, ParserConfigurationException {
        List<ExtractedCityRow> extractedDataRows = XmlParser.parseData(DATA_FILE);
        List<ExtractedOperationRow> operationRows = XmlParser.parseOperation(OPERATIONS_FILE);
        StringBuilder resultsXmlBuilder = new StringBuilder();
        resultsXmlBuilder.append("<results>").append(System.lineSeparator());
        for (ExtractedOperationRow operation : operationRows) {
            List<ExtractedCityRow> filteredDataRows = Filter.filterCitiesByExpression(extractedDataRows, operation.filter);
            resultsXmlBuilder.append(Operation.extractAndCompute(filteredDataRows, operation)).append(System.lineSeparator());
        }
        resultsXmlBuilder.append("</results>").append(System.lineSeparator());
        Files.write(Paths.get(OUTPUT_FILE), resultsXmlBuilder.toString().getBytes());
        return resultsXmlBuilder;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        //Don't change this part
        if (args.length == 3) {
            //Path to the data file, e.g. data/data.xml
            final String DATA_FILE = args[0];
            //Path to the data file, e.g. operations/operations.xml
            final String OPERATIONS_FILE = args[1];
            //Path to the output file
            final String OUTPUT_FILE = args[2];
            XMLCalculator.processAndWriteResultsAsXmlFile(DATA_FILE, OPERATIONS_FILE, OUTPUT_FILE);
        } else {
            System.exit(1);
        }
    }

}
