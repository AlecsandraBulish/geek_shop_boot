package ru.bulish.spring.geek_shop_boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.bulish.spring.geek_shop_boot.entity.RoleEntity;

import java.util.Optional;


/**
 * Interface Repository layer for Role Entity
 * @author Spring DATA JPA
 * @see JpaRepository
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByNameIgnoreCase(String name);

}
