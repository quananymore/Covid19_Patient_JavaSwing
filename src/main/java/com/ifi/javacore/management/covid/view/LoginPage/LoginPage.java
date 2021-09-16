package com.ifi.javacore.management.covid.view.LoginPage;

import com.ifi.javacore.management.covid.controller.ApachePOIController;
import com.ifi.javacore.management.covid.controller.PatientController;
import com.ifi.javacore.management.covid.model.Patient;
import com.ifi.javacore.management.covid.utilities.ModelUtilities;
import com.ifi.javacore.management.covid.view.mainPage.PatientPage2;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;


public class LoginPage extends JFrame {
    final static Logger log4j = Logger.getLogger(LoginPage.class);
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JPanel panelCover;
    private JLabel message;
    private JFrame frame;
    public LoginPage(){

        frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300,300));
        frame.setResizable(false);

        //add the panel
        frame.add(panelCover);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                java.util.List<Patient> patients = PatientController.findAll();
                patients = PatientController.sort(patients);
                ApachePOIController.exportExcel(patients);
                System.out.println("Export Excel");
                log4j.info("Export Excel");
            }
        };
        Timer timer = new java.util.Timer();
        timer.schedule(timerTask, 1,10000);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txtUser = txtUsername.getText();
                String txtPass = txtPassword.getText();

                if (txtUser==null||"".equals(txtUser)){
                    message.setText("Please fill username");
                }
                if (txtPass==null||"".equals(txtPass)){
                    message.setText("Please fill password");
                }

                JSONArray a = new ModelUtilities().getArrayObject("user.json");
                for (Object o : a) {
                    JSONObject user = (JSONObject) o;

                    String username = (String) user.get("username");
                    String password = (String) user.get("password");
                    if (username.equals(txtUser)) {
                        if (password.equals((txtPass))) {
                            message.setText("Login successful!");
                            new PatientPage2().setVisible(true);
                            frame.dispose();
                            break;
                        }else {
                            message.setText("Login Fail!");
//                            JOptionPane.showMessageDialog(frame,"Login Fail!");
                        }
                    }
                }
                message.setText("Login Fail!");
            }
        });
    }

}
