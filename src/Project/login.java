package Project;

import com.sun.source.tree.UsesTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import static com.sun.org.apache.xerces.internal.util.DOMUtil.setVisible;

public class login extends JDialog{
    private JTextField tfEmail;
    private JButton signUpButton;
    private JButton loginButton;
    private JButton forgetPasswordButton;
    private JPasswordField pfPassword;
    private JPanel loginPanel;

    public login(JFrame parent) {
    super(parent);
    setTitle("Login");
    setContentPane(loginPanel);
    setMinimumSize(new Dimension(500,400));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            //private void actionPerformed(ActionEvent e) {
            int check =0;
            String email=tfEmail.getText();
            String password= String.valueOf(pfPassword.getPassword());
            if(email.equals("") || password.equals("")){
                check=1;
                JOptionPane.showMessageDialog(null,"Please fill all requirements to the login ");
            }
            else if (email.equals("divan") && password.equals("admin")){
                check=1;
                setVisible(false);
                new adiminHome(new JFrame()).setVisible(true);
            }
            else
            {
                ResultSet rs = select.getData("select *from users where email='"+email+"' and password='"+password+"'");
                try{
                    if (rs.next())  //if value is okay
                    {
                        check=1;
                        if (rs.getString(7).equals("true")) {
                            setVisible(false);
                            new Home(new JFrame()).setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Wait for admin approval");
                        }
                    }
                    if (check==0){
                        JOptionPane.showMessageDialog(null,"Incorrect Email or Password");
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }

        }}
    });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SignUpPage(new JFrame()).setVisible(true);
            }
        });
        forgetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new forgetPassword(new JFrame()).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        login l1=new login(null);
        l1.setVisible(true);
    }
}
