package org.webly;
import java.util.ArrayList;
import java.util.List;

public class Body {
    private final List<Element> elements;

    private Body() {
        this.elements = new ArrayList<>();
    }

    static public Body CreateBody() {
        return new Body();
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public String getStyleSheetLink() {
        return "<link rel=\"stylesheet\" href=\"style.css\">";
    }


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
        sb.append("\t").append(getStyleSheetLink() + "\n");

        sb.append("</head>" + "\n");
        sb.append("<body>" + "\n");
        for (Element element : elements) {
            sb.append("\t").append(element).append("\n");
        }
        sb.append("</body>" + "\n");
        sb.append("</html>");
        return sb.toString();
    }

    public List<Element> getElements() {
        return elements;
    }
}