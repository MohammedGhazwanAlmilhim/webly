package org.webly.io;

import org.webly.structures.Body;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Denne klassen er designet for å generere HTML-filer ved bruk av BufferedWriter.
 */
public class HtmlWriter implements AutoCloseable  {
    private final BufferedWriter writer;

    /**
     * Privat konstruktør for å initialisere BufferedWriter med angitt filnavn.
     *
     * @param filename Navnet på filen BufferedWriter skal skrive til.
     * @throws IOException Hvis det oppstår en I/O-feil.
     */
    private HtmlWriter(String filename) throws IOException {
        FileWriter fWriter = new FileWriter(filename);
        writer = new BufferedWriter(fWriter);
    }

    /**
     * Metoden oppretter en ny HtmlWriter-instans ved bruk av angitt filnavn.
     *
     * @param filename Navnet på filen BufferedWriter skal skrive til.
     * @return En ny HtmlWriter-instans.
     * @throws IOException Hvis det oppstår en I/O-feil.
     */

    public static HtmlWriter CreateWriter(String filename) throws IOException {
        return new HtmlWriter(filename);
    }

    /**
     * Metoden skriver en HTML-element til filen ved bruk av Body-objektet.
     *
     * @param body Body-objektet som representerer HTML-elementet som skal skrives til filen.
     * @throws IOException Hvis det oppstår en I/O-feil.
     */
    public void writeElement(Body body) throws IOException {
        writer.write(String.valueOf(body));
        writer.newLine();
    }

    /**
     * Metoden lukker BufferedWriter.
     *
     * @throws IOException Hvis det oppstår en I/O-feil.
     */
    public void close() throws IOException {
        writer.close();
    }
}
