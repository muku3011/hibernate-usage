package com.learn.spring.service;

import com.learn.spring.exception.InvalidRequestException;
import com.learn.spring.exception.RecordNotFoundException;
import com.learn.spring.domain.EmployeeEntity;
import com.learn.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> employeeList = repository.findAll();

        if (employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public EmployeeEntity updateEmployee(EmployeeEntity entity) throws InvalidRequestException, RecordNotFoundException {
        if (entity.getId() != null) {
            Optional<EmployeeEntity> employee = repository.findById(entity.getId());
            if (!employee.isPresent()) {
                throw new RecordNotFoundException("No record found with ID : " + entity.getId());
            }
            entity = repository.save(entity);
            return entity;
        } else {
            throw new InvalidRequestException("Not a valid update request, do POST request");
        }
    }

    public EmployeeEntity createEmployee(EmployeeEntity entity) throws InvalidRequestException {
        if (entity.getId() != null) {
            throw new InvalidRequestException("Not a valid create request, do a PUT request");
        } else {
            return repository.save(entity);
        }
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}