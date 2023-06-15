package Project;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class select {

    public static ResultSet getData(String query){

        Connection con=null;
        Statement st = null;
        ResultSet rs= null;

        try{

            con=ConnectionProvider.getcon();
            st=con.createStatement();
            rs=st.executeQuery(query);
            return rs;

        }
        catch (Exception e){

            JOptionPane.showMessageDialog(null,e);
            return null;
        }

    }
}
