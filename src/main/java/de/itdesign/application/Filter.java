package de.itdesign.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    public static List<ExtractedCityRow> filterCitiesByExpression(List<ExtractedCityRow> rows, String filterExpression) {
        return rows.stream().filter(row -> row.attributes.get("name").matches(filterExpression)).collect(Collectors.toList());
    }

}
