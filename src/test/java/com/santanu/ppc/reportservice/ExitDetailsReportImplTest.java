package com.santanu.ppc.reportservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.santanu.ppc.model.DateEvent;
import com.santanu.ppc.model.Employee;
import com.santanu.ppc.reportservice.ExitDetailsReportImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExitDetailsReportImplTest {

    private ExitDetailsReportImpl reports;

    @BeforeEach
    void setUp() {
    	reports = new ExitDetailsReportImpl();
    }

    @Test
    void testGenerateReport_withValidEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmployeeFirstName("Santanu");
        employee.setEmployeeLastName("Sritam");
        employee.setExitEvent(new DateEvent(LocalDate.now(), LocalDate.now(), "Exit"));
        employees.add(employee);

        assertDoesNotThrow(() -> reports.generateReport(employees));
    }
}