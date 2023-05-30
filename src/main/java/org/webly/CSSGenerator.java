package org.webly;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/**
 * Denne klassen genererer CSS-regler basert på stilen definert i HTML-elementer og skriver dem til en CSS-fil.
 */
public class CSSGenerator {
    /**
     * Privat konstruktør for å opprette en CSSGenerator instans.
     */
    private CSSGenerator() {
    }

    /**
     * Metoden oppretter en ny CSSGenerator instans.
     *
     * @return En ny CSSGenerator instans.
     */
    public static CSSGenerator CreateCSSGenerator() {
        return new CSSGenerator();
    }

    /**
     * Metoden genererer CSS-regler fra gitt Body-objekt og skriver dem til en CSS-fil.
     *
     * @param body Body-objektet som inneholder HTML-elementer med stil.
     */
    public static void cssWriter(Body body) {
        // Opprett en ArrayList for å lagre CSS-reglene
        ArrayList<String> cssRules = new ArrayList<>();

        // Opprett en HashSet for å lagre stilreglene for elementer som ikke har noen ID, klasse eller tag name
        HashSet<String> globalStyles = new HashSet<>();

        // Gå gjennom hvert element i body og legg til CSS-regler hvis det er stilregler
        for (Element element : body.getElements()) {
            // Sjekk om elementet har stilregler
            if (!element.getStyle().isEmpty()) {
                // Opprett en CSS-regel for dette elementet
                String selector = "";

                // Legg til ID i selector hvis det finnes
                String id = element.getAttribute("id");
                if (id != null) {
                    selector += "#" + id;
                }

                // Legg til klasse i selector hvis det finnes
                String klass = element.getAttribute("class");
                if (klass != null) {
                    if (selector.isEmpty()) {
                        selector += "." + klass;
                    } else {
                        selector += "." + klass.replaceAll("\\s+", ".");
                    }
                }

                if (selector.isEmpty() && !element.getStyle().isEmpty()) {
                    selector += element.getTag();
                    globalStyles.add(selector);
                }

                // Sjekk om elementet har stilregler før vi legger til regelen
                if (!element.getStyle().isEmpty() && !selector.isEmpty()) {
                    StringBuilder rule = new StringBuilder("{\n");

                    // Legg til alle stilreglene til regelen
                    for (Map.Entry<String, String> styleRule : element.getStyle().entrySet()) {
                        rule.append("  ").append(styleRule.getKey()).append(": ").append(styleRule.getValue()).append(";\n");
                    }

                    rule.append("}");

                    cssRules.add(selector + " " + rule);
                }
            }

        }


        // Skriv CSS-reglene til fil hvis det finnes noen
        if (!cssRules.isEmpty()) {
            try {
                FileWriter writer = new FileWriter("style.css");
                for (String cssRule : cssRules) {
                    writer.write(cssRule + "\n");
                }

                // Legg til stilene for elementer uten noen ID, klasse eller tag name
                for (String globalStyle : globalStyles) {
                    writer.write(globalStyle + " {}\n");
                }

                writer.close();
                System.out.println("CSS generated and stored in style.css");
            } catch (IOException e) {
                System.out.println("Error writing CSS to file: " + e.getMessage());
            }
        }
    }


}
