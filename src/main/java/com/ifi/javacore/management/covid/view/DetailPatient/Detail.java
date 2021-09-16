/*
 * Created by JFormDesigner on Tue Sep 14 14:36:48 ICT 2021
 */

package com.ifi.javacore.management.covid.view.DetailPatient;

import com.ifi.javacore.management.covid.controller.ApachePOIController;
import com.ifi.javacore.management.covid.controller.Main;
import com.ifi.javacore.management.covid.controller.PatientController;
import com.ifi.javacore.management.covid.model.Patient;
import com.ifi.javacore.management.covid.utilities.DateUtilities;
import com.ifi.javacore.management.covid.utilities.Validate;
import com.ifi.javacore.management.covid.view.mainPage.PatientPage2;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author unknown
 */
public class Detail extends JFrame {
    final static Logger logger = Logger.getLogger(Detail.class);
    public Detail(Patient patient) {
        initComponents(patient);
    }

    private static Detail instance = null;
    final static Logger log4j = Logger.getLogger(Detail.class);

    private Patient patientInit;
    private File file;

    public static Detail getInstance(Patient patient) {
            instance = new Detail(patient);
        return instance;
    }
    private void initComponents(Patient patient) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel2 = new JPanel();
        label1 = new JLabel();
        txtID = new JTextField();
        txtName = new JTextField();
        label2 = new JLabel();
        label4 = new JLabel();
        textField3 = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        textField4 = new JTextField();
        label7 = new JLabel();
        txtInjectimes = new JTextField();
        txtAge = new JTextField();
        txtDetectDate = new JTextField();
        label8 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        panel3 = new JPanel();
        label9 = new JLabel();

        patientInit = patient;
        fillForm(patient);
        initEvent();
        initEvent();

        //======== this ========
        var contentPane = getContentPane();

        //======== panel2 ========
        {
            panel2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder( 0
            , 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
            , new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,
            panel2. getBorder( )) ); panel2. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //---- label1 ----
            label1.setText("ID");

            //---- label2 ----
            label2.setText("Name");

            //---- label4 ----
            label4.setText("Gender");

            //---- label5 ----
            label5.setText("Status");

            //---- label6 ----
            label6.setText("Age");

            //---- label7 ----
            label7.setText("Detect Date");

            //---- label8 ----
            label8.setText("Injection Times");

            //---- button1 ----
            button1.setText("Export Information");

            //---- button2 ----
            button2.setText("Update");

            //---- button3 ----
            button3.setText("Upload image");

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(button1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(label7)
                                .addGap(23, 23, 23)
                                .addComponent(txtDetectDate, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(0, 358, Short.MAX_VALUE)
                                        .addComponent(button3))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(textField4, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(label4, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                                    .addComponent(label1, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                                                .addGroup(panel2Layout.createParallelGroup()
                                                    .addGroup(panel2Layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtID, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                                    .addGroup(panel2Layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(textField3, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))))
                                        .addGap(18, 18, 18)
                                        .addGroup(panel2Layout.createParallelGroup()
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addGroup(panel2Layout.createParallelGroup()
                                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                                    .addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(29, 29, 29))
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addComponent(label8)
                                                .addGap(22, 22, 22)))
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtInjectimes, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                            .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                            .addComponent(txtAge, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))))
                                .addGap(37, 37, 37))))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2))
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4)
                            .addComponent(label6))
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5)
                            .addComponent(label8)
                            .addComponent(txtInjectimes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label7)
                            .addComponent(txtDetectDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(button3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button1)
                            .addComponent(button2))
                        .addGap(71, 71, 71))
            );
        }

        //======== panel3 ========
        {

            //---- label9 ----
            label9.setText(".");

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                        .addContainerGap(70, Short.MAX_VALUE)
                        .addComponent(label9)
                        .addGap(38, 38, 38))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(label9)
                        .addContainerGap(171, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void initEvent() {
        button1.addActionListener(e->{
            ApachePOIController.exportWord(patientInit);
        });

        button2.addActionListener(e->{
            updatePatient();
        });
        button3.addActionListener(e->{
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            file = chooser.getSelectedFile();
//            try{
//                label9 = new JLabel(new ImageIcon(ImageIO.read(new File(file.getAbsolutePath()))));
//            }catch (Exception ex){
//
//            }
        });
    }

    private void fillForm(Patient patient) {
        txtID.setText(String.valueOf(patient.getId()));
        txtName.setText(patient.getName());
        txtAge.setText(String.valueOf(patient.getAge()));
        textField4.setText(patient.getStatus());
        txtDetectDate.setText(DateUtilities.DateToString2(patient.getDetectDate()));
        txtInjectimes.setText(String.valueOf(patient.getInjectionTimes()));
        textField3.setText(patient.getGender());
        txtID.setEditable(false);
        txtAge.setEditable(false);
        textField3.setEditable(false);
        txtAge.setEditable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try{
            BufferedImage myPicture = ImageIO.read(new File(patient.getImage()));
            label9 = new JLabel(new ImageIcon(myPicture));
        }catch (Exception e){

        }
    }

    private void updatePatient() {
        Patient pt = new Patient();

        if(Validate.validateString(txtName.getText(),"Patient name")){
            pt.setName(txtName.getText());
        }

        if(Validate.validateDigit(txtAge.getText(),"Patient Age")) pt.setAge(Integer.valueOf(txtAge.getText()));

        pt.setGender(patientInit.getGender());
        if(Validate.validateDigit(txtInjectimes.getText(),"Patient injection times"))
            pt.setInjectionTimes(Integer.parseInt(txtInjectimes.getText()));
        if(Validate.validateString(textField4.getText(),"Patient status")) pt.setStatus(textField4.getText());
        System.out.println("Updating patient");
        pt.setId(Long.parseLong(txtID.getText()));
//        txtID.getText();
//        pt.setDetectDate(DateUtilities.StringToDate2(txtDetectDate.getText()));
        pt.setDetectDate(patientInit.getDetectDate());
        if(file!=null){
            pt.setImage(saveImg());
        }else{
            pt.setImage(patientInit.getImage());
        }
        List<Patient> patients = new ArrayList();

        for(Patient a : PatientController.findAll()){
            if(a.getId() == pt.getId()){
                patients.add(pt);
                continue;
            }
            patients.add(a);
        }

        PatientController.writeJson(patients);
        log4j.info("Update patient: ID "+pt.getId()+", Name: "+pt.getName());
        patientInit = pt;
        fillForm(pt);
        PatientPage2.showPatient();
    }
    private String saveImg() {
        BufferedImage bImage = null;
        String fileName = "D:\\IFI-Solution\\Project\\Covid_tracking\\src\\main\\java\\com\\ifi\\javacore\\management\\covid\\image\\"+file.getName();
        log4j.info("Image location to write: "+fileName);
        try {
            bImage = ImageIO.read(file);
            ImageIO.write(bImage, "jpg", new File(fileName));
        } catch (IOException e) {
            log4j.error("Exception occured :" + e.getMessage());
        }
        log4j.info("Images were written succesfully.");
        return fileName;

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel2;
    private JLabel label1;
    private JTextField txtID;
    private JTextField txtName;
    private JLabel label2;
    private JLabel label4;
    private JTextField textField3;
    private JLabel label5;
    private JLabel label6;
    private JTextField textField4;
    private JLabel label7;
    private JTextField txtInjectimes;
    private JTextField txtAge;
    private JTextField txtDetectDate;
    private JLabel label8;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel panel3;
    private JLabel label9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
