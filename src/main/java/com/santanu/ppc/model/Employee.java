package com.santanu.ppc.model;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    private String employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeDesignation;
    private DateEvent onboardEvent;
    private List<AmountEvent> salaryEvents;
    private List<AmountEvent> bonusEvents;
    private List<AmountEvent> reimbursementEvents;
    private DateEvent exitEvent;
}
