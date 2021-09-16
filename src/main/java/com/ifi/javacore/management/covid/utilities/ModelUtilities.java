package com.ifi.javacore.management.covid.utilities;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class ModelUtilities {
    private String pathModel = System.getProperty("user.dir")+"\\src\\main\\java\\com\\ifi\\javacore\\management\\covid\\model\\";

    public JSONArray getArrayObject (String fileName){
        if(fileName == null||"".equals(fileName)){
            return null;
        }
        String filePath = pathModel+fileName;
        JSONParser jsonParser = new JSONParser();
        JSONArray a = new JSONArray();
        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file

            a = (JSONArray) jsonParser.parse(reader);

            return a;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;
    }

}
