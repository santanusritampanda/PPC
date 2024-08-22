package com.santanu.ppc.reportservice;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.Employee;
import com.santanu.ppc.reportsmodel.MonthlySalaryReportModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Order(4)
public class MonthlySalaryReportImpl implements Reports {
    @Override
    public void generateReport(List<Employee> employees) {
        Map<String, MonthlySalaryReportModel> monthlyReportMap = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (Employee employee : employees) {
            if (employee.getSalaryEvents() != null) {
                for (AmountEvent salaryEvent : employee.getSalaryEvents()) {
                    LocalDate salaryDate = salaryEvent.getEventDate();
                    String monthYearKey = salaryDate.format(formatter);

                    MonthlySalaryReportModel report = monthlyReportMap.computeIfAbsent(monthYearKey, k -> new MonthlySalaryReportModel(k, 0, 0));
                    report.setTotalSalary(report.getTotalSalary() + salaryEvent.getAmount());
                    report.setTotalEmployees(report.getTotalEmployees() + 1);
                }
            }
        }

        // Print or store the report
        System.out.println("**** Monthly Salary Report ****");
        monthlyReportMap.values().forEach(report -> System.out.println("Month: " + report.getMonthYear() + ", Total Salary: " + report.getTotalSalary() + ", Total Employees: " + report.getTotalEmployees()));
    }
}
