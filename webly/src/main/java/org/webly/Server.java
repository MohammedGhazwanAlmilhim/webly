package org.webly;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class Server {
    private final String FILENAME;
    private final String CSSFILE = "style.css";

    private Server(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public static Server createServer(String FILENAME) {
        return new Server(FILENAME);
    }

    public void StartServer(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started. Listening on port " + port + "...");
        ServerLoop(server);
    }

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

    private String readHttpRequest(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null && !line.isBlank()) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    private void handleRequest(Socket client, String file, String contentType) throws IOException {
        String responseBody = "";
        try {
            responseBody = readFile(file);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        sendHttpResponse(client.getOutputStream(), contentType, responseBody);
    }

    private static void sendHttpResponse(OutputStream outputStream, String contentType, String body) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: " + contentType + "; charset=UTF-8\r\n" +
                "Connection: close\r\n\r\n" +
                body;
        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

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
