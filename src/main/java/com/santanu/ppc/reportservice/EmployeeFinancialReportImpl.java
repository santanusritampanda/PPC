package com.santanu.ppc.reportservice;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.Employee;
import com.santanu.ppc.reportsmodel.EmployeeFinancialReportModel;

import java.util.List;

@Service
@Order(5)
public class EmployeeFinancialReportImpl implements Reports {
    @Override
    public void generateReport(List<Employee> employees) {
        System.out.println("**** Employee Financial Report ****");
        for (Employee employee : employees) {
            double totalAmountPaid = 0;
            if (employee.getSalaryEvents() != null) {
                totalAmountPaid += employee.getSalaryEvents().stream().mapToDouble(AmountEvent::getAmount).sum();
            }
            if (employee.getBonusEvents() != null) {
                totalAmountPaid += employee.getBonusEvents().stream().mapToDouble(AmountEvent::getAmount).sum();
            }
            if (employee.getReimbursementEvents() != null) {
                totalAmountPaid += employee.getReimbursementEvents().stream().mapToDouble(AmountEvent::getAmount).sum();
            }
            EmployeeFinancialReportModel report = new EmployeeFinancialReportModel(employee.getEmployeeId(), employee.getEmployeeFirstName(), employee.getEmployeeLastName(), totalAmountPaid);
            System.out.println("Employee ID: " + report.getEmployeeId() + ", Name: " + report.getEmployeeFirstName() + " " + report.getEmployeeLastName() + ", Total Amount Paid: " + report.getTotalAmountPaid());
        }
    }
}
