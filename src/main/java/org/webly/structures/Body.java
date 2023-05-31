package org.webly.structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Denne klassen representerer et Body element av et HTML-dokument, som kan inneholde flere HTML-elementer.
 */
public class Body {
    private final List<Element> elements;

    /**
     * Privat konstruktør for å initialisere et Body objekt.
     */
    private Body() {
        this.elements = new ArrayList<>();
    }

    /**
     * Factory-metode for å opprette et nytt Body objekt.
     *
     * @return Et nytt Body objekt.
     */
    public static Body createBody() {
        return new Body();
    }

    /**
     * Metoden legger til et Element til listen over elementer i dette Body objektet.
     *
     * @param element Elementet som skal legges til.
     */
    public void addElement(Element element) {
        elements.add(element);
    }

    /**
     * Metoden returnerer en streng som representerer en link til stilarket for dette dokumentet.
     *
     * @return En streng som representerer en link til stilarket.
     */
    private String getStyleSheetLink() {
        return "<link rel=\"stylesheet\" href=\"style.css\">";
    }

    /**
     * Metoden gir en strengrepresentasjon av dette Body objektet, inkludert alle dets elementer.
     *
     * @return En strengrepresentasjon av dette Body objektet.
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>" + "\n");
        sb.append("<html lang=\"en\">" + "\n");
        sb.append("<head>" + "\n");
        sb.append("\t").append("<meta charset='UTF-8'>" + "\n");
        sb.append("\t").append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" + "\n");
        sb.append("\t").append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +"\n");
        sb.append("\t").append("<title>App</title>" + "\n");
        sb.append("\t").append(getStyleSheetLink()).append("\n");

        sb.append("</head>" + "\n");
        sb.append("<body>" + "\n");
        for (Element element : elements) {
            sb.append("\t").append(element).append("\n");
        }
        sb.append("</body>" + "\n");
        sb.append("</html>");
        return sb.toString();
    }

    /**
     * Metoden returnerer en liste av elementer i dette Body objektet.
     *
     * @return En liste av elementer i dette Body objektet.
     */
    public List<Element> getElements() {
        return elements;
    }
}
