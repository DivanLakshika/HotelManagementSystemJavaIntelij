package Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class adiminHome extends JDialog {

    private JTextField tfSearch;
    private JButton btnSearch;
    private JButton btnClear;
    private JTable tableSearch;
    private JButton btnLogout;
    private JPanel adminHomePanel;


    public adiminHome(JFrame parent) {


    super(parent);
    setTitle("Admin Panel");
    setContentPane(adminHomePanel);
    setMinimumSize(new Dimension(1366, 768));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

   /* JLabel background;
    setSize(1366,768);
    setLayout(null);
    ImageIcon img = new ImageIcon("admin Home.png");
    background = new JLabel("",img,JLabel.CENTER);
    background.setBounds(0,0,1366,768);
    add(background); */



    btnLogout.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            int a =JOptionPane.showConfirmDialog(null,"Do you really want to logout?","Select",JOptionPane.YES_NO_OPTION);
            if(a==0);
            {
                setVisible(false);
                new login(new JFrame()).setVisible(true);
            };
        }
    });
    btnClear.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new adiminHome(new JFrame()).setVisible(true);
        }
    });


       tableSearch.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                tableSearch.setModel(new DefaultTableModel(null,new String[]{"Name","Email","Security Question", "Address","Status"}));
                ResultSet rs=select.getData("select *from users");
                DefaultTableModel model=(DefaultTableModel)tableSearch.getModel();
                model.setRowCount(0);

                try{
                    while (rs.next()){
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7)});

                    }
                    rs.close();
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1);
                }
            }
        });



        adminHomePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);


            }
        });



        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nameOrEmail= tfSearch.getText();
                ResultSet rs=select.getData("select *from users where name like '%"+nameOrEmail+"%' or email like '%"+nameOrEmail+"%' ");
                DefaultTableModel model=(DefaultTableModel)tableSearch.getModel();
                model.setRowCount(0);

                try{
                    while (rs.next()){
                        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7)});

                    }
                    rs.close();
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1);
                }


            }
        });
        tableSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = tableSearch.getSelectedRow();
                TableModel model =tableSearch.getModel();
                String email = model.getValueAt(index,1).toString();
                String status = model.getValueAt(index,4).toString();
                    if (status.equals("true"))
                        status="false";
                    else status="true";

                    try{

                       int a=JOptionPane.showConfirmDialog(null,"Do you want to cahange status of "+email+"","select",JOptionPane.YES_NO_OPTION);
                        if(a==0){
                            InsertUpdateDelete.setData("update users set status='"+status+"' where email='"+email+"' ","Status changed successfully");
                            setVisible(false);
                            new adiminHome(new JFrame()).setVisible(true);
                        }
                    }
                    catch (Exception e1){
                        JOptionPane.showMessageDialog(null,e1);
                    }

            }
        });
        adminHomePanel.addComponentListener(new ComponentAdapter() {
        });
        adminHomePanel.addComponentListener(new ComponentAdapter() {
        });
    }




    public static void main(String[] args) {
        adiminHome ad=new adiminHome(null);
        ad.setVisible(true);




    }

   // private void createUIComponents() {
        // private void visible(){



       // tableSearch.setVisible(true);
        // };

        // TODO: place custom component creation code here
  //  }

}
