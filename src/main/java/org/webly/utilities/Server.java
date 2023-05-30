package org.webly.utilities;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * Denne klassen håndterer en enkel HTTP-server som serverer HTML og CSS-filer.
 */
public class Server {
    private final String FILENAME;
    private final String CSSFILE = "style.css";

    /**
     * Privat konstruktør som tar en filnavn-parameter for serverklassen.
     *
     * @param FILENAME Navnet på filen som skal serveres av HTTP-serveren.
     */
    private Server(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    /**
     * Oppretter en Server-instans med angitt filnavn.
     *
     * @param FILENAME Navnet på filen som skal serveres av HTTP-serveren.
     * @return En ny Server-instans.
     */
    public static Server createServer(String FILENAME) {
        return new Server(FILENAME);
    }

    /**
     * Starter HTTP-serveren på angitt port.
     *
     * @param port Porten serveren skal lytte på.
     * @throws IOException Hvis det oppstår en I/O-feil.
     */
    public void StartServer(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started. Listening on port " + port + "...");
        ServerLoop(server);
    }

    /**
     * Håndterer inngående HTTP-forespørsler i en kontinuerlig løkke.
     *
     * @param server ServerSocket-instansen som skal lytte for inngående forespørsler.
     * @throws IOException Hvis det oppstår en I/O-feil.
     */
    private void ServerLoop(ServerSocket server) throws IOException {
        while (true) {
            Socket client = server.accept();
            new Thread(() -> {
                try {
                    String request = readHttpRequest(client.getInputStream());
                    if (request.contains("GET /style.css")) {
                        handleRequest(client, CSSFILE, "text/css");
                    } else {
                        handleRequest(client, FILENAME, "text/html");
                    }
                    client.close();
                } catch (IOException e) {
                    System.out.println("Error processing request: " + e.getMessage());
                }
            }).start();
        }
    }

    /**
     * Leser inn en HTTP-forespørsel fra en klient.
     *
     * @param inputStream InputStream fra klienten.
     * @return En streng representasjon av HTTP-forespørselen.
     * @throws IOException Hvis det oppstår en I/O-feil.
     */
    private String readHttpRequest(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null && !line.isBlank()) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    /**
     * Håndterer en HTTP-forespørsel og sender tilbake en passende respons.
     *
     * @param client Socket-objektet som representerer klienten.
     * @param file Filen som skal sendes som respons.
     * @param contentType Responsens innholdstype.
     * @throws IOException Hvis det oppstår en I/O-feil.
     */
    private void handleRequest(Socket client, String file, String contentType) throws IOException {
        String responseBody = "";
        try {
            responseBody = readFile(file);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        sendHttpResponse(client.getOutputStream(), contentType, responseBody);
    }

    /**
     * Sender et HTTP-respons til en klient.
     *
     * @param outputStream OutputStream til klienten.
     * @param contentType Responsens innholdstype.
     * @param body Kroppen i HTTP-responsen.
     * @throws IOException Hvis det oppstår en I/O-feil.
     */
    private static void sendHttpResponse(OutputStream outputStream, String contentType, String body) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: " + contentType + "; charset=UTF-8\r\n" +
                "Connection: close\r\n\r\n" +
                body;
        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }
    /**
     * Åpner en URI i brukerens standard nettleser.
     *
     * @param uri URIen som skal åpnes.
     */
    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Lese innholdet i en fil og returnerer det som en streng.
     *
     * @param filePath Stien til filen som skal leses.
     * @return Innholdet i filen som en streng.
     * @throws IOException Hvis det oppstår en I/O-feil.
     */
    private String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
}
