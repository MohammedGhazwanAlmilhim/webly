package org.webly.structures;

import java.util.*;

/**
 * Denne klassen representerer et HTML-element med egenskaper som tag, tekst, attributter, stil og barn.
 */
public class Element {
    private final String tag;
    private String text;
    private final Map<String, String> attributes;
    private final Map<String, String> style;
    private final List<Element> children;

    /**
     * Konstruktør for å initialisere et HTML-element med angitt tag og tekst.
     *
     * @param tag  Navnet på HTML-taggen.
     * @param text Teksten som skal vises i HTML-elementet.
     */
    public Element(String tag, String text) {
        this.tag = tag;
        this.text = text;
        this.attributes = new HashMap<>();
        this.style = new HashMap<>();
        this.children = new ArrayList<>();
    }

    /**
     * Returnerer navnet på HTML-taggen for dette elementet.
     *
     * @return Taggen for dette elementet.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Legger til en CSS-stil til dette elementet.
     *
     * @param styleString Strengen som inneholder stilen i formatet "egenskap1: verdi1; egenskap2: verdi2; ..."
     * @return Dette Element-objektet.
     */
    public Element setStyle(String styleString) {
        String[] styleRules = styleString.split(";");
        for (String rule : styleRules) {
            String[] parts = rule.trim().split(":");
            if (parts.length == 2) {
                String property = parts[0].trim();
                String value = parts[1].trim();
                style.put(property, value);
            }
        }
        return this;
    }

    /**
     * Returnerer en map av stiler som er lagt til dette elementet.
     *
     * @return Map av stiler for dette elementet.
     */
    public Map<String, String> getStyle() {
        return style;
    }

    /**
     * Returnerer en strengrepresentasjon av dette HTML-elementet, inkludert dets barn.
     *
     * @return En strengrepresentasjon av dette HTML-elementet.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tag);
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            sb.append(" ").append(attribute.getKey()).append("=\"").append(attribute.getValue()).append("\"");
        }

        sb.append(">");
        for (Element child : children) {
            sb.append("\n").append("  ").append(child.toString());
        }
        sb.append(text).append("\n").append("</").append(tag).append(">");
        return sb.toString();
    }

    /**
     * Returnerer verdien av angitt attributt.
     *
     * @param name Navnet på attributtet.
     * @return Verdien til det angitte attributtet.
     */
    public String getAttribute(String name) {
        return attributes.get(name);
    }

    /**
     * Legger til et attributt til dette elementet.
     *
     * @param name  Navnet på attributtet.
     * @param value Verdien til attributtet.
     * @return Dette Element-objektet.
     */
    public Element setAttribute(String name, String value) {
        attributes.put(name, value);
        return this;
    }

    /**
     * Oppretter et nytt Element-objekt med angitt tag.
     *
     * @param tag Navnet på HTML-taggen.
     * @return Et nytt Element-objekt.
     */
    public static Element createInstance(String tag) {
        return new Element(tag, "");
    }

    /**
     * Oppdaterer teksten for dette elementet.
     *
     * @param text Den nye teksten for dette elementet.
     * @return Dette Element-objektet.
     */
    public Element setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Legger til et Element til listen over elementer i dette Element-objektet.
     *
     * @param elements Elementene som skal legges til.
     */
    public void addElement(Element... elements) {
        children.addAll(Arrays.asList(elements));
    }

    /**
     * Returnerer en liste av barn elementer av dette Elementet.
     * Barn elementer er elementer som er nøstet innenfor dette elementet i HTML strukturen.
     *
     * @return en Liste av barn Element objekter.
     */
    public List<Element> getChildren() {
        return this.children;
    }

}
