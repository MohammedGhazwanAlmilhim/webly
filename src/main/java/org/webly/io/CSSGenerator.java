package org.webly.io;

import org.webly.structures.Body;
import org.webly.structures.Element;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/**
 * CSSGenerator-klassen genererer CSS-regler basert på stilen definert i HTML-elementer og skriver dem til en CSS-fil.
 */
public class CSSGenerator {
    /**
     * Privat konstruktør for å opprette en CSSGenerator-instans.
     */
    private CSSGenerator() {
    }

    /**
     * Oppretter en ny CSSGenerator-instans.
     *
     * @return En ny CSSGenerator-instans.
     */
    public static CSSGenerator createCSSGenerator() {
        return new CSSGenerator();
    }

    /**
     * Genererer CSS-regler fra det gitte Body-objektet og skriver dem til en CSS-fil.
     *
     * @param body Body-objektet som inneholder HTML-elementer med stil.
     */

    public static void cssWriter(Body body) {
        ArrayList<String> cssRules = new ArrayList<>();
        HashSet<String> globalStyles = new HashSet<>();

        for (Element element : body.getElements()) {
            cssWriterRecursive(element, cssRules, globalStyles);
        }

        try {
            FileWriter writer = new FileWriter("style.css");

            writer.write("@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');\n\n");

            // Write the CSS reset rule first
            writer.write("body { margin: 0; font-family: 'Poppins', sans-serif;}\n\n");

            for (String cssRule : cssRules) {
                writer.write(cssRule + "\n");
            }

            for (String globalStyle : globalStyles) {
                writer.write(globalStyle + "\n");
            }

            writer.close();
            System.out.println("CSS generert og lagret i style.css");
        } catch (IOException e) {
            System.out.println("Error writing CSS to file: " + e.getMessage());
        }
    }


    private static void cssWriterRecursive(Element element, ArrayList<String> cssRules, HashSet<String> globalStyles) {
        if (!element.getStyle().isEmpty()) {
            String selector = "";

            String id = element.getAttribute("id");
            if (id != null) {
                selector += "#" + id;
            }

            String klass = element.getAttribute("class");
            if (klass != null) {
                if (selector.isEmpty()) {
                    selector += "." + klass;
                } else {
                    selector += "." + klass.replaceAll("\\s+", ".");
                }
            }

            if (selector.isEmpty() && element.getTag() != null) {
                selector += element.getTag();
            }

            if (!selector.isEmpty()) {
                StringBuilder rule = new StringBuilder(selector + " {\n");

                for (Map.Entry<String, String> styleRule : element.getStyle().entrySet()) {
                    rule.append("  ").append(styleRule.getKey()).append(": ").append(styleRule.getValue()).append(";\n");
                }

                rule.append("}");

                if (!element.getStyle().isEmpty()) {
                    cssRules.add(rule.toString());
                } else {
                    globalStyles.add(rule.toString());
                }
            }
        }

        for (Element child : element.getChildren()) {
            cssWriterRecursive(child, cssRules, globalStyles);
        }
    }

}
