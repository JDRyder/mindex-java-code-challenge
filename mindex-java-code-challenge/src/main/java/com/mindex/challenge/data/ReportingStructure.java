package com.mindex.challenge.data;

public class ReportingStructure {

    private Employee Employee;
    /**
     * total number of employees below this employee in the structure.
     */
    private int NumberOfReports;

    public Employee getEmployee() {
        return Employee;
    }

    public void setEmployee(Employee employee) {
        Employee = employee;
    }

    public int getNumberOfReports() {
        return NumberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        NumberOfReports = numberOfReports;
    }



}
