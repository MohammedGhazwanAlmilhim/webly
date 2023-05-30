package org.webly;

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
     * @param tag Navnet på HTML-taggen.
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
     * Metoden returnerer navnet på HTML-taggen for dette elementet.
     *
     * @return Taggen for dette elementet.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Metoden legger til en CSS-stil til dette elementet.
     *
     * @param property Navnet på CSS-egenskapen.
     * @param value Verdien til CSS-egenskapen.
     * @return Dette Element-objektet.
     */
    public Element style(String property, String value) {
        style.put(property, value);
        return this;
    }

    /**
     * Metoden returnerer en map av stiler som er lagt til dette elementet.
     *
     * @return Map av stiler for dette elementet.
     */
    public Map<String, String> getStyle() {
        return style;
    }

    /**
     * Metoden gir en strengrepresentasjon av dette HTML-elementet, inkludert dets barn.
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
            sb.append("\n" + "  ").append(child.toString());
        }
        sb.append(text).append(" "+ "\n"+"</").append(tag).append(">");
        return sb.toString();
    }

    /**
     * Metoden returnerer verdien av angitt attributt.
     *
     * @param name Navnet på attributtet.
     * @return Verdien til det angitte attributtet.
     */
    public String getAttribute(String name) {
        return attributes.get(name);
    }

    /**
     * Metoden legger til et attributt til dette elementet.
     *
     * @param name Navnet på attributtet.
     * @param value Verdien til attributtet.
     * @return Dette Element-objektet.
     */
    public Element attribute(String name, String value) {
        attributes.put(name, value);
        return this;
    }

    /**
     * Metoden oppretter et nytt Element-objekt med angitt tag.
     *
     * @param tag Navnet på HTML-taggen.
     * @return Et nytt Element-objekt.
     */
    public static Element create(String tag) {
        return new Element(tag, "");
    }

    /**
     * Metoden oppdaterer teksten for dette elementet.
     *
     * @param text Den nye teksten for dette elementet.
     * @return Dette Element-objektet.
     */
    public Element with(String text) {
        this.text = text;
        return this;
    }

}
