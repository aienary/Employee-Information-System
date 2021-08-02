/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaltestf1014;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class PracticalTestF1014 extends JFrame{
    JLabel lblname, lblic, lblphone, lblpos, lbldisplay;
    JTextField txtname, txtic, txtphone;
    JComboBox cbopos;
    JButton btnadd, btnclear, btnview;
    JTextArea tarea, display;
    ButtonGroup bg;
    String pos[]={"Manager", "Clark", "Designer"};
    
    String driver ="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/employee";
    String uname="root";
    String pass="";
    
PracticalTestF1014(){
    setLayout(new FlowLayout());
    setTitle("Practical Test");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);

    Font font = new Font("Arial", Font.BOLD,14);
    //---TAJUK
    JLabel tjk = new JLabel("EMPLOYEE INFORMATION SYSTEM");
    tjk.setFont(new Font("Dialog",Font.BOLD,20));
    tjk.setForeground(Color.black);
    add(tjk);
    //setBound(x,y,width,height);
    tjk.setBounds(200,30,500,40);
    
    bg = new ButtonGroup();

    lblname = new JLabel("NAME: ");
    lblname.setFont(font);
    add(lblname);
    lblname.setBounds(30,80,130,25);

    txtname = new JTextField(10);
    add(txtname);
    txtname.setBounds(200,80,250,25);

    lblic = new JLabel("IC: ");
    lblic.setFont(font);
    add(lblic);
    lblic.setBounds(30,110,130,25);

    txtic = new JTextField(10);
    add(txtic);
    txtic.setBounds(200,110,250,25);
    
    lblphone = new JLabel("PHONE NO: ");
    lblphone.setFont(font);
    add(lblphone);
    lblphone.setBounds(30,140,130,25);

    txtphone = new JTextField(10);
    add(txtphone);
    txtphone.setBounds(200,140,250,25);
    
    lblpos = new JLabel("POSITION: ");
    lblpos.setFont(font);
    add(lblpos);
    lblpos.setBounds(30,170,130,25);

    cbopos = new JComboBox(pos);
    add(cbopos);
    cbopos.setBounds(200,170,150,25);
    
    //--buttonAdd
    btnadd = new JButton("ADD");
    add(btnadd);
    btnadd.setBounds(30,210,90,30);
    btnadd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
            String name = txtname.getText();
            String ic = txtic.getText();
            String phone = txtphone.getText();
            //combo box guna getSelectedItem
            String pos = (String) cbopos.getSelectedItem();
            
            //database
                try {
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(url,uname,pass);
                Statement stat = conn.createStatement();
                
                String sql = "Insert into employee_details values ('"+name+"', " 
                        + "'"+ic+"', '"+phone+"', '"+pos+"') ";
                stat.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Data Sucessfully Inserted!");
                
                display.append("Name: " +name+"\n");
                display.append("Ic: "+ic+"\n");
                display.append("Phone No: " +phone+"\n");
                display.append("Position: "+pos+"\n");
                
                }catch(Exception Ex){
                    JOptionPane.showMessageDialog(null, "Error:" + Ex.getMessage());
                }
        }
    });
    
    //--btnClear
    btnclear = new JButton("CLEAR");
    add(btnclear);
    btnclear.setBounds(130,210,90,30);
    btnclear.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            txtname.setText("");
            txtic.setText("");
            txtphone.setText("");
            bg.clearSelection();
            cbopos.setSelectedIndex(0);
        } 
    });
    
    //--btnview
    btnview = new JButton("VIEW ALL");
    add(btnview );
    btnview.setBounds(230,210,90,30);
    btnview.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = txtname.getText();
            String ic = txtic.getText();
            String phone = txtphone.getText();
            String pos = (String) cbopos.getSelectedItem();
            
            try{
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(url,uname,pass);
                Statement stat = conn.createStatement();
                
                String sql = "Select * from employee_details";
                ResultSet rs = stat.executeQuery(sql);
                
                while(rs.next()){ //loop
                //
                display.append("Name: " +rs.getString(1)+"\n");
                display.append("Ic: " +rs.getString(2)+"\n");
                display.append("Phone No: "  +rs.getString(3)+"\n");
                display.append("Position: " +rs.getString(4)+"\n\n\n");
                }
                
                }catch(Exception Ex){
                    JOptionPane.showMessageDialog(null, "Error:" + Ex.getMessage());
                }
                }
           });
    
    lbldisplay = new JLabel();
    add(lbldisplay);
    lbldisplay.setBounds(30,360,300,25);
        

    display = new JTextArea();
    JScrollPane ScrollPane = new JScrollPane (display,
    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    add(ScrollPane);
    ScrollPane.setBounds(550,80,250,300);

    setVisible(true);
    setSize(850,450);
    
}
public static void main(String[] args) {
     PracticalTestF1014 palt = new PracticalTestF1014();
 }
}
