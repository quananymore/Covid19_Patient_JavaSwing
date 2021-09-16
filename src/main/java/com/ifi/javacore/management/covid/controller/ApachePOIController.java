package com.ifi.javacore.management.covid.controller;

import com.ifi.javacore.management.covid.model.Patient;
import com.ifi.javacore.management.covid.utilities.DateUtilities;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;


public class ApachePOIController {
    final static Logger logger = Logger.getLogger(ApachePOIController.class);
    final static String outFolder = "D:\\IFI-Solution\\Project\\Covid_tracking\\src\\main\\java\\com\\ifi\\javacore\\management\\covid\\output\\";
    final static String excelFilePath = "D:\\IFI-Solution\\Project\\Covid_tracking\\src\\main\\java\\com\\ifi\\javacore\\management\\covid\\output\\listPatient.xlsx";

//    public static String excelFilePath = pathFileExcel+new Date().getTime()+"_listPatient.xlsx";
    public static void exportExcel(List<Patient> patients) {
        List<Patient>listNew = new ArrayList();
        if(patients.size()>10){
            //in 10 bệnh nhân
            for(int i = 0; i<10;i++){
                listNew.add(patients.get(i));
            }
        }else {
            listNew = patients;
        }
        try {
            writeExcel(listNew,excelFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeExcel(List<Patient> patients, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);

        // Create sheet
        Sheet sheet = workbook.createSheet("Result"); // Create sheet with sheet name

        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (Patient patient : patients) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeResult(patient, row);
            rowIndex++;
        }


        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Patient ID");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Patient name");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Patient Age");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Patient gender");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Detection Time");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Patient status");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Injection times");
    }

    // Write data
    private static void writeResult(Patient patient, Row row) {

        Cell cell = row.createCell(0);
        cell.setCellValue(patient.getId());

        cell = row.createCell(1);
        cell.setCellValue(patient.getName());

        cell = row.createCell(2);
        cell.setCellValue(patient.getAge());

        cell = row.createCell(3);
        cell.setCellValue(patient.getGender());

        cell = row.createCell(4);
        cell.setCellValue(DateUtilities.DateToString2(patient.getDetectDate()));

        cell = row.createCell(5);
        cell.setCellValue(patient.getStatus());

        cell = row.createCell(6);
        cell.setCellValue(patient.getInjectionTimes());


    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }


    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

    public static void exportWord(Patient patient) {
            System.out.println("Doing Export to word docx....");
            logger.info("Doing Export to word docx....");
        XWPFDocument doc = new XWPFDocument();

        Map<String, String> map = new HashMap<>();

            // Thêm giá Key-Value vào HashMap
        map.put("ID: ", String.valueOf(patient.getId()));
        map.put("Name: ", patient.getName());
        map.put("Gender: ",patient.getGender() );
        map.put("Age: ",String.valueOf(patient.getAge()));
        map.put("Detection time: ",DateUtilities.DateToString2(patient.getDetectDate()) );
        map.put("Heath status: ",patient.getStatus() );
        map.put("Number of injection: ",String.valueOf(patient.getInjectionTimes()));

        for(var entry : map.entrySet()){
            XWPFParagraph xwpfParagraph = doc.createParagraph();
            XWPFRun run = xwpfParagraph.createRun();
            run.setText(entry.getKey() + entry.getValue());
        }

        String fileName = patient.getName()+"_"+String.valueOf(patient.getId())+"_Info.docx";
        File f = new File(outFolder);
//        if(!f.exists()){
//            System.out.println("Created Folder"+ outFolder);
//            logger.info("Created Folder"+ outFolder);
//            f.mkdir();
//        }
        try(FileOutputStream out = new FileOutputStream(new File(outFolder+fileName)))
         {
            doc.write(out);
            doc.close();
             System.out.println("Wrote to file: "+ outFolder+fileName);
             logger.info("Wrote to file: "+ outFolder+fileName);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

//    public String objectToText(Patient patient){
//
//    }
}
