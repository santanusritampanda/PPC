package com.santanu.ppc.reportservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.santanu.ppc.model.DateEvent;
import com.santanu.ppc.model.Employee;
import com.santanu.ppc.reportservice.OnboardDetailsReportImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OnboardDetailsReportImplTest {

    private OnboardDetailsReportImpl reports;

    @BeforeEach
    void setUp() {
    	reports = new OnboardDetailsReportImpl();
    }

    @Test
    void testGenerateReport_withValidEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmployeeId("E001");
        employee.setEmployeeFirstName("Santanu");
        employee.setEmployeeLastName("Sritam");
        employee.setEmployeeDesignation("Developer");
        employee.setOnboardEvent(new DateEvent(LocalDate.now(), LocalDate.now(), "Onboard"));
        employees.add(employee);

        assertDoesNotThrow(() -> reports.generateReport(employees));
    }
    @Test
    void testGenerateReport_withValidEmployeesMultiple() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmployeeId("E001");
        employee1.setEmployeeFirstName("Santanu");
        employee1.setEmployeeLastName("Sritam");
        employee1.setEmployeeDesignation("Developer");
        employee1.setOnboardEvent(new DateEvent(LocalDate.now(), LocalDate.now(), "Onboard"));
        employees.add(employee1);
        
        Employee employee2 = new Employee();
        employee2.setEmployeeId("E001");
        employee2.setEmployeeFirstName("Milan");
        employee2.setEmployeeLastName("Behera");
        employee2.setEmployeeDesignation("Designer");
        employee2.setOnboardEvent(new DateEvent(LocalDate.now(), LocalDate.now(), "Onboard"));
        employees.add(employee2);
        
        Employee employee3 = new Employee();
        employee3.setEmployeeId("E001");
        employee3.setEmployeeFirstName("Virat");
        employee3.setEmployeeLastName("Kohli");
        employee3.setEmployeeDesignation("Accountant");
        employee3.setOnboardEvent(new DateEvent(LocalDate.now(), LocalDate.now(), "Onboard"));
        employees.add(employee3);

        assertDoesNotThrow(() -> reports.generateReport(employees));
    }
}