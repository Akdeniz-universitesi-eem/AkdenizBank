package com.akdenizbank.mls.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.akdenizbank.mls.user.EmployeeUser;
import com.akdenizbank.mls.user.repository.EmployeeUserRepository;

@Service
public class EmployeeUserService {
    
    private EmployeeUserRepository employeeUserRepository;

    public EmployeeUserService(EmployeeUserRepository employeeUserRepository) {
        this.employeeUserRepository = employeeUserRepository;
    }

    public EmployeeUser create(EmployeeUser employeeUser) {
        return this.employeeUserRepository.save(employeeUser);
    }

    public EmployeeUser getById(String id) {
        return this.employeeUserRepository.findById(id).orElse(null);
    }

    public Page<EmployeeUser> getAll(Pageable pageable) {
        return this.employeeUserRepository.findAll(pageable);
    }

    public void delete(String id) {
        EmployeeUser employeeUserInDB = this.getById(id);
        if (employeeUserInDB == null) {
            throw new RuntimeException("No Such Customer");
        }
        this.employeeUserRepository.delete(employeeUserInDB);
    }

}
