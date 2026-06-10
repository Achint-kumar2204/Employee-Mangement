package com.Employee_Management.auth_service.Services;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String validate(
            String username,
            String password) {

        if("admin".equals(username)
                && "admin123".equals(password))
            return "ADMIN";

        if("user".equals(username)
                && "user123".equals(password))
            return "USER";

        return null;
    }

}
