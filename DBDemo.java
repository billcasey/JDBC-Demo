package dbdemo;

import java.sql.*;
import java.util.Scanner;

/**
 * A simple class to access/update a local mySQL database.
 * 
 * It connects to the database, and reads out the results of the 'People' table.
 * The purpose is to teach myself how to use JDBC.
 *
 * @author Bill Casey
 */
public class DBDemo
{
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;    
   
    /**
     * Constructor which initializes the connection and statement objects.
     * Connects using the username/password given as parameters by the user.
     * 
     * @param dbUserName The username that will be used for the connection attempt.
     * @param dbUserPassword The password for the username in the database.
     */
    public DBDemo(String dbUserName, String dbUserPassword)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); //This is so you don't have to use fully qualified names. Not required for connection.
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBCDemo", dbUserName, dbUserPassword);
            statement = connect.createStatement();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void readDatabase() throws Exception 
    {
        //Connect to the database with credentials
        try
        {
            //create query
            //execute query
            
            //while loop to print the results
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        System.out.println("Enter password for DB Username \"bill\"");
        Scanner kb = new Scanner(System.in);
        DBDemo dbd = new DBDemo("bill", kb.nextLine());
        
        try
        {
            dbd.readDatabase();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        kb.close();
    }
}
