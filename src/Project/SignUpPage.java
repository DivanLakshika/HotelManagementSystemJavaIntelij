package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage extends JDialog{

    private JButton btnSignup;
    private JButton btnLogin;
    private JButton btnForgetpassword;
    private JTextField tfName;
    private JTextField tfEmail;
    private JComboBox cbSequrityQuestion;
    private JPasswordField pfPassword;
    private JPanel SignupPanel;
    private JTextField tfAnswer;
    private JTextField tfAddress;


    public SignUpPage(JFrame parent) {

    super(parent);
    setTitle("SignUP");
    setContentPane(SignupPanel);
    setMinimumSize(new Dimension(1000,700));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);




    btnSignup.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

                String name = tfName.getText();
                String email= tfEmail.getText();
                String password= String.valueOf(pfPassword.getPassword());
                String answer = tfAnswer.getText();
                String securityQuestion=  String.valueOf(cbSequrityQuestion.getSelectedItem());
                String address= tfAddress.getText();
                if(name.equals("") || email.equals("") || password.equals("") || answer.equals("") || address.equals("")){
                    JOptionPane.showMessageDialog(null,"Every Field is Requried");
                }
                else {
                    String Query;
                    Query= "insert into users values('" +name+ "','"+ email +"','"+password +"','"+ securityQuestion+"','" +answer+"','"+address+"','false')";//user not able to login when registration happened that is why false only admin approved that
                    InsertUpdateDelete.setData(Query,"Registered Successfully");
                    setVisible(false);
                    new SignUpPage(new JFrame()).setVisible(true);

            }

        }
    });

      btnLogin.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              setVisible(false);
              new login(new JFrame()).setVisible(true);
          }
      });
      btnForgetpassword.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              setVisible(false);
              new forgetPassword(new JFrame()).setVisible(true);
          }
      });
  }

    public static void main(String[] args) {
        SignUpPage sp1=new SignUpPage(null);

        sp1.setVisible(true);

       }

}


