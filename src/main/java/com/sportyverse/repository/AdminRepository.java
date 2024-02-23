package com.sportyverse.repository;

import com.sportyverse.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.admin_email=:email AND a.admin_password=:password")
    public Admin verifyByEmailAndPassword(String email, String password);
}
