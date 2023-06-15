package Project;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class customerCheckOut extends JDialog{
    private JTextField tfRoomNumberSearch;
    private JButton btnSearch;
    private JTextField tfCustomerName;
    private JTextField tfPricePerDay;
    private JTextField tfCheckIn;
    private JTextField tfNumberOfDaysStays;
    private JTextField tfCheckout;
    private JTextField tfTotalAmount;
    private JTextField tfCustomerMobileNumber;
    private JTextField tfEmail;
    private JTable tableCustomerCheckOut;
    private JButton btnCheckOut;
    private JButton btnClear;
    private JPanel CustomerCheckOutPanel;

    public customerCheckOut(JFrame parent){

        super(parent);
        setTitle("Customer Check Out Panel");
        setContentPane(CustomerCheckOutPanel);
        setMinimumSize(new Dimension(1000, 600 ));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        tfCustomerName.setEditable(false);
        tfCheckIn.setEditable(false);
        tfCheckout.setEditable(false);
        tfCustomerMobileNumber.setEditable(false);
        tfPricePerDay.setEditable(false);
        tfNumberOfDaysStays.setEditable(false);
        tfTotalAmount.setEditable(false);
        tfEmail.setEditable(false);



        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomNo=tfRoomNumberSearch.getText();
                try{
                    ResultSet rs=select.getData("select *from customer where roomNo='"+roomNo+"' and checkout is Null"); //checkOut  like this
                    if(rs.next()) {
                        tfRoomNumberSearch.setEditable(false);
                        id = rs.getInt(1);
                        tfCustomerName.setText(rs.getString(2));
                        tfCheckIn.setText(rs.getString(9));
                        tfCustomerMobileNumber.setText(rs.getString(3));
                        tfPricePerDay.setText(rs.getString(13));

                        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Calendar cal = Calendar.getInstance();
                        tfCheckout.setText(myFormat.format(cal.getTime()));
                        String dateBeforeString = rs.getString(9);
                        Date dateBefore = myFormat.parse(dateBeforeString);

                        String dateAfterString = myFormat.format(cal.getTime());
                        Date dateAfter = myFormat.parse(dateAfterString);
                        long difference = dateAfter.getTime() - dateBefore.getTime();
                        int noOfDayStay = (int) (difference / (1000 * 60 * 60 * 24));
                        if (noOfDayStay == 0)
                            noOfDayStay = 1;
                        tfNumberOfDaysStays.setText(String.valueOf(noOfDayStay));
                        float price = Float.parseFloat(tfPricePerDay.getText());

                        tfTotalAmount.setText(String.valueOf(noOfDayStay * price));
                        tfEmail.setText(rs.getString(6));
                        roomType = rs.getString(12);
                        bed = rs.getString(11);

                    }
                        else{
                            JOptionPane.showMessageDialog(null,"Room Number is not Booked or Room Number Does not Exist");

                        }




                }catch (Exception e1){JOptionPane.showMessageDialog(null,e1);}
            }
        });
        btnCheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String name = tfCustomerName.getText();
               String mobileNumber=tfCustomerMobileNumber.getText();
               String email=tfEmail.getText();

               String checkOut=tfCheckout.getText();
               String numberOfDaysStays=tfNumberOfDaysStays.getText();
               String totalAmount=tfTotalAmount.getText();
               roomNo= tfRoomNumberSearch.getText();
               Query ="update customer set numberOfDaysStay='"+numberOfDaysStays+"',totalAmount='"+totalAmount+"',checkOut='"+checkOut+"' where id='"+id+"'";
               InsertUpdateDelete.setData(Query,"");
               Query="update room set status='Not Booked' where roomNo='"+roomNo+"'";
               InsertUpdateDelete.setData(Query,"");
              // String path="C:\\";
                String path = "C:\\Users\\Paboda\\Desktop\\Divan\\";
               com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
               try {
                   PdfWriter.getInstance(doc, new FileOutputStream(path+""+id+".pdf"));
                   doc.open();
                   Paragraph paragraph1=new Paragraph("                                           Hotel Management System\n") ;
                   doc.add(paragraph1);
                   Paragraph paragraph2=new Paragraph("****************************************************************************************************************") ;
                   doc.add(paragraph2);
                   Paragraph paragraph3=new Paragraph("\tBill ID: "+id+"\nCustomer Details:\nName: "+name+"\nMobile Number: "+mobileNumber+"\nEmail: "+email+"\n   ") ;
                   doc.add(paragraph3);
                   doc.add(paragraph2);
                   Paragraph paragraph4=new Paragraph("\tRoom Details:\nNumber: "+tfRoomNumberSearch.getText()+"\nType: "+roomType+"\nBed: "+bed+"\nPrice Per Day: "+tfPricePerDay.getText()+"") ;
                   doc.add(paragraph4);
                   doc.add(paragraph2);
                   PdfPTable tb1=new PdfPTable(4);
                   tb1.addCell("Check In Date: "+tfCheckIn.getText());
                   tb1.addCell("Check Out Date: "+checkOut);
                   tb1.addCell("No of Days Stays: "+numberOfDaysStays);
                   tb1.addCell("Total Amount Paid: "+totalAmount);
                   doc.add(tb1);
                   doc.add(paragraph2);
                   Paragraph paragraph5=new Paragraph("Thank You, Please Visit Again.");
                   doc.add(paragraph5);


               }
               catch (Exception e1){
                   JOptionPane.showMessageDialog(null,e1);
               }
               doc.close();

               int a=JOptionPane.showConfirmDialog(null,"Do you want to Print Bill?","select",JOptionPane.YES_NO_OPTION);
               if(a==0){
                   try{
                       if ((new File("C:\\Users\\Paboda\\Desktop\\Divan\\"+id+".pdf")).exists())
                       {
                          Process p = Runtime
                                  .getRuntime()
                                  .exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\Paboda\\Desktop\\Divan\\"+id+".pdf");

                       }
                       else
                           System.out.println("File is not Exists");
                   }
                   catch (Exception e1){
                       JOptionPane.showMessageDialog(null,e1);
                   }
               }
               setVisible(false);
               new customerCheckOut(new JFrame()).setVisible(true);
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new customerCheckOut(new JFrame()).setVisible(true);
            }
        });
        tableCustomerCheckOut.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                tableCustomerCheckOut.setModel(new DefaultTableModel(null,new String[]{"Id","Name","Mobile Number","Nationality","Gender","Email","Id Proof","Address","Check In","Room No","Bed","Room type","Price Per Day"}));
                ResultSet rs = select.getData("select *from customer where checkout is NULL");
                DefaultTableModel model =(DefaultTableModel)tableCustomerCheckOut.getModel();
                try{
                    while (rs.next()){
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13)});
                    }
                    rs.close();
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1);
                }

            }
        });
    }

    int id=0;
    String Query;
    String roomType;

    String bed;
    String roomNo;


    public static void main(String[] args) {
        customerCheckOut c1=new customerCheckOut(null);
        c1.setVisible(true);
    }
}
