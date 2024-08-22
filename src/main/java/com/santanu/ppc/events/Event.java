package com.santanu.ppc.events;

import com.santanu.ppc.model.Employee;

public interface Event {
    void process(Employee employee);
}
