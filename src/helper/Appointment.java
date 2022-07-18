package helper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Appointment {

    //first 5
    private String AppID;
    private String title;
    private String description;
    private String location;
    private String type;

    //second 5
    private String start;
    private String end;
    private String customer_id;
    private String user_id;
    private String contact;

    public Appointment(String AppId, String title,String description, String location, String contact, String type, String start, String end, String customer_id, String user_id) {
        this.AppID = AppId;
        this.title = title;
        this.description= description;
        this.location = location;
        this.type = type;

        this.start = start;
        this.end = end;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.contact = contact;

    }

    public static int addNewAppointment (String title, String description, String location, String type,  Timestamp start, Timestamp end, int customer_id, int user_id, int div_id) throws SQLException {

        String sql = " INSERT into Appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) Values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBCDAO.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setString(5, String.valueOf(start));
        ps.setString(6, String.valueOf(end));
        ps.setInt(7,customer_id);
        ps.setInt(8,user_id);
        ps.setInt(9,div_id);
       // ps.setString(5, end);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static int deleteAppointment(int app_id) throws SQLException {

        String sql = "DELETE FROM Appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBCDAO.connection.prepareStatement(sql);
        ps.setInt(1,app_id);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static int updateAppointment(int AppointmentId, String title, String description, String location, String type, Timestamp start, Timestamp end, int customerId, int userId, int contactId) throws SQLException {

        String sql = " Update Appointments Set  Title =?, Description =?, Location =?, Type =? Start =?, End =?, Customer_ID =?, User_ID =?, Contact_ID =? Where Appointment_ID =?";
        PreparedStatement ps = JDBCDAO.connection.prepareStatement(sql);

        ps.setString(1,title);
        ps.setString(2,description);
        ps.setString(3,location);
        ps.setString(4,type);
        ps.setString(5, String.valueOf(start));
        ps.setString(6, String.valueOf(end));
        ps.setInt(7,customerId);
        ps.setInt(8,userId);
        ps.setInt(9,contactId);
        ps.setInt(10,AppointmentId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public String getAppID() {
        return AppID;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }
    public String getType() {
        return type;
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
    public String getCustomer_id() {
        return customer_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public String getContact() {
        return contact;
    }
}
