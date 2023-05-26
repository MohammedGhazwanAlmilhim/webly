package org.webly;

public class Main {
    public static void main(String[] args) {
        Webly app = Webly.create("index.html");

        Element video = Element.create("video").with("video").attribute("onclick", "playVideo()");

        app.add(video);

        app.start(2000);
    }
}