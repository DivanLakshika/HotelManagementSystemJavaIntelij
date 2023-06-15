package Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import java.util.Vector;

public class manageRoom extends JDialog{
    private JTable manageRoomTable;
    private JTextField tfRoomNumber;
    private JComboBox cbRoomtype;
    private JTextField tfPrice;
    private JButton addRoomButton;
    private JPanel ManageRoomPanel;
    private JComboBox cbBed;

    public manageRoom(JFrame parent){
        super(parent);
        setTitle("Home");
        setContentPane(ManageRoomPanel);
        setMinimumSize(new Dimension(1000,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);





        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomNo= tfRoomNumber.getText();
                String roomType= String.valueOf(cbRoomtype.getSelectedItem());
                String bed=String.valueOf(cbBed.getSelectedItem());
                String price = tfPrice.getText();
                DefaultTableModel model=(DefaultTableModel)manageRoomTable.getModel();

               String Query ="insert into room values('"+roomNo+"','"+roomType+"','"+bed+"','"+price+"','Not Booked')";
               InsertUpdateDelete.setData(Query,"Successfully updated");
               setVisible(false);
               new manageRoom(new JFrame()).setVisible(true);

            }
        });

       /* manageRoomTable.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                manageRoomTable.setModel(new DefaultTableModel(null,new String[]{"Room Number","Room Type","Bed Type", "Price","Status"}));
            }
        });*/

     /*   manageRoomTable.addComponentListener(new ComponentAdapter() {     // method that is for table shown
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                ResultSet rs =select.getData("select *from room");
                DefaultTableModel model=(DefaultTableModel)manageRoomTable.getModel();
                try {
                    while (rs.next()){
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
                    }
                    manageRoomTable.setVisible(true);
                    rs.close();

                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1);
                }
            }
        });

        ManageRoomPanel.addComponentListener(new ComponentAdapter() {     // method that is for table shown
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                ResultSet rs =select.getData("select *from room");
                DefaultTableModel model=(DefaultTableModel)manageRoomTable.getModel();
                try {
                    while (rs.next()){
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
                    }

                    rs.close();

                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1);
                }
            }
        }); */

        createtabletoManageRoom();
    }


    private void createtabletoManageRoom(){

        manageRoomTable.setModel(new DefaultTableModel(null,new String[]{"Room Number","Room Type","Bed Type", "Price","Status"}));

        ResultSet rs =select.getData("select *from room");
        DefaultTableModel model=(DefaultTableModel)manageRoomTable.getModel();
        try {
            while (rs.next()){
                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
            }
            manageRoomTable.setVisible(true);
            rs.close();

        }
        catch (Exception e1){
            JOptionPane.showMessageDialog(null,e1);
        }

       /*tableCustomerDetailsBill.setModel(new DefaultTableModel(null,new String[]{"Id","Name","Mobile Number","Nationality","Gender","Email","Id Proof","Address","Check In","Room No","Bed","Room type","Price Per Day","Number of Days Stays","Total Amount","Check Out"}));

        String checkOutDate=tfSearchByCheckOutDate.getText();
        ResultSet rs =select.getData("select *from customer where checkOut='"+checkOutDate+"'");
        DefaultTableModel model =(DefaultTableModel)tableCustomerDetailsBill.getModel();
        // model.setRowCount(0);//before search table should be null, if not search result can be not effective because other details can see
        try   {

            while (rs.next()){

                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)});
            }

        }
        catch (Exception e1){
            JOptionPane.showMessageDialog(null,e1);
        } */
    }


    public static void main(String[] args) {
        manageRoom m1=new manageRoom(null);
        m1.setVisible(true);
    }
}
