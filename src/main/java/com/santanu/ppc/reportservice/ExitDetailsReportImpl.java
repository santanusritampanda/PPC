package com.santanu.ppc.reportservice;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.santanu.ppc.model.Employee;
import com.santanu.ppc.reportsmodel.ExitDetailsModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Order(3)
public class ExitDetailsReportImpl implements Reports {
    @Override
    public void generateReport(List<Employee> employees) {
        Map<String, List<ExitDetailsModel>> monthWiseEmployees = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (Employee employee : employees) {
            if (employee.getExitEvent() != null && employee.getExitEvent().getValueDate() != null) {
                LocalDate joinDate = employee.getExitEvent().getValueDate();
                String monthYearKey = joinDate.format(formatter);

                ExitDetailsModel empDetails = new ExitDetailsModel(
                        employee.getEmployeeFirstName(),
                        employee.getEmployeeLastName()
                );

                monthWiseEmployees
                        .computeIfAbsent(monthYearKey, k -> new ArrayList<>())
                        .add(empDetails);
            }
        }

        System.out.println("**** Monthly Exit Details ****");
        monthWiseEmployees.forEach((monthYear, employeesList) -> {
            System.out.println("Month: " + monthYear);
            employeesList.forEach(employee -> System.out.println("Employee Name: " + employee.getEmployeeFirstName() + " " + employee.getEmployeeLastName()));
        });
    }
}
