package com.santanu.ppc.reportservice;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.Employee;
import com.santanu.ppc.reportsmodel.MonthlyAmountReleasedReportModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Order(6)
public class MonthlyAmountReleasedReportImpl implements Reports {
    @Override
    public void generateReport(List<Employee> employees) {
        Map<String, MonthlyAmountReleasedReportModel> monthlyReportMap = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (Employee employee : employees) {
            if (employee.getSalaryEvents() != null) {
                for (AmountEvent salaryEvent : employee.getSalaryEvents()) {
                    getDetails(monthlyReportMap, formatter, salaryEvent);
                }
            }
            if (employee.getBonusEvents() != null) {
                for (AmountEvent bonusEvent : employee.getBonusEvents()) {
                    getDetails(monthlyReportMap, formatter, bonusEvent);
                }
            }
            if (employee.getReimbursementEvents() != null) {
                for (AmountEvent reimbursementEvent : employee.getReimbursementEvents()) {
                    getDetails(monthlyReportMap, formatter, reimbursementEvent);
                }
            }
        }

        System.out.println("**** Monthly Amount Released Reports ****");
        monthlyReportMap.values().forEach(report -> System.out.println("Month: " + report.getMonthYear() + ", Total Amount: " + report.getTotalAmount() + ", Total Employees: " + report.getTotalEmployees()));
    }

    private void getDetails(Map<String, MonthlyAmountReleasedReportModel> monthlyReportMap, DateTimeFormatter formatter, AmountEvent amountEvent) {
        LocalDate eventDate = amountEvent.getEventDate();
        String monthYearKey = eventDate.format(formatter);

        MonthlyAmountReleasedReportModel report = monthlyReportMap.computeIfAbsent(monthYearKey, k -> new MonthlyAmountReleasedReportModel(k, 0, 0));
        report.setTotalAmount(report.getTotalAmount() + amountEvent.getAmount());
        report.setTotalEmployees(report.getTotalEmployees() + 1);
    }
}
