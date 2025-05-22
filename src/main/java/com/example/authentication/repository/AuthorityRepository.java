package com.example.authentication.repository;

import com.example.authentication.dao.AuthorityEntity;
import com.example.authentication.dao.UserEntity;
import com.example.authentication.dto.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
@Repository
public class AuthorityRepository {
    private final List<AuthorityEntity> authorities = new ArrayList<>();

    public List<AuthorityEntity> findByUsername(String username){
        return Optional.of(authorities)
                .map(entities ->
                        entities.stream()
                                .filter(entity -> entity.getUsername().equals(username))
                                .map(user -> new AuthorityEntity(user.getId(),user.getUsername(), user.getAuthority()))
                                .collect(Collectors.toList())
                )
                .orElse(new ArrayList<>());
    }

    public void save(AuthorityEntity authorityEntity) {
        authorities.add(authorityEntity);
    }
}
