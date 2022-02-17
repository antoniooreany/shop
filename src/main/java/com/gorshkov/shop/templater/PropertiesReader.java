package com.gorshkov.shop.templater;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static final String PATH = "./src/main/resources/application.properties";

    private static Properties properties = new Properties();

    private static void loadProperties(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                properties.put(parts[0], parts[1]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No properties file found at: " + path);
        } catch (IOException e) {
            throw new RuntimeException("Something's wrong with your properties file at: " + path);
        }
    }

    public static Properties getProperties() {
        loadProperties(PATH);
        return properties;
    }
}
