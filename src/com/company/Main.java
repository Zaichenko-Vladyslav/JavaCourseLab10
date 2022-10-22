/*
 *
 * Classname: Main
 *
 * @version 22.06.2020
 * @author Vladyslav Zaichenko
 *
 * Module 4 task 1
 *
 * Home task. Files management.
 *
 *  1. Parse the file logs.txt (see the attachment).
 *  Extract to a separate file all the logs from October 2019.
 *
 *  2. Calculate the total number of logs (lines).
 *
 *  3. Calculate the total  number of  ERROR logs.
 *  Use previous skills - String.split
 *
 *  4. Calculate the total number of ERROR logs. Use Files.lines.
 *
 *  5. Compare two results.
 *
 */

package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println
                ("\n ------------------- PART 1 -------------------");

        /*
         * @param startSpring return time start
         */
        LocalDateTime startSpring = LocalDateTime.now();

        /*
        * @param text return text from file logs.txt
        */
        String text = new String(Files.readAllBytes(Paths.get("/Users/Vladyslav/Desktop/logs.txt")));

        /*
         * @param lines return string array of lines from file logs.txt
         */
        String[] lines = text.split(System.lineSeparator());

        System.out.println("\n This file logs.txt contains: " + lines.length + " lines");

        /*
         * @param counterErrors return count of lines with ERROR from file logs.txt
         */
        int counterErrors = 0;

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("ERROR")) {
                counterErrors++;
            }
        }

        System.out.println("\n This file logs.txt contains: " + counterErrors + " ERROR logs");

        /*
         * @param finishSpring return time finish
         */
        LocalDateTime finishSpring = LocalDateTime.now();

        System.out.println("Using String.split for finding ERRORS needs "
                + ChronoUnit.MILLIS.between(startSpring, finishSpring) + " milliseconds");

        System.out.println
                ("\n ------------------- PART 2 -------------------");

        /*
         * @param startFiles return time start
         */
        LocalDateTime startFiles = LocalDateTime.now();

        /*
         * @param counterErrors2 return count of lines with ERROR from file logs.txt
         */
        final long counterErrors2 = Files.lines(Paths.get("/Users/Vladyslav/Desktop/logs.txt"))
                .filter(line -> line.contains("ERROR"))
                .count();

        System.out.println("\n This file logs.txt contains: " + counterErrors2 + " ERROR logs");

        /*
         * @param finishFiles return time finish
         */
        LocalDateTime finishFiles = LocalDateTime.now();

        System.out.println("Using Files.lines for finding ERRORS needs "
                + ChronoUnit.MILLIS.between(startFiles, finishFiles) + " milliseconds");

        System.out.println
                ("\n ------------------- PART 3 -------------------");

        /*
         * @param theOctober2019Logs return String List
         * with date October 2019 from file logs.txt
         * (from stream to list)
         */
        List<String> theOctober2019Logs = Files.lines(Paths.get("/Users/Vladyslav/Desktop/logs.txt"))
                .filter(line -> line.contains("2019-10-"))
                        .collect(Collectors.toList());

        // @param octoberLogs return empty line and filling
        String octoberLogs = "";
        for (String line:theOctober2019Logs){
            octoberLogs += line + System.lineSeparator();
        }

        // Creation file October2019logs.txt and recording data from octoberLogs
        Path path = Paths.get("/Users/Vladyslav/Desktop/October2019logs.txt");

        Files.write(path, octoberLogs.getBytes());

        System.out.println
                ("\nFile with all logs from October 2019 on your Desktop");

        // ------------ MY OUTPUT -------------

        /*

        ------------------- PART 1 -------------------

        This file logs.txt contains: 2845607 lines

        This file logs.txt contains: 361 ERROR logs
        Using String.split for finding ERRORS needs 3849 milliseconds

        ------------------- PART 2 -------------------

        This file logs.txt contains: 361 ERROR logs
        Using Files.lines for finding ERRORS needs 1180 milliseconds

        ------------------- PART 3 -------------------

        File with all logs from October 2019 on your Desktop

        */
    }
}
