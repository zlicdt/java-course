package com.zlicdt.db;
import java.sql.*;
public class sqliteAction {
    public static void init() {
        Connection c = null;
        Statement stmt = null;
        
        try {
            c = DriverManager.getConnection("jdbc:sqlite:data.db");
            stmt = c.createStatement();
            String createAccountsString = "CREATE TABLE IF NOT EXISTS accounts (id INTEGER PRIMARY KEY, username TEXT, password TEXT, email TEXT)";
            stmt.executeUpdate(createAccountsString);
            String createBookString = "CREATE TABLE IF NOT EXISTS book (id INTEGER PRIMARY KEY, name TEXT, time TEXT, date TEXT, room TEXT)";
            stmt.executeUpdate(createBookString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Ensure connection to database is closed
            try {
                if (stmt != null) stmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                System.out.println("Error closing database resources: " + e.getMessage());
            }
        }
    }
}