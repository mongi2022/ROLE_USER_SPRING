package com.tpc.sec.services;

import com.tpc.sec.entities.AppRole;
import com.tpc.sec.entities.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SecurityService {
    AppUser addUser(AppUser appUser);

    AppRole addRole(AppRole appRole);

    ResponseEntity<String> addRoleToUser(Long userId, Long roleId);

    void addRoleToUser2(String username, String roleName);

    AppUser loadUserByUsername(String username);

    List<AppUser> getAllUsers();
}
