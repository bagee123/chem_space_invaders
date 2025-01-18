package com.example.chem_project;

import java.io.*;

public class timeConversion {
    public static void main(String[] args) {
        int conversionNo = 0;
        String filename = "C:\\intellij\\chem project\\src\\com\\example\\chem_project\\p_mid o_fast.txt"; // Replace with your file path
        String outputFilename = "C:\\intellij\\chem project\\src\\com\\example\\chem_project\\convertedData\\pmof_conv.txt"; // Output file for updated values

        try (BufferedReader reader = new BufferedReader(new FileReader(filename));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                double decimalTime = convertToDecimalTime(line);
                writer.write(String.format("%.2f", decimalTime) + "\n"); // Write decimal time to output file
                conversionNo++;
            }
            System.out.println("Conversion " +conversionNo + " completed. Updated values saved to " + outputFilename);
        } catch (IOException e) {
            System.err.println("Error reading/writing files: " + e.getMessage());
        }
    }

    private static double convertToDecimalTime(String timeString) {
        String[] timeParts = timeString.split(":");
        int minutes = Integer.parseInt(timeParts[0]);
        int seconds = Integer.parseInt(timeParts[1]);
        int milliseconds = Integer.parseInt(timeParts[2]);

        return minutes * 60 + seconds + (double) milliseconds / 100;
    }
}
