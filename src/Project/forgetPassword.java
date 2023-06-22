package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class forgetPassword extends JDialog {
    private JButton btnSave;
    private JButton btnSignup;
    private JButton btnLogin;
    private JTextField tfEmail;
    private JTextField tfSequrityQuestio;
    private JTextField tfAnswer;
    private JPasswordField pfNewpassword;
    private JButton btnSearch;
    private JPanel forgetPasswordPanel;
    String email;
    public forgetPassword(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(forgetPasswordPanel);
        setMinimumSize(new Dimension(800, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) { //chanege e to evt
                int check=0;
                email=tfEmail.getText();
                if(email.equals("")){
                    check =1;
                    JOptionPane.showMessageDialog(null,"Please enter email address");
                }
                else{
                    ResultSet rs = select.getData("select *from users where email='"+ email +"'");
                    try{
                        if(rs.next()){ //email is access

                            check=1;
                            tfEmail.setEditable(false);
                            tfSequrityQuestio.setEditable(false);
                            tfSequrityQuestio.setText(rs.getString(4));
                        }

                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null,e);
                    }
                }
                if (check ==0){
                    JOptionPane.showMessageDialog(null,"Incorrect email address");

                }

            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String email = tfEmail.getText();
                String password=String.valueOf(pfNewpassword.getPassword());
                String answer=tfAnswer.getText();
                String securityQuestion=tfSequrityQuestio.getText();
                int check=0;
                if ( email.equals("") || password.equals("") ||answer.equals("") ||securityQuestion.equals("")){
                   check=1;
                    JOptionPane.showMessageDialog(null,"Please required all fields");}

                else{
                    ResultSet rs=select.getData("select *from users where email= '"+email +"' and securityQuestion = '"+securityQuestion+"' and answer='"+answer+"'");
                    try{
                        if(rs.next()){ //if the user is correct
                           check=1;
                            InsertUpdateDelete.setData("update users set password='"+password+"' where email='"+email+"'","Password set successfully");
                            setVisible(false);
                            new forgetPassword(new JFrame()).setVisible(true);
                        }
                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null,e);
                    }

                }

                if(check==0){
                    JOptionPane.showMessageDialog(null,"Incorrect answer");
                }


            }
        });
        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SignUpPage(new JFrame()).setVisible(true);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new login(new JFrame()).setVisible(true); //if login method is private then can not work here
            }
        });
    }

    public static void main(String[] args) {
        forgetPassword fg1=new forgetPassword(null);
        fg1.setVisible(true);
    }

}
