package com.santanu.ppc.events;

import com.santanu.ppc.model.DateEvent;
import com.santanu.ppc.model.Employee;

public class ExitEvent implements Event {
    private final DateEvent dateEvent;

    public ExitEvent(DateEvent dateEvent) {
        this.dateEvent = dateEvent;
    }

    @Override
    public void process(Employee employee) {
        employee.setExitEvent(dateEvent);
    }
}
