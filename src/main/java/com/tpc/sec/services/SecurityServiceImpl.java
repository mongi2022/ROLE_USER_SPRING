package com.tpc.sec.services;

import com.tpc.sec.entities.AppRole;
import com.tpc.sec.entities.AppUser;
import com.tpc.sec.exceptions.NotFoundRoleException;
import com.tpc.sec.exceptions.NotFoundUserException;
import com.tpc.sec.repositories.AppRoleRepository;
import com.tpc.sec.repositories.AppUserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;

    public SecurityServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser addUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }


    @Override
    public void addRoleToUser(Long userId, Long roleId)  {
        AppUser user = appUserRepository.findById(userId).orElseThrow(() -> new NotFoundUserException("User not found"));
        AppRole role = appRoleRepository.findById(roleId).orElseThrow(() -> new NotFoundRoleException("Role not found"));

        user.getAppRoles().add(role);
        appUserRepository.save(user);
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
        return appRoleRepository.save(appRole);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }


}
