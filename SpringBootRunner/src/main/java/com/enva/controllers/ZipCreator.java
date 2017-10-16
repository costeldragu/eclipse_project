package com.enva.controllers;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCreator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipCreator.class);

    public ZipCreator() throws IOException {
        StringBuilder stars = new StringBuilder();
        int starNumber = 40;
        Stream.generate(() -> "*").limit(starNumber).forEach(stars::append);
        LOGGER.info(stars.toString());
        LOGGER.info("* Zip creator is starting");
        LOGGER.info(stars.toString());

        createWorbook();
    }

    private void createWorbook() throws IOException {
        Workbook workbook = new XSSFWorkbook();

        createSheet(workbook, "Sheet 1");
        createSheet(workbook, "Sheet 2");
        createSheet(workbook, "Sheet 3");
        createSheet(workbook, "Sheet 4");
        createSheet(workbook, "Sheet 5");
        createSheet(workbook, "Sheet 6");
        createSheet(workbook, "Sheet 7");
        createSheet(workbook, "Sheet 8");
        createSheet(workbook, "Sheet 9");

       workbook.setActiveSheet(2);
        workbook.setFirstVisibleTab(2);

        workbook.getSheet("Sheet 3").setActiveCell(new CellAddress(0, 0));
        byte[] fileAsBites = getWbToZipAsBites(getWbAsBites(workbook), "text.xlsx");

        try (FileOutputStream file = new FileOutputStream("D:\\test\\test" + System.currentTimeMillis() + ".zip")) {
            file.write(fileAsBites);
        }

        workbook.close();
    }

    private void createSheet(Workbook workbook, String name) {
        Sheet sheet = workbook.createSheet(name);
        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 9));
        Row rowhead = sheet.createRow(0);
        rowhead.createCell(0).setCellValue("No.");
        rowhead.createCell(1).setCellValue("Name");
        rowhead.createCell(2).setCellValue("Address");
        rowhead.createCell(3).setCellValue("Email");
        for (int x = 0; x < 1000; ++x) {
            Row row = sheet.createRow(x);
            row.createCell(0).setCellValue(x);
            row.createCell(1).setCellValue("Sankumarsingh " + x);
            row.createCell(2).setCellValue("India");
            row.createCell(3).setCellValue("sankumarsingh@gmail.com");
        }


    }

    private byte[] getWbAsBites(Workbook workbook) {
        ByteArrayOutputStream bus = new ByteArrayOutputStream();
        try {
            workbook.write(bus);
            bus.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bus.toByteArray();
    }

    private byte[] getWbToZipAsBites(byte[] file, String fileName) throws IOException {
        ByteArrayOutputStream bus = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(bus);
        ZipEntry entry = new ZipEntry(fileName);
        zip.putNextEntry(entry);
        zip.write(file, 0, file.length);
        zip.closeEntry();
        zip.close();
        return bus.toByteArray();
    }

}
