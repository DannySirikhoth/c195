package helper;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class JDBCDAO {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = UTC"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
     private static final String password = "Passw0rd!"; // Password
     public static Connection connection;  // Connection Interface


    public static void  openConnection()
    {

        try {
            Class.forName(driver); // Locate Driver
            System.out.println("before connection attempt");
            connection = DriverManager.getConnection(jdbcUrl, userName, password ); // Reference Connection object
            System.out.println("Connection successful!");

            //This sets the Division_ID default value to 1 for future crud operations
            Statement statement = connection.createStatement();
            statement.executeUpdate("Alter Table customers ALTER Division_ID SET DEFAULT 1;");
            System.out.println("Division ID SET ");


        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }

    }

    public static void closeConnection() throws SQLException {
        try
        {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }

    }

    public static Connection getConn(){

        return connection;
    }

    public static Boolean checkCredentials(String user,String pass){

        //verify this method is called
        System.out.println("check credentials called");

        //runs the query
        try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT User_name FROM users;");
                System.out.println("Check Credentials query ran");

            //goes through all values
            while(rs.next())
            {
                //prints all the usernames in the Client_schedule database
                //System.out.println(rs.getString("User_name"));
               if(rs.getString("User_name").equals(user) )
                   {
                       System.out.println("User found");

                    try
                        {
                            Statement statement2 = connection.createStatement();
                            ResultSet rs2 = statement2.executeQuery("SELECT Password FROM users;");
                            //System.out.println("users was found so password query ran");

                            while(rs2.next())
                            {
                                if(rs2.getString("Password").equals(pass) )
                                    {
                                        System.out.println("password and user match returning true");
                                        return true;
                                    }
                            }
                        }
                    catch(Exception e)
                        {
                            System.out.println("Error:" + e.getMessage());
                        }
                   }
            }
            }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }

        return false;
    }




}
