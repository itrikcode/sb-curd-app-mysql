package com.itsp.curdappmysql.repository;

import com.itsp.curdappmysql.bean.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeLoginRepo extends JpaRepository<Login,Long> {
    Login findByUsername(String username);
}
