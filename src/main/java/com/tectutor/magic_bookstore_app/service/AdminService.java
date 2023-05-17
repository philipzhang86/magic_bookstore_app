package com.tectutor.magic_bookstore_app.service;

import com.tectutor.magic_bookstore_app.dto.AdminDTO;
import com.tectutor.magic_bookstore_app.model.Admin;
import com.tectutor.magic_bookstore_app.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    public AdminDTO insertAdmin(AdminDTO adminDTO) {
        Admin obj = new Admin();
        obj.setAdminId(adminDTO.getId());
        obj.setAdminName(adminDTO.getName());
        obj.setAdminPassword(adminDTO.getPassword());
        adminRepo.save(obj);
        return adminDTO;
    }

    public Admin login(Admin admin) {
        Admin foundAdmin = adminRepo.findByAdminName(admin.getAdminName());
        if (foundAdmin != null && foundAdmin.getAdminPassword().equals(admin.getAdminPassword())) return foundAdmin;
        return null;
    }

    public boolean login2(Admin admin) {
        Admin result = adminRepo.findByAdminNameAndAdminPassword(admin.getAdminName(), admin.getAdminPassword());
        return result != null;
    }

}
