/*
 * This is used to list the database tables and their contents.
 * Never be called by the main program.
 */

package tests;

import java.sql.*;

public class listDatabase {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM accounts"; // Change to the table you want to list
            ResultSet rs = stmt.executeQuery(sql);
            String output = "ID\tUNAME\tPSWD\tEMAIL\n";
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                output += id + "\t" + username + "\t" + password + "\t" + email + "\n";
            }
            System.out.println(output);
            sql = "SELECT * FROM book"; // Change to the table you want to list
            rs = stmt.executeQuery(sql);
            output = "ID\tUNAME\tTIME\tDATE\tROOM\n";
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String time = rs.getString("time");
                String date = rs.getString("date");
                String room = rs.getString("room");
                output += id + "\t" + name + "\t" + time + "\t" + date + "\t" + room + "\n";
            }
            System.out.println(output);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            System.out.println("Database listing completed.");
        }
    }
}
