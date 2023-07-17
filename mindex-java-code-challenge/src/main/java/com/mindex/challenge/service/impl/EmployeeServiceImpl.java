package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Reading employee with id [{}]", id); // This log should say 'reading employee with id...'?

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /**
     * find the entire reporting structure for a given employee id
     * @param id
     * @return
     */
    @Override
    public ReportingStructure getReportingStructure(String id) {
        Employee employee = read(id);
        Employee report;

        List<Employee> reports = new ArrayList<>();
        if (employee.getDirectReports() != null){
            reports.addAll(employee.getDirectReports());
        }

        //forEach directReport, add their direct reports to the list
        int i = 0;
        while (i < reports.size()){
            report = read(reports.get(i).getEmployeeId());
            if (report.getDirectReports() != null){
                reports.addAll(report.getDirectReports());
            }
            ++i;
        }

        //return size.
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(reports.size());
        return reportingStructure;
    }

    @Override
    public Compensation getCompensation(String id) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        return compensationRepository.findByEmployee(employee);
    }

    @Override
    public Compensation createCompensation(Compensation compensation) {

        LOG.debug("Creating compensation [{}]", compensation);

        compensationRepository.insert(compensation);

        return compensation;
    }


}
