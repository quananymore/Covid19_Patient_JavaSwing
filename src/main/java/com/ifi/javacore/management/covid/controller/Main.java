package com.ifi.javacore.management.covid.controller;

import com.ifi.javacore.management.covid.model.Patient;
import com.ifi.javacore.management.covid.view.DetailPatient.Detail;
import com.ifi.javacore.management.covid.view.LoginPage.LoginPage;
import com.ifi.javacore.management.covid.view.mainPage.PatientPage2;
import org.apache.log4j.Logger;

import java.util.List;

public class Main {
    final static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
////        new LoginPage();
    new PatientPage2().setVisible(true);
    }
}
