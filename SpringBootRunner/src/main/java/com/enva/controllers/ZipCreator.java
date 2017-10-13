package com.enva.controllers;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("FirstSheet");
        HSSFRow rowhead = sheet.createRow((short) 0);
        rowhead.createCell(0).setCellValue("No.");
        rowhead.createCell(1).setCellValue("Name");
        rowhead.createCell(2).setCellValue("Address");
        rowhead.createCell(3).setCellValue("Email");

        HSSFRow row = sheet.createRow((short) 1);
        row.createCell(0).setCellValue("1");
        row.createCell(1).setCellValue("Sankumarsingh");
        row.createCell(2).setCellValue("India");
        row.createCell(3).setCellValue("sankumarsingh@gmail.com");

        workbook.close();

        byte[] fileAsBites = getWbToZipAsBites(getWbAsBites(workbook), "text.xlsx");

        try(FileOutputStream file = new FileOutputStream("D:\\test.zip")) {
            file.write(fileAsBites);
        }
    }

    private byte[] getWbAsBites(HSSFWorkbook workbook) {
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
