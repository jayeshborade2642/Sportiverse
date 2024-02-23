package com.sportyverse.mapper;

import com.sportyverse.dto.AdminDto;
import com.sportyverse.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDto mapToAdminDto(Admin admin){
        return new AdminDto(
                admin.getAdminId(),
                admin.getAdmin_firstName(),
                admin.getAdmin_lastName(),
                admin.getAdmin_email(),
                admin.getAdmin_password()
        );
    }

    public Admin mapToAdmin(AdminDto adminDto){
        return new Admin(
                adminDto.getAdminId(),
                adminDto.getAdmin_firstName(),
                adminDto.getAdmin_lastName(),
                adminDto.getAdmin_email(),
                adminDto.getAdmin_password()
        );
    }
}
