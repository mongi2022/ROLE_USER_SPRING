package com.tpc.sec.services;

import com.tpc.sec.entities.AppRole;
import com.tpc.sec.entities.AppUser;
import com.tpc.sec.exceptions.NotFoundRoleException;
import com.tpc.sec.exceptions.NotFoundUserException;
import com.tpc.sec.repositories.AppRoleRepository;
import com.tpc.sec.repositories.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser addUser(AppUser appUser) {

//        AppUser user=  appUserRepository.findByUsername(appUser.getUsername());
//        System.out.println(user);
        List<String> users = appUserRepository.findAll().stream().map(u -> u.getUsername()).toList();
        System.out.println(users);
        if (users.contains(appUser.getUsername())){
           return appUser;
        }else {
            String hashedPassword = passwordEncoder.encode(appUser.getPassword());
            appUser.setPassword(hashedPassword);
            return appUserRepository.save(appUser);
        }


    }


    @Override
    public ResponseEntity<String> addRoleToUser(Long userId, Long roleId) {
        AppUser user = appUserRepository.findById(userId).orElseThrow(() -> new NotFoundUserException("User not found"));
        AppRole role = appRoleRepository.findById(roleId).orElseThrow(() -> new NotFoundRoleException("Role not found"));
        List<String> rolesUser = user.getAppRoles().stream().map(r->r.getRoleName()).toList();
        if (rolesUser.contains(role.getRoleName())) {
          return   ResponseEntity.ok("Role existe déja");
        } else {
            user.getAppRoles().add(role);
            appUserRepository.save(user);
          return    ResponseEntity.ok("Role added to the user successfully");
        }
    }
    @Override
    public void addRoleToUser2(String username, String roleName) {
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findByRoleName(roleName);

        user.getAppRoles().add(role);
        appUserRepository.save(user);
    }


    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppRole addRole(AppRole appRole) {
        List<String> roles = appRoleRepository.findAll().stream().map(u -> u.getRoleName()).toList();
        System.out.println(roles);
        if (roles.contains(appRole.getRoleName())) {
            return appRole;
        } else {
            return appRoleRepository.save(appRole);
        }
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }


}
