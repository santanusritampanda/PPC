package com.santanu.ppc.reportservice;

import java.util.List;

import com.santanu.ppc.model.Employee;

public interface Reports {
    void generateReport(List<Employee> employees);
}