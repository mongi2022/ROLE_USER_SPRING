package com.tpc.sec.repositories;

import com.tpc.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole  findByRoleName(String roleName);
}
