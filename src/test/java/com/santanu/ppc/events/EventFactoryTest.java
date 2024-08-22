package com.santanu.ppc.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.santanu.ppc.events.Event;
import com.santanu.ppc.events.EventFactory;
import com.santanu.ppc.events.OnboardEvent;
import com.santanu.ppc.model.DateEvent;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventFactoryTest {

    @InjectMocks
    private EventFactory eventFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEvent_Onboard() {
        DateEvent event = new DateEvent();
        Event result = eventFactory.createEvent("ONBOARD", event);
        assertTrue(result instanceof OnboardEvent);
    }

    @Test
    public void testCreateEvent_InvalidType() {
        assertThrows(IllegalArgumentException.class, () -> {
            eventFactory.createEvent("INVALID", new Object());
        });
    }
}