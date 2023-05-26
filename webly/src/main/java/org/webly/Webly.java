package org.webly;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Webly {
    private final String FILENAME;
    private static Body body;
    private static Server server;

    private Webly(String FILENAME) {
        server = Server.createServer(FILENAME);
        body = Body.CreateBody();
        this.FILENAME = FILENAME;
    }

    public static Webly create(String filepath) {
        return new Webly(filepath);
    }

    public void add(Element... element) {
        for (Element e : element) {
            body.addElement(e);
        }
    }

    public void CompileAll() {
        try {
            HtmlWriter htmlWriter = HtmlWriter.CreateWriter(FILENAME);
            htmlWriter.writeElement(body);
            htmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteCSS() {
        CSSGenerator.cssWriter(body);
    }


    public void start(int port) {
        try {
            WriteCSS();
            CompileAll();
            Server.openWebpage(new URI("http://localhost:" + port + "/" + FILENAME));
            System.out.println("Opened webpage in browser at http://localhost:" + port + "/" + FILENAME);
            server.StartServer(port);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }


}
