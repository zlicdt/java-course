package com.zlicdt.db;
import java.sql.*;
public class sqliteAction {
    public static void init() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:data.db");
            Statement stmt = c.createStatement();
            String createAccountsString = "CREATE TABLE IF NOT EXISTS accounts (id INTEGER PRIMARY KEY, username TEXT, password TEXT, email TEXT)";
            stmt.executeUpdate(createAccountsString);
            String createBookString = "CREATE TABLE IF NOT EXISTS book (id INTEGER PRIMARY KEY, name TEXT, time TEXT, date TEXT, room TEXT)";
            stmt.executeUpdate(createBookString);
            stmt.close();
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}