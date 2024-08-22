package com.santanu.ppc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santanu.ppc.events.Event;
import com.santanu.ppc.events.EventFactory;
import com.santanu.ppc.exception.DataException;
import com.santanu.ppc.exception.InvalidFormatException;
import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.DateEvent;
import com.santanu.ppc.model.Employee;
import com.santanu.ppc.reportservice.Reports;
import com.santanu.ppc.util.CommonUtils;

@Service
public class PPCServiceImpl implements PPCService {

    @Autowired
    private List<Reports> reports;

    @Autowired
    private EventFactory eventFactory;

    public void processFiles(InputStream inputStream) throws IOException {
        String line = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<Employee> employees = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");              
                if (data.length == 9) {
                	CommonUtils.checkEventDetails(data[5], "ONBOARD");
                    Employee employee = CommonUtils.convertToEmployeeDetails(data);
                    employees.add(employee);
                    DateEvent onboardEvent = CommonUtils.convertToDateEvent(data);
                    Event event = eventFactory.createEvent("ONBOARD", onboardEvent);
                    event.process(employee);
                } else if (data.length == 6) {
                    String employeeId = CommonUtils.validateStringWithFieldName(data[1], "Employee ID");
                    String eventCategory = CommonUtils.checkEventDetails(data[2], null);
                    Employee employee = CommonUtils.findEmployeeById(employees, employeeId);
                    if (employee != null) {
                        if (eventCategory.equals("EXIT")) {
                            DateEvent dateEvent = CommonUtils.convertToDateEvent(data);
                            Event event = eventFactory.createEvent(eventCategory, dateEvent);
                            event.process(employee);
                        } else {
                            AmountEvent amountEvent = CommonUtils.convertToAmountEvent(data);
                            Event event = eventFactory.createEvent(eventCategory, amountEvent);
                            event.process(employee);
                        }
                    }
                } else {
                    throw new InvalidFormatException("The number of columns in the file are invalid");
                }
            }
            generateReports(employees);
        } catch (IOException | InvalidFormatException | DataException | DateTimeParseException | NumberFormatException e) {
            System.err.println("Error processing line: " + line);
            System.err.println("Reason: " + e.getMessage());
            throw e;
        }
    }

    

    private void generateReports(List<Employee> employees) {
        reports.forEach(reportStrategy -> reportStrategy.generateReport(employees));
    }
}
