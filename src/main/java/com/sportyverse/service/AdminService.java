package com.sportyverse.service;

import com.sportyverse.dto.AdminDto;

public interface AdminService {

    AdminDto verifyByEmailAndPassword(String email, String password);
}
