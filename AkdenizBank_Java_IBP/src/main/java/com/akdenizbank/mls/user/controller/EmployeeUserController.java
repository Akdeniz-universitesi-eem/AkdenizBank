package com.akdenizbank.mls.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akdenizbank.mls.generic.rest.GenericApiResponse;
import com.akdenizbank.mls.user.EmployeeUser;
import com.akdenizbank.mls.user.service.EmployeeUserService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeUserController {

    @Autowired
    private EmployeeUserService employeeUserService;

    @GetMapping
    public GenericApiResponse getAllEmployees(Pageable pageable) {
        Page<EmployeeUser> employeesPage = this.employeeUserService.getAll(pageable);
        return new GenericApiResponse(200, "Success", "66661549", employeesPage);
    }

    @GetMapping("/{id}")
    public GenericApiResponse getEmployeeById(@PathVariable String id) {
        EmployeeUser employeeInDB = this.employeeUserService.getById(id);
        if (employeeInDB == null) {
            throw new RuntimeException("No Such Customer");
        }
        return new GenericApiResponse(200, "Success", "666861423", employeeInDB);
    }

    @DeleteMapping("/{id}")
    public GenericApiResponse deleteEmployee(@PathVariable String id) {
        this.employeeUserService.delete(id);
        return new GenericApiResponse(200, "Success", "6667549");
    }
}
