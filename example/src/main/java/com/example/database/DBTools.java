package com.example.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBTools {
    private static final String DB_URL_MEMORY = "jdbc:sqlite::memory:";
    private static final String DB_URL_FILE = "jdbc:sqlite:pessoas.db";
    private static final String PROPERTIES_FILE = "application.properties";

    private static boolean isTest;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = DBTools.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.println("Arquivo " + PROPERTIES_FILE + " não encontrado.");
                isTest = true; // Valor padrão se o arquivo não for encontrado
                return;
            }
            properties.load(input);
            isTest = Boolean.parseBoolean(properties.getProperty("test", "true")); // Valor padrão é true
        } catch (IOException ex) {
            System.out.println("Erro ao carregar " + PROPERTIES_FILE + ": " + ex.getMessage());
            isTest = true; // Valor padrão em caso de erro
        }
    }

    public static Connection getConnection() throws SQLException {
        if (isTest) {
            System.out.println("Conectando ao banco em memória...");
            return DriverManager.getConnection(DB_URL_MEMORY);
        } else {
            System.out.println("Conectando ao banco local...");
            return DriverManager.getConnection(DB_URL_FILE);
        }
    }

    public static boolean isTestMode() {
        return isTest;
    }
}