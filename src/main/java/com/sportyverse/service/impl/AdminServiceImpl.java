package com.sportyverse.service.impl;

import com.sportyverse.dto.AdminDto;
import com.sportyverse.entity.Admin;
import com.sportyverse.mapper.AdminMapper;
import com.sportyverse.repository.AdminRepository;
import com.sportyverse.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminDto verifyByEmailAndPassword(String email, String password) {
        Admin admin = adminRepository.verifyByEmailAndPassword(email, password);

        return adminMapper.mapToAdminDto(admin);
    }
}
