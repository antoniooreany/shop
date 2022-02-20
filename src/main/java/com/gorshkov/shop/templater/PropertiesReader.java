package com.gorshkov.shop.templater;

import com.gorshkov.shop.Starter;
import org.eclipse.jetty.util.UrlEncoded;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesReader {
    private static final String PATH = "./src/main/resources/application.properties";
//    private static final String PATH = PropertiesReader.class.getResource("application.properties").getPath();

    private static final Properties properties = new Properties();

    private static void readProperties() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                properties.put(parts[0], parts[1]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No properties file found at: " + PropertiesReader.PATH);
        } catch (IOException e) {
            throw new RuntimeException("Something's wrong with your properties file at: " + PropertiesReader.PATH);
        }
    }

    public static Properties getProperties() {
        readProperties();
        return new Properties(properties);
    }
}
