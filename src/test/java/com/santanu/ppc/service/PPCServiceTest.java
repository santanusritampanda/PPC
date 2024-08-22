package com.santanu.ppc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.santanu.ppc.events.BonusEvent;
import com.santanu.ppc.events.EventFactory;
import com.santanu.ppc.events.OnboardEvent;
import com.santanu.ppc.events.ReimbursementEvent;
import com.santanu.ppc.events.SalaryEvent;
import com.santanu.ppc.exception.InvalidFormatException;
import com.santanu.ppc.exception.DataException;
import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.DateEvent;
import com.santanu.ppc.reportservice.Reports;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PPCServiceTest {

    @Mock
    List<Reports> reports;

    @Mock
    private EventFactory eventFactory;

    @InjectMocks
    private PPCServiceImpl ppcService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessFile_withValidData() throws Exception {
        String fileContent = "1, E001, John, Doe, Developer, ONBOARD, 01-01-2020, 01-01-2020, Notes\n" +
                "2, E001, SALARY, 1000, 01-02-2020, Notes\n" +
                "2, E001, REIMBURSEMENT, 500, 01-01-2020, Notes\n" +
                "3, E001, BONUS, 500, 01-03-2020, Notes\n";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

        when(eventFactory.createEvent(eq("ONBOARD"), any(DateEvent.class))).thenReturn(mock(OnboardEvent.class));
        when(eventFactory.createEvent(eq("SALARY"), any(AmountEvent.class))).thenReturn(mock(SalaryEvent.class));
        when(eventFactory.createEvent(eq("BONUS"), any(AmountEvent.class))).thenReturn(mock(BonusEvent.class));
        when(eventFactory.createEvent(eq("REIMBURSEMENT"), any(AmountEvent.class))).thenReturn(mock(ReimbursementEvent.class));

        ppcService.processFiles(inputStream);

        verify(eventFactory, times(1)).createEvent(eq("ONBOARD"), any(DateEvent.class));
        verify(eventFactory, times(1)).createEvent(eq("SALARY"), any(AmountEvent.class));
        verify(eventFactory, times(1)).createEvent(eq("BONUS"), any(AmountEvent.class));
        verify(eventFactory, times(1)).createEvent(eq("REIMBURSEMENT"), any(AmountEvent.class));
        verify(reports, times(1)).forEach(any());
    }

    @Test
    void testProcessFile_withInvalidDataFormat() {
        String fileContent = "1, Santanu, Sritam, Developer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

        assertThrows(InvalidFormatException.class, () -> ppcService.processFiles(inputStream));
    }

    @Test
    void testProcessFile_withMissingData() {
        String fileContent = "1, , Prakash, Jena, Developer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

        assertThrows(DataException.class, () -> ppcService.processFiles(inputStream));
    }

}