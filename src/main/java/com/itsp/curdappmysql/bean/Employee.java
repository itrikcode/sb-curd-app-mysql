package com.itsp.curdappmysql.bean;

import jakarta.persistence.*;

@Entity
@Table(name="emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid")
    private  Long empId;
    private String empName;
    private String empAddress;
    private String empMobile;

    private String empAddhar;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getEmpAddhar() {
        return empAddhar;
    }

    public void setEmpAddhar(String empAddhar) {
        this.empAddhar = empAddhar;
    }
}
