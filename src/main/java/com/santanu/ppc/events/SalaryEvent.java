package com.santanu.ppc.events;

import java.util.ArrayList;

import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.Employee;

public class SalaryEvent implements Event {
    private final AmountEvent amountEvent;

    public SalaryEvent(AmountEvent amountEvent) {
        this.amountEvent = amountEvent;
    }

    @Override
    public void process(Employee employee) {
        if (employee.getSalaryEvents() == null) {
            employee.setSalaryEvents(new ArrayList<>());
        }
        employee.getSalaryEvents().add(amountEvent);
    }
}
