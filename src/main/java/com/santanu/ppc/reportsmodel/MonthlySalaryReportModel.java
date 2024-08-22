package com.santanu.ppc.reportsmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlySalaryReportModel {
    private String monthYear;
    private double totalSalary;
    private int totalEmployees;
}
