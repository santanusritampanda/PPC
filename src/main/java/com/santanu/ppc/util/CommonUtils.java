package com.santanu.ppc.util;

import java.util.List;

import com.santanu.ppc.exception.InvalidFormatException;
import com.santanu.ppc.exception.DataException;
import com.santanu.ppc.model.AmountEvent;
import com.santanu.ppc.model.DateEvent;
import com.santanu.ppc.model.Employee;

public class CommonUtils {
	public static Employee convertToEmployeeDetails(String[] parts) {
		Employee employee = new Employee();
		employee.setEmployeeId(validateStringWithFieldName(parts[1], "Employee ID"));
		employee.setEmployeeFirstName(validateStringWithFieldName(parts[2], "First Name"));
		employee.setEmployeeLastName(validateStringWithFieldName(parts[3], "Last Name"));
		employee.setEmployeeDesignation(validateStringWithFieldName(parts[4], "Designation"));
		return employee;
	}

	public static AmountEvent convertToAmountEvent(String[] parts) {
		AmountEvent amountEvent = new AmountEvent();
		amountEvent.setAmount(checkDouble(parts[3]));
		amountEvent.setEventDate(DateParserUtil.parseEventDate(parts[4].trim()));
		amountEvent.setNotes(validateStringWithFieldName(parts[5], "Notes"));
		return amountEvent;
	}

	public static DateEvent convertToDateEvent(String[] parts) {
		DateEvent dateEvent = new DateEvent();
		dateEvent.setValueDate(DateParserUtil.parseValueDate(parts[parts.length - 3].trim()));
		dateEvent.setEventDate(DateParserUtil.parseEventDate(parts[parts.length - 2].trim()));
		dateEvent.setNotes(validateStringWithFieldName(parts[parts.length - 1], "Notes"));
		return dateEvent;
	}

	public static String validateStringWithFieldName(String value, String fieldName) {
		if (value == null || value.trim().isEmpty()) {
			throw new DataException(fieldName + " is missing or empty");
		}
		return value.trim();
	}

	public static Double checkDouble(String value) {
		try {
			return Double.parseDouble(value.trim());
		} catch (NumberFormatException e) {
			throw new InvalidFormatException("Amount" + " is invalid: " + value);
		}
	}

	public static String checkEventDetails(String value, String fieldName) {
		if (value == null || value.trim().isEmpty()) {
			throw new DataException(value + " is missing or empty");
		}
		if (fieldName == null) {
			if (value.equalsIgnoreCase("SALARY") || value.equalsIgnoreCase("BONUS")
					|| value.equalsIgnoreCase("REIMBURSEMENT") || value.equalsIgnoreCase("EXIT")) {
				return value.trim();
			} else {
				throw new InvalidFormatException("Invalid event category: " + value);
			}
		} else {
			if (value.equalsIgnoreCase("ONBOARD")) {
				return value.trim();
			} else {
				throw new InvalidFormatException("Invalid event category: " + value);
			}
		}
	}

	public static Employee findEmployeeById(List<Employee> employees, String empId) {
		return employees.stream().filter(employee -> employee.getEmployeeId().equals(empId)).findFirst()
				.orElseThrow(() -> new DataException("Employee not found: " + empId));
	}
}
