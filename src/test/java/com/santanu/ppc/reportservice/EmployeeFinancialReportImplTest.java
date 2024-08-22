package com.santanu.ppc.reportservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.Employee;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmployeeFinancialReportImplTest {

    private EmployeeFinancialReportImpl reports;

    @BeforeEach
    void setUp() {
    	reports = new EmployeeFinancialReportImpl();
    }

    @Test
    void testGenerateReport_withValidEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmployeeId("E001");
        employee.setEmployeeFirstName("Santanu");
        employee.setEmployeeLastName("Sritam");
        employee.setSalaryEvents(List.of(new AmountEvent(1000, LocalDate.now(), "Salary")));
        employee.setBonusEvents(List.of(new AmountEvent(500, LocalDate.now(), "Bonus")));
        employee.setReimbursementEvents(List.of(new AmountEvent(200, LocalDate.now(), "Reimbursement")));
        employees.add(employee);

        assertDoesNotThrow(() -> reports.generateReport(employees));
    }
}