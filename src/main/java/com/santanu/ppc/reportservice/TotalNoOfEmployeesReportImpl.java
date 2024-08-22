package com.santanu.ppc.reportservice;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.santanu.ppc.model.Employee;

import java.util.List;

@Service
@Order(1)
public class TotalNoOfEmployeesReportImpl implements Reports {
    @Override
    public void generateReport(List<Employee> employees) {
        System.out.println("**** Total Number of Employees Report ****");
        System.out.println("Total number of employees in this organization: " + employees.size());
    }
}
