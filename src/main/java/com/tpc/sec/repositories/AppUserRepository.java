package com.tpc.sec.repositories;

import com.tpc.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
AppUser findByUsername(String username);
}
