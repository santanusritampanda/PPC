package com.santanu.ppc.reportservice;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.santanu.ppc.model.Employee;
import com.santanu.ppc.reportsmodel.OnboardDetailsModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Order(2)
public class OnboardDetailsReportImpl implements Reports {
    @Override
    public void generateReport(List<Employee> employees) {
        Map<String, List<OnboardDetailsModel>> monthWiseEmployees = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (Employee employee : employees) {
            if (employee.getOnboardEvent() != null && employee.getOnboardEvent().getValueDate() != null) {
                LocalDate joinDate = employee.getOnboardEvent().getValueDate();
                String monthYearKey = joinDate.format(formatter);

                OnboardDetailsModel empDetails = new OnboardDetailsModel(
                        employee.getEmployeeId(),
                        employee.getEmployeeFirstName(),
                        employee.getEmployeeLastName(),
                        employee.getEmployeeDesignation()
                );

                monthWiseEmployees
                        .computeIfAbsent(monthYearKey, k -> new ArrayList<>())
                        .add(empDetails);
            }
        }

        System.out.println("**** Monthly Joining Details ****");
        monthWiseEmployees.forEach((monthYear, employeesList) -> {
            System.out.println("Month: " + monthYear);
            employeesList.forEach(employee -> System.out.println("Employee ID: " + employee.getEmployeeId() + ", Name: " + employee.getEmployeeFirstName() + " " + employee.getEmployeeLastName() + ", Designation: " + employee.getEmployeeDesignation()));
        });
    }
}
