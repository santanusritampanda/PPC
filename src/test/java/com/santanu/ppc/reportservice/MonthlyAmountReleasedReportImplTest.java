package com.santanu.ppc.reportservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.Employee;
import com.santanu.ppc.reportservice.MonthlyAmountReleasedReportImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyAmountReleasedReportImplTest {

    private MonthlyAmountReleasedReportImpl reports;

    @BeforeEach
    void setUp() {
    	reports = new MonthlyAmountReleasedReportImpl();
    }

    @Test
    void testGenerateReport_withValidEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        AmountEvent salaryEvent = new AmountEvent(1000, LocalDate.now(), "Salary");
        employee.setSalaryEvents(List.of(salaryEvent));
        employees.add(employee);

        assertDoesNotThrow(() -> reports.generateReport(employees));
    }
}