package org.mycompany.myname.database;

import java.io.File;
import java.sql.*;

public class JDBCMyClass {
    private static final String url = "jdbc:mysql://localhost:3306/usersMyWebApp";
    private static final String user = "root";
    private static final String password = "root852456";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void addUser(String login, String email, String pass){
        String query = "INSERT INTO users (login, email, password, homeDir) VALUES (?,?,?,?);";
        try ( Connection con = DriverManager.getConnection(url, user, password);
                Statement stmt = con.createStatement()){
            PreparedStatement pStatement = con.prepareStatement(query);
            File homeDirs = new File("/Users/pavelzaborin/MWA/"+login);
            homeDirs.mkdirs();
            pStatement.setString(1, login);
            pStatement.setString(2, email);
            pStatement.setString(3, pass);
            pStatement.setString(4,homeDirs.getAbsolutePath()+"/");
            pStatement.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println("addUser");
        }
    }

    public static Boolean findUserByLogin(String login){
        String query = "select count(*) from users where login = ?;";
        try ( Connection con = DriverManager.getConnection(url, user, password);
              Statement stmt = con.createStatement();){
            PreparedStatement pStatement = con.prepareStatement(query);
            pStatement.setString(1, login);
            rs = pStatement.executeQuery();
            rs.next();
            int i = rs.getInt(1);
            return i > 0;
        }
        catch (SQLException ex){
            System.out.println("findUserByLogin");
        }

        return null;
    }

    public static Boolean findUserByMail(String mail){
        String query = "select count(*) from users where email = ?;";
        try ( Connection con = DriverManager.getConnection(url, user, password);
              Statement stmt = con.createStatement();){
            PreparedStatement pStatement = con.prepareStatement(query);
            pStatement.setString(1, mail);
            rs = pStatement.executeQuery();
            rs.next();
            int i = rs.getInt(1);
            return i > 0;
        }
        catch (SQLException ex){
            System.out.println("findUserByMail");
        }

        return null;
    }

    public static Boolean tryConnect(String login, String pass){
        String query = "select count(*) from users where login = ? and password = ? or email = ? and password = ?;";
        try ( Connection con = DriverManager.getConnection(url, user, password);
              Statement stmt = con.createStatement();){
            PreparedStatement pStatement = con.prepareStatement(query);
            pStatement.setString(1, login);
            pStatement.setString(2, pass);
            pStatement.setString(3, login);
            pStatement.setString(4, pass);
            rs = pStatement.executeQuery();
            rs.next();
            int i = rs.getInt(1);
            return i > 0;
        }
        catch (SQLException ex){
            System.out.println("tryConnect");
        }

        return null;
    }

    public static String getDirectory(String login){
        String query = "select homeDir from users where login = ? or email = ?;";
        try ( Connection con = DriverManager.getConnection(url, user, password);
              Statement stmt = con.createStatement();){
            PreparedStatement pStatement = con.prepareStatement(query);
            pStatement.setString(1, login);
            pStatement.setString(2, login);
            rs = pStatement.executeQuery();
            rs.next();
            String path = rs.getString(1);
            return path;
        }
        catch (SQLException ex){
            System.out.println("getDirectory");
        }

        return null;
    }

}