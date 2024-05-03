package com.example.education.controller;

import com.example.education.service.UserService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
public class TasksController {

    @Autowired
    private UserService userService;

    @PostMapping("/1")
    public ResponseEntity<String> checkTask1(@RequestParam("file") MultipartFile excelFile) throws IOException {

        var user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        File file = ResourceUtils.getFile("classpath:Task1.xlsx");
        InputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbookOriginal = (XSSFWorkbook) WorkbookFactory.create(inputStream);
        XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(excelFile.getInputStream());

        for (int i = 0; i < 957; i++) {
            String cell1Value;
            String cell2Value;

            String cell1ValueCompare;
            String cell2ValueCompare;

            XSSFCell cell1 = workbookOriginal.getSheetAt(0).getRow(i).getCell(0);
            cell1Value = extractValue(cell1);
            XSSFCell cell2 = workbookOriginal.getSheetAt(0).getRow(i).getCell(1);
            cell2Value = extractValue(cell2);

            cell1ValueCompare = extractValue(workbook.getSheetAt(0).getRow(i).getCell(0));
            cell2ValueCompare = extractValue(workbook.getSheetAt(0).getRow(i).getCell(1));

            if (!cell1Value.equals(cell1ValueCompare) || !cell2Value.equals(cell2ValueCompare)) {
                userService.addMistakeToTask1(user.getUsername());
                return ResponseEntity.status(420).body("Не совпадает");
            }
        }

        return ResponseEntity.ok("Правильно");
    }

    @PostMapping("/2")
    public ResponseEntity<String> checkTask2(@RequestParam("file") MultipartFile excelFile) throws IOException {

        var user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        File file = ResourceUtils.getFile("classpath:Task2.xlsx");
        InputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbookOriginal = (XSSFWorkbook) WorkbookFactory.create(inputStream);
        XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(excelFile.getInputStream());

        for (int i = 0; i < 6; i++) {
            String cell1Value;
            String cell2Value;

            String cell1ValueCompare;
            String cell2ValueCompare;

            XSSFCell cell1 = workbookOriginal.getSheetAt(0).getRow(i).getCell(0);
            cell1Value = extractValue(cell1);
            XSSFCell cell2 = workbookOriginal.getSheetAt(0).getRow(i).getCell(1);
            cell2Value = extractValue(cell2);

            cell1ValueCompare = extractValue(workbook.getSheetAt(0).getRow(i).getCell(0));
            cell2ValueCompare = extractValue(workbook.getSheetAt(0).getRow(i).getCell(1));

            if (!cell1Value.equals(cell1ValueCompare) || !cell2Value.equals(cell2ValueCompare)) {
                userService.addMistakeToTask2(user.getUsername());
                return ResponseEntity.status(420).body("Не совпадает");
            }
        }

        return ResponseEntity.ok("Правильно");
    }

    @PostMapping("/3")
    public ResponseEntity<String> checkTask3(@RequestParam("file") MultipartFile excelFile) throws IOException {

        var user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        File file = ResourceUtils.getFile("classpath:Task3.xlsx");
        InputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbookOriginal = (XSSFWorkbook) WorkbookFactory.create(inputStream);
        XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(excelFile.getInputStream());

        for (int i = 0; i < 2; i++) {
            String cell1Value;

            String cell1ValueCompare;

            XSSFCell cell1 = workbookOriginal.getSheetAt(0).getRow(i).getCell(0);
            cell1Value = extractValue(cell1);

            cell1ValueCompare = extractValue(workbook.getSheetAt(0).getRow(i).getCell(0));

            if (!cell1Value.equals(cell1ValueCompare)) {
                userService.addMistakeToTask3(user.getUsername());
                return ResponseEntity.status(420).body("Не совпадает");
            }
        }

        return ResponseEntity.ok("Правильно");
    }

    @PostMapping("/4")
    public ResponseEntity<String> checkTask4(@RequestBody Map<String, String> answers) {

        if (
                !answers.containsKey("1") ||
                !answers.containsKey("2") ||
                !answers.containsKey("3") ||
                !answers.containsKey("4") ||
                !answers.containsKey("5") ||
                !answers.containsKey("6") ||
                !answers.containsKey("7") ||
                !answers.containsKey("8")) {
            return ResponseEntity.badRequest().body("Json-файл должен содержать ключи 1-8");
        }

        if (!answers.get("1").toUpperCase().equals("B")) {
            return ResponseEntity.status(420).body("Неверно");
        }

        if (!answers.get("2").toUpperCase().equals("C")) {
            return ResponseEntity.status(420).body("Неверно");
        }

        if (!answers.get("3").toUpperCase().equals("B")) {
            return ResponseEntity.status(420).body("Неверно");
        }

        if (!answers.get("4").toUpperCase().equals("C")) {
            return ResponseEntity.status(420).body("Неверно");
        }

        if (!answers.get("5").toUpperCase().equals("A")) {
            return ResponseEntity.status(420).body("Неверно");
        }

        if (!answers.get("6").toUpperCase().equals("B")) {
            return ResponseEntity.status(420).body("Неверно");
        }

        if (!answers.get("7").toUpperCase().equals("D")) {
            return ResponseEntity.status(420).body("Неверно");
        }

        if (!answers.get("8").toUpperCase().equals("C")) {
            return ResponseEntity.status(420).body("Неверно");
        }

        return ResponseEntity.ok("Правильно");
    }

    private String extractValue(XSSFCell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType().equals(CellType.NUMERIC)) {
            return String.valueOf(cell.getNumericCellValue());
        }
        else {
            return cell.getStringCellValue();
        }
    }
}
