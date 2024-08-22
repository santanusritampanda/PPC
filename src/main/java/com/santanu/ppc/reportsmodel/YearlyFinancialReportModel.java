package com.santanu.ppc.reportsmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class YearlyFinancialReportModel {
    private String event;
    private String employeeId;
    private LocalDate eventDate;
    private double eventValue;
}
