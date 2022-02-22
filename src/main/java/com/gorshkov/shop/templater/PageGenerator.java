package com.gorshkov.shop.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class PageGenerator {
    private static final String HTML_DIR = PropertiesReader.getProperties().getProperty("htmlDir");

    private static PageGenerator pageGenerator;
    private static Configuration cfg;

    private PageGenerator() {
        cfg = new Configuration();
    }

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer writer = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, writer);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    public static void writePage(PrintWriter writer, String filename) {
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(new HashMap<>(), writer);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
