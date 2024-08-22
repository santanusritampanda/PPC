package com.santanu.ppc.reportsmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyAmountReleasedReportModel {
    private String monthYear;
    private double totalAmount;
    private int totalEmployees;
}
