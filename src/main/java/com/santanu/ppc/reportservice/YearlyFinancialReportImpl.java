package com.santanu.ppc.reportservice;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.Employee;
import com.santanu.ppc.reportsmodel.YearlyFinancialReportModel;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Order(7)
public class YearlyFinancialReportImpl implements Reports {
    @Override
    public void generateReport(List<Employee> employees) {
        Map<Year, List<YearlyFinancialReportModel>> yearlyReportMap = new HashMap<>();

        for (Employee employee : employees) {
            String empId = employee.getEmployeeId();

            if (employee.getSalaryEvents() != null) {
                for (AmountEvent event : employee.getSalaryEvents()) {
                    addEventToYearlyReport(yearlyReportMap, "SALARY", empId, event);
                }
            }

            if (employee.getBonusEvents() != null) {
                for (AmountEvent event : employee.getBonusEvents()) {
                    addEventToYearlyReport(yearlyReportMap, "BONUS", empId, event);
                }
            }

            if (employee.getReimbursementEvents() != null) {
                for (AmountEvent event : employee.getReimbursementEvents()) {
                    addEventToYearlyReport(yearlyReportMap, "REIMBURSEMENT", empId, event);
                }
            }
        }

        System.out.println("**** Yearly Financial Report ****");
        yearlyReportMap.forEach((year, reports) -> {
            System.out.println("Year: " + year);
            reports.forEach(report -> System.out.println("Event: " + report.getEvent() + ", Employee ID: " + report.getEmployeeId() + ", Event Date: " + report.getEventDate() + ", Event Value: " + report.getEventValue()));
        });
    }

    private void addEventToYearlyReport(Map<Year, List<YearlyFinancialReportModel>> yearlyReportMap, String eventType,
                                               String empId, AmountEvent event) {
        Year year = Year.from(event.getEventDate());
        YearlyFinancialReportModel report = new YearlyFinancialReportModel(eventType, empId, event.getEventDate(), event.getAmount());

        yearlyReportMap
                .computeIfAbsent(year, k -> new ArrayList<>())
                .add(report);
    }
}
