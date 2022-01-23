package ru.bulish.spring.geek_shop_boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.bulish.spring.geek_shop_boot.entity.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByNameIgnoreCase(String name);

}