package com.santanu.ppc.events;

import com.santanu.ppc.model.DateEvent;
import com.santanu.ppc.model.Employee;

public class OnboardEvent implements Event {
    private final DateEvent onboardEvent;

    public OnboardEvent(DateEvent onboardEvent) {
        this.onboardEvent = onboardEvent;
    }

    @Override
    public void process(Employee employee) {
        employee.setOnboardEvent(onboardEvent);
    }
}
