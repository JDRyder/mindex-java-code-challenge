package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private Employee employee;
    private Double Salary;
    private Date EffectiveDate;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }

    public Date getEffectiveDate() {
        return EffectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        EffectiveDate = effectiveDate;
    }
}
