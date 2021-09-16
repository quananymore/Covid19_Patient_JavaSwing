package com.ifi.javacore.management.covid.utilities;

import javax.swing.*;

public class Validate {
    public final static String pattern = "\\d+";
    public final static String patternSecondWay = "[0-9]+";

    public static boolean validateString(Object input,String name){
      if(input == null||"".equals(input.toString())){
          JOptionPane.showMessageDialog(null, "Please fill "+ name+" !");
          Thread.currentThread().stop();
          return false;
      }
      return true;
    }


    public static boolean validateDigit(Object input,String name){
        if(input == null||"".equals(input.toString())){
            JOptionPane.showMessageDialog(null,
                    "Please fill "+ name+" !");
            Thread.currentThread().stop();
            return false;
        }else if(!input.toString().matches(pattern)){
            JOptionPane.showMessageDialog(new JFrame(),
                    name+" must be number!",
                    "Validate",
                    JOptionPane.PLAIN_MESSAGE);
            Thread.currentThread().stop();
            return false;
        }
        return true;
    }
}
