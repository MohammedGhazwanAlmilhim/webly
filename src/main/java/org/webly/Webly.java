package org.webly;

import org.webly.io.CSSGenerator;
import org.webly.io.HtmlWriter;
import org.webly.structures.Body;
import org.webly.structures.Element;
import org.webly.utilities.Server;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Webly er en klasse som forenkler prosessen med å generere og kjøre en enkel webapplikasjon.
 */
public class Webly {
    private final String FILENAME;
    private final Body body;
    private final Server server;

    /**
     * Privat konstruktør for å opprette en Webly-instans med angitt filnavn.
     *
     * @param FILENAME Filnavnet for HTML-filen som skal genereres.
     */
    private Webly(String FILENAME) {
        this.server = Server.createServer(FILENAME);
        this.body = Body.createBody();
        this.FILENAME = FILENAME;
    }

    /**
     * Metoden oppretter en ny Webly-instans med angitt filnavn.
     *
     * @param filepath Filbanen for HTML-filen som skal genereres.
     * @return En ny Webly-instans.
     */
    public static Webly createApp(String filepath) {
        return new Webly(filepath);
    }

    /**
     * Metoden legger til Element-objekter i Body-objektet til Webly-instansen.
     *
     * @param element En eller flere Element-objekter som skal legges til i Body-objektet.
     */
    public void addElement(Element... element) {
        for (Element e : element) {
            body.addElement(e);
        }
    }

    /**
     * Metoden kompilerer alle HTML- og CSS-filer ved å skrive dem til disk.
     */
    private void compileAll() {
        try {
            HtmlWriter htmlWriter = HtmlWriter.CreateWriter(FILENAME);
            htmlWriter.writeElement(body);
            htmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoden skriver CSS-regler til CSS-filen basert på Body-objektet.
     */
    private void writeCSS() {
        CSSGenerator.cssWriter(body);
    }

    /**
     * Metoden starter webapplikasjonen ved å generere HTML- og CSS-filer, åpne webleseren og starte HTTP-serveren.
     *
     * @param port Portnummeret som HTTP-serveren skal lytte på.
     */
    public void startServerOnPort(int port) {
        try {
            writeCSS();
            compileAll();
            Server.openWebpage(new URI("http://localhost:" + port + "/" + FILENAME));
            System.out.println("Åpnet nettsiden i nettleseren på: http://localhost:" + port + "/" + FILENAME);
            server.StartServer(port);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
}
