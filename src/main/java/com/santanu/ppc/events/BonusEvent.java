package com.santanu.ppc.events;

import java.util.ArrayList;

import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.Employee;

public class BonusEvent implements Event {
    private final AmountEvent amountEvent;

    public BonusEvent(AmountEvent amountEvent) {
        this.amountEvent = amountEvent;
    }

    @Override
    public void process(Employee employee) {
        if (employee.getBonusEvents() == null) {
            employee.setBonusEvents(new ArrayList<>());
        }
        employee.getBonusEvents().add(amountEvent);
    }
}