package Example;

import Project.ConnectionProvider;
import Project.select;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class dddd {

    public static void main(String[] args) {

         String r;


        String  roomNo = "101";

        try {
            ConnectionProvider.getcon();


            ResultSet rs = select.getData("select *from room where roomNo'" + roomNo + "'");
            while (rs.next()) {
                r= rs.getString(1);
                System.out.println(r);

            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1);
        }
    }


}
