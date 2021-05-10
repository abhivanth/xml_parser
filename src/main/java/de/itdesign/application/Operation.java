package de.itdesign.application;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Operation {
    final static String RESULT_PLACEHOLDER = "$RESULT_PLACEHOLDER";

    public static String extractAndCompute(List<ExtractedCityRow> rows, ExtractedOperationRow operation) {
        String result = "<result name=\"" + operation.getName() + "\">" + RESULT_PLACEHOLDER + "</result>";
        List<Double> selectedValues = null;
        if ("attrib".equals(operation.getType())) {
            selectedValues = rows.stream().map(row ->
                    row.attributes.get(operation.getAttribute())
            ).map(Double::parseDouble).collect(Collectors.toList());
        } else {
            selectedValues = rows.stream().map(row ->
                    row.childNodes.get(operation.getAttribute())
            ).map(Double::parseDouble).collect(Collectors.toList());
        }
        return result.replace(RESULT_PLACEHOLDER, Operation.compute(selectedValues, operation.getFunction()));
    }

    private static String compute(List<Double> rows, String function) {
        Double result = null;
        DecimalFormat df = new DecimalFormat("0.00");
        if ("sum".equalsIgnoreCase(function)) {
            result = rows.stream().collect(Collectors.summingDouble(d -> d));
        } else if ("average".equalsIgnoreCase(function)) {
            result = rows.stream().collect(Collectors.averagingDouble(d -> d));
        } else if ("max".equalsIgnoreCase(function)) {
            result = rows.stream().max(Double::compare).get();
        } else if ("min".equalsIgnoreCase(function)) {
            result = rows.stream().min(Double::compare).get();
        }
        return df.format(result).replace(",", ".");
    }
}
