package Project;

import org.w3c.dom.CDATASection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class cutomerDetailsBil extends JDialog {
    private JTextField tfSearchByCheckOutDate;
    private JButton btnSearch;
    private JTable tableCustomerDetailsBill;
    private JPanel panelCustomerDetailsBill;

    public cutomerDetailsBil(JFrame parent){
        super(parent);
        setTitle("Customer Details Bill");
        setContentPane(panelCustomerDetailsBill);
        setMinimumSize(new Dimension(1000,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        SimpleDateFormat myFormat=new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal=Calendar.getInstance();
        tfSearchByCheckOutDate.setText(myFormat.format((cal.getTime())));


        panelCustomerDetailsBill.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);

                ResultSet rs = select.getData("select *from customer where checkOut is not NULL");
                DefaultTableModel model = (DefaultTableModel) tableCustomerDetailsBill.getModel();
                try {

                    while (rs.next()) {

                        model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16)});
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }



            } } );
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkOutDate=tfSearchByCheckOutDate.getText();
                ResultSet rs =select.getData("select *from customer where checkOut='"+checkOutDate+"'");
                DefaultTableModel model =(DefaultTableModel)tableCustomerDetailsBill.getModel();
                model.setRowCount(0);//before search table should be null, if not search result can be not effective because other details can see
                try   {

                    while (rs.next()){

                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)});
                    }

                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1);
                }

            }
        });
        tableCustomerDetailsBill.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index =tableCustomerDetailsBill.getSelectedRow();
                TableModel model=tableCustomerDetailsBill.getModel();
                String id=model.getValueAt(index,0).toString();

                try{
                    if ((new File("C:\\Users\\Paboda\\Desktop\\Divan\\"+id+".pdf")).exists()) {
                        Process p =Runtime
                                .getRuntime()
                                .exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\Paboda\\Desktop\\Divan\\"+id+".pdf");
                    }

                    else {
                        JOptionPane.showMessageDialog(null,"File is not exist");
                    }

                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1);
                }

            }
        });

       createtableInForm();

      // OnlytableShow();
    }


  /*  private void OnlytableShow(){                    //Long method
        String checkOutDate=tfSearchByCheckOutDate.getText();
        ResultSet rs =select.getData("select *from customer where checkOut='"+checkOutDate+"'");
        DefaultTableModel model =(DefaultTableModel)tableCustomerDetailsBill.getModel();
        model.setRowCount(0);//before search table should be null, if not search result can be not effective because other details can see
        try   {

            while (rs.next()){

                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)});
            }

        }
        catch (Exception e1){
            JOptionPane.showMessageDialog(null,e1);
        }

}; */

    private void createtableInForm(){

        tableCustomerDetailsBill.setModel(new DefaultTableModel(null,new String[]{"Id","Name","Mobile Number","Nationality","Gender","Email","Id Proof","Address","Check In","Room No","Bed","Room type","Price Per Day","Number of Days Stays","Total Amount","Check Out"}));

        String checkOutDate=tfSearchByCheckOutDate.getText();
        ResultSet rs =select.getData("select *from customer where checkOut='"+checkOutDate+"'");
        DefaultTableModel model =(DefaultTableModel)tableCustomerDetailsBill.getModel();
        model.setRowCount(0);//before search table should be null, if not search result can be not effective because other details can see
        try   {

            while (rs.next()){

                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)});
            }

        }
        catch (Exception e1){
            JOptionPane.showMessageDialog(null,e1);
        }
        }

    /*private void createtableInForm(){                                                             //wrong method
        String checkOutDate=tfSearchByCheckOutDate.getText();
        ResultSet rs =select.getData("select *from customer where checkOut='"+checkOutDate+"'");
        try {
            while (rs.next()) {
                Object[][] data = {{rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16)}};

                tableCustomerDetailsBill.setModel(new DefaultTableModel(data, new String[]{"Id", "Name", "Mobile Number", "Nationality", "Gender", "Email", "Id Proof", "Address", "Check In", "Room No", "Bed", "Room type", "Price Per Day", "Number of Days Stays", "Total Amount", "Check Out"}));
            }
        }
        catch(Exception  e){JOptionPane.showMessageDialog(null,e);}
    }
*/


    public static void main(String[] args) {
        cutomerDetailsBil cb1 =new cutomerDetailsBil(null);
        cb1.setVisible(true);
    }
}

