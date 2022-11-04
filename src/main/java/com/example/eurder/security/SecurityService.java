package com.example.eurder.security;


import com.example.eurder.domain.User;
import com.example.eurder.exceptions.securityexceptions.UnauthorizatedException;
import com.example.eurder.exceptions.securityexceptions.UnknownUserException;
import com.example.eurder.exceptions.securityexceptions.WrongPasswordException;
import com.example.eurder.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        UsernamePassword usernamePassword = getUsernamePassword(authorization);
        User user = userRepository.getUser(usernamePassword.getUsername());
        if(user == null) {
            logger.error("Unknown user" + usernamePassword.getUsername());
            throw new UnknownUserException();
        }
        if(!user.doesPasswordMatch(usernamePassword.getPassword())) {
            logger.error("Password does not match for user " + usernamePassword.getUsername());
            throw new WrongPasswordException();
        }
        if(!user.canHaveAccessTo(feature)) {
            logger.error("User " + usernamePassword.getUsername() + " does not have access to " + feature);
            throw new UnauthorizatedException();
        }

    }

    public UsernamePassword getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UsernamePassword(username, password);
    }
}
