package com.itsp.curdappmysql.controller;

import com.itsp.curdappmysql.bean.EmployeeRequest;
import com.itsp.curdappmysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class BatchController {

    @Autowired
    EmployeeService employeeService;
    @PostMapping("/batch")
    public String createBatchOpt(@RequestBody List<EmployeeRequest> employeeRequestList){
        return employeeService.batchCreateEmployee(employeeRequestList);
   }
}
