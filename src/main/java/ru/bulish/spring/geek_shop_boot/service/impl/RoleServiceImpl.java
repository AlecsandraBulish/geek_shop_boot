package ru.bulish.spring.geek_shop_boot.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bulish.spring.geek_shop_boot.entity.RoleEntity;
import ru.bulish.spring.geek_shop_boot.repository.RoleRepository;
import ru.bulish.spring.geek_shop_boot.service.RoleService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity findByName(String name) {
        return roleRepository.findByNameIgnoreCase(name)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

}
