package Project;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;

public class table {
    public static void main(String[] args) {

        Connection con= null;
        Statement stat =null;
        try{

            con = ConnectionProvider.getcon();
            stat = con.createStatement();
            stat.executeUpdate("create table users(name varchar(200), email varchar(200), password varchar(200), securityQuestion varchar(500), answer varchar(200), address varchar(200),status varchar(20))");
            stat.executeUpdate("create table room(roomNo varchar(10),roomType varchar(200),bed varchar(200),price int, status varchar(20))");
            stat.executeUpdate("create table customer(id int(10),name varchar(200),mobileNumber varchar(20),nationality varchar(200), gender varchar(200), email varchar(200), idProof varchar(200), address varchar(500), checkIn varchar(200), roomNo varchar(10), bed varchar(200), roomType varchar(200), pricePerDay int(10), numberOfDaysStay int(10),totalAmount varchar(200),checkOut varchar(50))");
            JOptionPane.showMessageDialog(null,"Table created successfully");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

        finally {
            try{
                con.close();
                stat.close();
            }
            catch (Exception e){}
        }
    }
}
