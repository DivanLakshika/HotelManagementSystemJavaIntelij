package Project;

import javax.swing.*;
import java.sql.*;

public class InsertUpdateDelete {

    public static  void setData(String Query,String msg)
    {
        Connection con =null;
        Statement stat=null;

        try{
            con = ConnectionProvider.getcon();
            stat=con.createStatement();
            stat.executeUpdate(Query);

            if(!msg.equals(""))
                 JOptionPane.showMessageDialog(null,msg);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
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
