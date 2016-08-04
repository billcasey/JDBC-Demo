package dbdemo;

import java.sql.*;
import java.util.Scanner;

/**
 * A simple class to access a local mySQL database.
 * 
 * It connects to the database and reads out the results of the 'People' table.
 * The purpose is to teach myself how to use JDBC.
 * 
 * I will add additional methods to learn more about adding records, updating values, and removing rows in the future.
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
        catch (Exception e) //Catch more specific exceptions? Or just read the stack trace?
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Selects all rows from the People table in the JDBCDemo Database.
     * The database only has one table. The only columns in that table are fName and lName.
     */
    public void readDatabase()
    {
        try
        {
            String query = "SELECT fName, lName FROM People;"; //Could have also used SELECT *
            resultSet = statement.executeQuery(query);
            
            //Print the query for reference, and print the column labels.
            System.out.println("\n" + query);
            System.out.println("Row\tFirst Name\tLast Name");
            
            //Print the rows
            while(resultSet.next()) //.next iterates through the result set.
            {
                System.out.print(
                        resultSet.getRow() + "\t" +
                        resultSet.getString("fName") + "\t\t" + 
                        resultSet.getString("lName") + "\n");
                //.getRow() returns the row's number, not the entire row itself. 
                //.getString() accepts either an integer for the column index, or the label of the column.
            }
        }
        catch (Exception e) //Catch more specific exceptions? Or just read the stack trace?
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Call to close and release resources from JDBC instances.
     * If they are not null, calls the close methods on the following fields
     *      connect
     *      statement
     *      resultSet
     */
    private void close()
    {
        try
        {
            if(connect != null)
                connect.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
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
        finally //Close the JDBC connections and close the scanner
        {
            dbd.close();
            kb.close();
        }
    }
}
