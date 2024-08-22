package com.santanu.ppc.events;

import java.util.ArrayList;

import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.Employee;

public class ReimbursementEvent implements Event {
    private final AmountEvent amountEvent;

    public ReimbursementEvent(AmountEvent amountEvent) {
        this.amountEvent = amountEvent;
    }

    @Override
    public void process(Employee employee) {
        if (employee.getReimbursementEvents() == null) {
            employee.setReimbursementEvents(new ArrayList<>());
        }
        employee.getReimbursementEvents().add(amountEvent);
    }
}
