package com.santanu.ppc.reportsmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OnboardDetailsModel {
    private String employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeDesignation;
}
