package de.itdesign.application;

import java.util.HashMap;
import java.util.Map;

public class ExtractedOperationRow {
    String name;
    String attribute;
    String type;
    String function;
    String filter;

    public void setName(String name) {
        this.name = name;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getName() {
        return name;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getType() {
        return type;
    }

    public String getFunction() {
        return function;
    }

    public String getFilter() {
        return filter;
    }
}
