package com.ifi.javacore.management.covid.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ifi.javacore.management.covid.model.Patient;
import com.ifi.javacore.management.covid.utilities.IdCounter;
import com.ifi.javacore.management.covid.utilities.ModelUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PatientController {
    public static final String pathModel = System.getProperty("user.dir")+"\\src\\main\\java\\com\\ifi\\javacore\\management\\covid\\model\\";
    public static List<Patient> findAll(){
        List<Patient> patientList = new ArrayList();
        JSONArray a = new ModelUtilities().getArrayObject("Patient.json");
        try{
            for (Object o : a) {
                JSONObject obj = (JSONObject) o;
                Patient patient = new Gson().fromJson(obj.toJSONString(), Patient.class);
                patientList.add(patient);
                IdCounter.updateCounter(patient.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return patientList;
    }

    public static  Patient findById(Long valueAt) {
        Patient patient = null;
        JSONArray a = new ModelUtilities().getArrayObject("Patient.json");
        try{
            for (Object o : a) {
                JSONObject obj = (JSONObject) o;
                patient = new Gson().fromJson(obj.toJSONString(), Patient.class);
                if(patient.getId()==valueAt){
                    return patient;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return patient;
    }

    public static List<Patient> sort(List<Patient> patients) {
        Collections.sort(patients, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                return o2.getDetectDate().compareTo(o1.getDetectDate());
            }
        });

        return patients;
    }

    public static void writeJson(List<Patient>patientList ) {
        /* Begin write to json */
        String path = pathModel + "Patient.json";
        try (FileWriter file = new FileWriter(new File(path))) {
            System.out.println("Begin write to json...");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonString = gson.toJson(patientList);
            file.write(prettyJsonString);
            file.flush();
            file.close();
            System.out.println("Write to json file successful!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
