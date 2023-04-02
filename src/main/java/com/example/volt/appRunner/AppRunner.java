package com.example.volt.appRunner;

import com.example.volt.model.Role;
import com.example.volt.model.User;
import com.example.volt.service.UserServiceImp;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class AppRunner implements ApplicationRunner {

    private final UserServiceImp userService;
    private final PasswordEncoder passwordEncoder;

    public AppRunner(UserServiceImp userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@mail.com");
        List<Role> roleSet = Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_ADMIN"));
        admin.setRoles(roleSet);
        userService.add(admin);


        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setEmail("user@gmail.com");
        List<Role> roleSet2 = Arrays.asList(new Role("ROLE_USER"));
        user.setRoles(roleSet2);
        userService.add(user);
    }
}
