package ru.bulish.spring.geek_shop_boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.bulish.spring.geek_shop_boot.entity.UserEntity;

import java.util.Optional;


/**
 * Interface Repository layer for User Entity
 * @author Spring DATA JPA
 * @see JpaRepository
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
