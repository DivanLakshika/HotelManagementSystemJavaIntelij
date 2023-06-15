package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JDialog {

    private JButton btncustomerCheckIn;
    private JButton btncustomerCheckOut;
    private JButton btncustomerDetailsBill;
    private JButton btnlogout;
    private JPanel HomePanel;
    private JButton btnmanageRoom;

    public Home(JFrame parent){
        super(parent);
        setTitle("Home");
        setContentPane(HomePanel);
        setMinimumSize(new Dimension(1366,768));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnlogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a= JOptionPane.showConfirmDialog(null,"Do you really want to logout","select",JOptionPane.YES_NO_OPTION);
                if (a == 0) {   //user said yes
                    setVisible(false);
                    new login(new JFrame()).setVisible(true);
                }

            }
        });
        btnmanageRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new manageRoom(new JFrame()).setVisible(true);
            }
        });

        btncustomerCheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new customerCheckOut(new JFrame()).setVisible(true);
            }
        });
        btncustomerCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerCheckIn(new JFrame()).setVisible(true);

            }
        });
        btncustomerDetailsBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cutomerDetailsBil(new JFrame()).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Home H1=new Home(null);
        H1.setVisible(true);
    }
}
