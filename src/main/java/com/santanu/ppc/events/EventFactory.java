package com.santanu.ppc.events;

import org.springframework.stereotype.Service;

import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.DateEvent;

@Service
public class EventFactory {
    public Event createEvent(String eventType, Object event) {
        return switch (eventType) {
            case "ONBOARD" -> new OnboardEvent((DateEvent) event);
            case "EXIT" -> new ExitEvent((DateEvent) event);
            case "SALARY" -> new SalaryEvent((AmountEvent) event);
            case "REIMBURSEMENT" -> new ReimbursementEvent((AmountEvent) event);
            case "BONUS" -> new BonusEvent((AmountEvent) event);
            default -> throw new IllegalArgumentException("Event Category Not Found: " + eventType);
        };
    }
}
