package com.ifi.javacore.management.covid.view.mainPage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ifi.javacore.management.covid.controller.ApachePOIController;
import com.ifi.javacore.management.covid.controller.Main;
import com.ifi.javacore.management.covid.controller.PatientController;
import com.ifi.javacore.management.covid.model.Patient;
import com.ifi.javacore.management.covid.utilities.IdCounter;
import com.ifi.javacore.management.covid.utilities.ModelUtilities;
import com.ifi.javacore.management.covid.utilities.Validate;
import com.ifi.javacore.management.covid.view.DetailPatient.Detail;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class PatientPage2 extends JFrame {

    final static Logger log4j = Logger.getLogger(PatientPage2.class);

    private JPanel panel1;
    private  JTextField txtName = new JTextField();
    private  JComboBox gender = new JComboBox(new String[] {null,"Male", "Female"});
    private JTable tblPatient = new JTable();
    private JButton btnAdd = new JButton("Add");
    private JButton btnSearch = new JButton("Search");
    private JButton btnUploadImg = new JButton("Upload");
    private JButton btnDel = new JButton("Delete");
    private JComboBox injectionTime  = new JComboBox(new Integer[]{null,1,2});
    private  JTextField txtStatus = new JTextField();
    private  JTextField txtAge = new JTextField();
    private  JTextField txtImg = new JTextField();

    private JScrollPane jScrollPane = new JScrollPane();
    private static DefaultTableModel tableModel;

    private File file;
    public PatientPage2() {
        initComponents();
    }

    private void initComponents() {
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel pnlInfo = new JPanel();
        pnlInfo.setLayout(new BorderLayout());
        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new GridLayout(0,2));

        pnlForm.add(createInfoField(txtName, "Name"));
        pnlForm.add(createInfoField(txtAge, "Age"));
        pnlForm.add(createInfoField(gender, "Gender"));
        pnlForm.add(createInfoField(txtStatus, "Status"));
        pnlForm.add(createInfoField(injectionTime, "Injection times"));
        pnlForm.add(createInfoField(btnUploadImg, "Image"));
//        pnlForm.add(createInfoField(btnUploadImg,""));
        pnlInfo.add(pnlForm, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(1, 3));

        pnlButtons.add(btnAdd);
//        pnlButtons.add(btnSearch);
        pnlButtons.add(btnDel);
        pnlButtons.setPreferredSize(new Dimension(0, 50));
        pnlInfo.add(pnlButtons, BorderLayout.SOUTH);

        pnlForm.setPreferredSize(new Dimension(0, 300));
        add(pnlInfo, BorderLayout.NORTH);

        jScrollPane.setViewportView(tblPatient);

        initTable();

        add(jScrollPane, BorderLayout.CENTER);

        showPatient();
        initEvents();

    }

    private void initEvents() {
        tblPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblPatient.getSelectedRow();
                if (row >= 0 && e.getClickCount() == 2) {
                    Patient patient = PatientController.findById((Long)tableModel.getValueAt(row, 0));
                    Detail.getInstance(patient).setVisible(true);
                }
            }
        });

        btnAdd.addActionListener(e->{
            addPatient();
        });
        btnDel.addActionListener(e->{
            int row = tblPatient.getSelectedRow();
            Patient patient = PatientController.findById((Long)tableModel.getValueAt(row, 0));
//            System.out.println(patient.getName());
            deletePatient(patient);
        });

        btnUploadImg.addActionListener(e->{
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            file = chooser.getSelectedFile();
        });



    }

    private void deletePatient(Patient patient) {
        List<Patient> patients = PatientController.findAll();
        int index=0;
        for(Patient a : patients){
            if(a.getId() == patient.getId()){
                index = patients.indexOf(a);
                break;
            }
//            patients1.add(a);
        }
//        System.out.println(index);
        patients.remove(index);
        PatientController.writeJson(patients);
        log4j.info("Removing patient: ID "+patient.getId()+", Name: "+patient.getName());
        log4j.info("Remove successful!");
        showPatient();
        clearForm();
    }

    public static void showPatient() {
        List<Patient> patientList = PatientController.findAll();
        tableModel.setRowCount(0);

        patientList.forEach((patient) -> {
            tableModel.addRow(new Object[] {patient.getId(),
                    patient.getName(), patient.getGender(), patient.getAge(),patient.getStatus(),patient.getInjectionTimes(),patient.getDetectDate()});
        });
    }


    private void initTable() {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Long.class;
                    case 1:
                    case 2:
                    case 4:
                        return String.class;
                    case 3:
                    case 5:
                        return Integer.class;
                    case 6:
                        return Date.class;
                    default:
                        return Object.class;
                }
            }
        };
        String [] cols = {
                "ID", "Name", "Gender", "Age", "Status","Injection Times", "Detect date"
        };
        tableModel.setColumnIdentifiers(cols);
        tblPatient.setModel(tableModel);
    }

    private JPanel createInfoField(JComponent field, String label) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel lbl = new JLabel(label);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lbl, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.1;
        gbc.weightx = 0.4;
        panel.add(field, gbc);
        return panel;
    }

    private void addPatient() {
        List<Patient> patientList = PatientController.findAll();
        Patient patient = new Patient();
        patient.setId(IdCounter.getAndIncrement());
        if(Validate.validateString(txtName.getText(),"Patient name")){
            patient.setName(txtName.getText());
        };

        if(Validate.validateDigit(txtAge.getText(),"Patient Age")){
            patient.setAge(Integer.valueOf(txtAge.getText()));
        };
        if(file==null){
            JOptionPane.showMessageDialog(new JFrame(),
                    "Please upload image!",
                    "Inane warning",
                    JOptionPane.PLAIN_MESSAGE);
        }
        patient.setImage(saveImg());
        if(Validate.validateString(String.valueOf(gender.getSelectedItem()),"Patient gender")){
            patient.setGender(String.valueOf(gender.getSelectedItem()));
        };
        if(Validate.validateString(String.valueOf(injectionTime.getSelectedItem()),"Patient injection times")){
            patient.setInjectionTimes((int)injectionTime.getSelectedItem());
        };
        if(Validate.validateString(txtStatus.getText(),"Patient status")){
            patient.setStatus(txtStatus.getText());
        };

        Date date = new Date();
        patient.setDetectDate(date);
        patient.setInjectionTimes((int)(injectionTime.getSelectedItem()));
        patientList.add(patient);
        PatientController.writeJson(patientList);
//writeLogs
        log4j.info("Add patient: ID "+patient.getId()+", Name: "+patient.getName());
        log4j.info("Insert patient successful!");
        showPatient();
        clearForm();
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

    private void clearForm() {
        txtAge.setText("");
        txtName.setText("");
        txtStatus.setText("");
        txtImg.setText("");
    }



}
