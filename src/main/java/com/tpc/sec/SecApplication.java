package com.tpc.sec;

import com.tpc.sec.entities.AppRole;
import com.tpc.sec.entities.AppUser;
import com.tpc.sec.repositories.AppRoleRepository;
import com.tpc.sec.repositories.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class SecApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecApplication.class, args);
	}

//	@Bean
//	CommandLineRunner start(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository){
//		return args -> {
//			Stream.of("user1","user2","user3").forEach(u->{
//				AppUser user=new AppUser(null,u,u+"@gmail.com",new ArrayList<>());
//				appUserRepository.save(user);
//			});
//
//			Stream.of("ADMIN","MANAGER","USER").forEach(r->{
//				AppRole role=new AppRole(null,r);
//				appRoleRepository.save(role);
//			});
//		};
//	}
}
