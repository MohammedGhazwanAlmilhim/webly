package org.webly;

import java.util.*;

public class Element {
    private final String tag;
    private String text;
    private final Map<String, String> attributes;
    private final Map<String, String> style;
    private final List<Element> children;

    public Element(String tag, String text) {
        this.tag = tag;
        this.text = text;
        this.attributes = new HashMap<>();
        this.style = new HashMap<>();
        this.children = new ArrayList<>();
    }

    public String getTag() {
        return tag;
    }

    public Element style(String property, String value) {
        style.put(property, value);
        return this;
    }
    public Map<String, String> getStyle() {
        return style;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tag);
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            sb.append(" ").append(attribute.getKey()).append("=\"").append(attribute.getValue()).append("\"");
        }

        sb.append(">");
        for (Element child : children) {
            sb.append("\n" + "  ").append(child.toString());
        }
        sb.append(text).append(" "+ "\n"+"</").append(tag).append(">");
        return sb.toString();
    }

    public String getAttribute(String name) {
        return attributes.get(name);
    }

    public Element attribute(String name, String value) {
        attributes.put(name, value);
        return this;
    }

    public static Element create(String tag) {
        return new Element(tag, "");
    }
    public Element with(String text) {
        this.text = text;
        return this;
    }

}
