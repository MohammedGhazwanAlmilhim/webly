package org.webly;

import org.webly.structures.Element;

public class Main {
    public static void main(String[] args) {
        Webly app = Webly.createApp("blog.html");

        Element section1 = Element.createInstance("section")
                .setAttribute("id", "section1")
                .setAttribute("class", "container")
                .setStyle("width: 100%; background-color: #1c1b1b; height: 100vh; display:flex;  justify-content:center; align-items:center;");

        Element section2 = Element.createInstance("section")
                .setAttribute("class", "information")
                .setStyle("width: 400px; background-color:white; display:flex; flex-direction:column; justify-content:center; padding:30px; border-radius:10px;");

        Element heading1 = Element.createInstance("h1").setText("Hvorfor bruke Webly?").setStyle("font-size:30px; margin: 0 0 20px 0;");
        Element paragraph1 = Element.createInstance("p").setText("Webly er et effektivt verktøy for å bygge webapplikasjoner, da det gir brukerne muligheten til å skape funksjonelle og estetisk tiltalende nettsteder med enkle programmeringskonsepter, noe som kan spare betydelig tid og krefter i utviklingsprosessen.");
        Element paragraph2 = Element.createInstance("p").setText("Forfatter: Mohammed Ghazwan Almilhim");
        Element paragraph3 = Element.createInstance("p").setText("Publisert: 1. juni 2023");


        section1.addElement(section2);

        section2.addElement(heading1, paragraph1, paragraph2, paragraph3);

        app.addElement(section1);

        app.startServerOnPort(2000);
    }
}
