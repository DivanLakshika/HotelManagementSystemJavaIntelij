package Example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class asd extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label1;

    public asd() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


  /*      JLabel background;
    setSize(1366,768);
    setLayout(null);
    ImageIcon img = new ImageIcon("admin Home.png");
    background = new JLabel("",img,JLabel.CENTER);
    background.setBounds(0,0,1366,768);
    add(background); */

    JLayeredPane layerdpane = new JLayeredPane();
    layerdpane.setBounds(0,0,1366,768);

    layerdpane.add(label1, JLayeredPane.DRAG_LAYER);
    layerdpane.add(contentPane,JLayeredPane.DEFAULT_LAYER);

        JFrame frame = new JFrame("JLayeredPane");
        frame.add(layerdpane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500,500));
        frame.setLayout(null);
        frame.setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        asd dialog = new asd();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
