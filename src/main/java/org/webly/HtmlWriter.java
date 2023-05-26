package org.webly;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlWriter implements AutoCloseable  {
    private final BufferedWriter writer;
    /**
     *
     * @param filename
     * @throws IOException
     */
    private HtmlWriter(String filename) throws IOException {
        FileWriter fWriter = new FileWriter(filename);
        writer = new BufferedWriter(fWriter);
    }
    /**
     *Her skal hele funskjonen beskrives med en eller flere linjer
     *
     *
     * @param filename forklarer hva variablen gjør
     * @return
     * @throws IOException
     */
    public static HtmlWriter CreateWriter(String filename) throws IOException {
        return new HtmlWriter(filename);
    }

    /**
     *
     * @param body
     * @throws IOException
     */

    public void writeElement(Body body) throws IOException {
        writer.write(String.valueOf(body));
        writer.newLine();
    }

    /**
     *
     * @throws IOException
     */

    public void close() throws IOException {
        writer.close();
    }
}
