package com.tpc.sec.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpc.sec.entities.AppRole;
import com.tpc.sec.entities.AppUser;
import com.tpc.sec.services.SecurityServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SecurityController {
    private SecurityServiceImpl securityService;

    public SecurityController(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    @PostMapping("add-user")

    public AppUser addUserController(@RequestBody AppUser appUser) {
        return securityService.addUser(appUser);
    }

    @PostMapping("add-role")

    public AppRole addRoleController(@RequestBody AppRole appRole) {
        return securityService.addRole(appRole);
    }


    @PostMapping("/users/{userId}/roles/{roleId}")
    public ResponseEntity<String> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) throws Exception {
        securityService.addRoleToUser(userId, roleId);
        return ResponseEntity.ok("Role added to the user successfully");
    }

    //******Dexieme methode*****////

//    @PostMapping("/users/{username}/roles/{roleName}")
//    public ResponseEntity<String> addRoleToUser2(@PathVariable String username, @PathVariable String roleName) throws Exception {
//        securityService.addRoleToUser2(username, roleName);
//        return ResponseEntity.ok("Role added to the user successfully");
//    }

    @GetMapping("users")
    public List<AppUser> getAllUsers() {

        List<AppUser> allUsers = securityService.getAllUsers();
        return allUsers;

    }
}
