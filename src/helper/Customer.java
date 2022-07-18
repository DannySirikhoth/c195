/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer {
    private String id;
    private String name;
    private String address;
    private String zip;
    private String phone;


    public Customer() {

    }

    public Customer(String id, String name, String address, String zip, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.phone = phone;

    }
    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone(){ return phone;}

    public static int addNewCustomer(String name, String address, Integer zipcode) throws SQLException {

        String sql = " INSERT into Customers(Customer_name,Address, Postal_code) Values(?,?,?)";
        PreparedStatement ps = JDBCDAO.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setInt(3, zipcode);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }
    public static int updateCustomer(int customerID, String name, String address, String zipcode, String phone, int DivisionID) throws SQLException {

        String sql = " Update Customers Set Customer_name =?, Address =?, Postal_Code =?, Phone =?, Division_ID =? Where Customer_id = ?";
        PreparedStatement ps = JDBCDAO.connection.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, zipcode);
        ps.setString(4,phone);
        ps.setInt(5,DivisionID);
        ps.setInt(6,customerID);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
    public static int deleteCustomer(int customer_id) throws SQLException {

        String sql = "DELETE FROM Customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBCDAO.connection.prepareStatement(sql);
        ps.setInt(1,customer_id);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;

    }



}