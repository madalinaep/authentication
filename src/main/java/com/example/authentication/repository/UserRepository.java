package com.example.authentication.repository;

import com.example.authentication.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//public interface UserRepository extends JpaRepository<UserEntity, String> {
@Repository
public class UserRepository {
    private final List<UserEntity> users = new ArrayList<>();

    public void save(UserEntity userEntity) {
        users.add(userEntity);
    }

    public List<UserEntity> findAll() {
        return users;
    }

    public boolean existsByUsername(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    public UserEntity findByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }
}
