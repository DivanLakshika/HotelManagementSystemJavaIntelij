package Project;

import com.mysql.cj.protocol.a.NativeUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomerCheckIn extends JDialog{
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfNationality;
    private JTextField tfMobileNumber;
    private JComboBox cbGender;
    private JTextField tfIdProof;
    private JTextField tfAddress;
    private JTextField tfCheckInDate;
    private JComboBox cbRoomType;
    private JComboBox cbBed;
    private JComboBox cbRoomNumber;
    private JTextField tfPrice;
    private JButton btnalloteRoom;
    private JButton btnclear;
    private JPanel CustomerCheckInPanel;

    public CustomerCheckIn(JFrame parent){
        super(parent);
        setTitle("Customer Check-In");
        setContentPane(CustomerCheckInPanel);
        setMinimumSize(new Dimension(1366,768));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new CustomerCheckIn(new JFrame()).setVisible(true);
            }
        });

            tfCheckInDate.setEditable(false);
            tfPrice.setEditable(false);
            SimpleDateFormat myFormat=new SimpleDateFormat("yyyy/MM/dd");
            Calendar cal=Calendar.getInstance();
            tfCheckInDate.setText(myFormat.format(cal.getTime()));   //SET DATE

        cbBed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomDetails();

            }
        });
        cbRoomType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomDetails();

            }
        });
        cbRoomNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                roomNo=String.valueOf(cbRoomNumber.getSelectedItem());
                try{

                    ResultSet rs=select.getData("select *from room where roomNo='"+roomNo+"'");
                    while (rs.next())
                    {
                        tfPrice.setText(rs.getString(4));
                    }
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1);
                }
            }
        });

        btnalloteRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=1;
                String name=tfName.getText();
                String mobileNumber=tfMobileNumber.getText();
                String nationality=tfNationality.getText();
                String gender=String.valueOf(cbGender.getSelectedItem());
                String email=tfEmail.getText();
                String idProof=tfIdProof.getText();
                String address=tfAddress.getText();
                String checkIn=tfCheckInDate.getText();
                String bed=String.valueOf(cbBed.getSelectedItem());
                String roomType=String.valueOf(cbRoomType.getSelectedItem());
                String roomNo=String.valueOf(cbRoomNumber.getSelectedItem());
                String price=tfPrice.getText();
                String Query ="select max(id) from customer";
                try {

                    ResultSet rs = select.getData(Query);
                    while (rs.next()){
                        id=rs.getInt(1);
                        id=id+1;

                        if (!price.equals("")){

                            Query="update room set status='Booked' where roomNo='"+roomNo+"'";
                            InsertUpdateDelete.setData(Query,"");
                            Query="insert into customer(id,name,mobileNumber,nationality,gender,email,idProof,address,checkIn,roomNo,bed,roomType,pricePerDay) values('"+id+"','"+name+"','"+mobileNumber+"','"+nationality+"','"+gender+"','"+email+"','"+idProof+"','"+address+"','"+checkIn+"','"+roomNo+"','"+bed+"','"+roomType+"','"+price+"')";
                            InsertUpdateDelete.setData(Query,"Customer Check In Successfully");
                            setVisible(false);
                            new CustomerCheckIn(new JFrame()).setVisible(true);
                        }
                    }


                } catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1);
                }
            }
        });
    }

    String bed;
    String roomType;
    String roomNo;
    String price;

    public void roomDetails(){

        cbRoomNumber.removeAllItems();
        tfPrice.setText("");

        bed = String.valueOf(cbBed.getSelectedItem());
        roomType = String.valueOf(cbRoomType.getSelectedItem());
       // roomNo = String.valueOf(cbRoomNumber.getSelectedItem());

        try {
            ResultSet rs =select.getData("select *from room where bed='"+bed+"' and roomType='"+roomType+"' and status='Not Booked'");
            while (rs.next()){

                cbRoomNumber.addItem(rs.getString(1));

            };

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    };


    public static void main(String[] args) {

        CustomerCheckIn cu1=new CustomerCheckIn(null);
        cu1.setVisible(true);

    }

}
