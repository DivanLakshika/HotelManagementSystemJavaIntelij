package Example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class example extends JFrame{
    private JPanel panel1;
    private JTable table1;

    public example(){
        createtable();

    }

    private void createtable(){

       table1.setModel(new DefaultTableModel(null,new String[]{"Id","Name","Mobile Number","Nationality","Gender","Email","Id Proof","Address","Check In","Room No", "Bed", "Room type", "Price Per Day", "Number of Days Stays","Total Amount","Check Out" }));

    }

    public static void main(String[] args) {
        example ex1 =new example();
        ex1.setContentPane(ex1.panel1);
        ex1.setTitle("Example");
        ex1.setBounds(600,200,1000,600);
        ex1.setVisible(true);
        ex1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
