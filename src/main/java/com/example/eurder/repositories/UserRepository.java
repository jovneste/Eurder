package com.example.eurder.repositories;

import com.example.eurder.domain.User;
import com.example.eurder.security.Role;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
@Repository
public class UserRepository {
    private ConcurrentHashMap<String, User> userDatabase;

    public UserRepository() {
        userDatabase = new ConcurrentHashMap<>();
        userDatabase.put("admin@eurder.com",new User("admin","adminson","admin@eurder.com","street"
                ,"044","root"));
        userDatabase.get("admin@eurder.com").setRole(Role.ADMIN);
        userDatabase.put("customer@eurder.com",new User("customer","notadminson","customer@eurder.com","street"
                ,"044","root"));
    }
    public void save(User user) {
        userDatabase.put(user.getEmailAddress(), user);
    }

    public ConcurrentHashMap<String, User> getUserDatabase() {
        return userDatabase;
    }

    public User getUser(String username) {
        return userDatabase.get(username);
    }
}
