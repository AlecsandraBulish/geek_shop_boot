package ru.bulish.spring.geek_shop_boot.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bulish.spring.geek_shop_boot.entity.RoleEntity;
import ru.bulish.spring.geek_shop_boot.entity.UserEntity;
import ru.bulish.spring.geek_shop_boot.repository.UserRepository;
import ru.bulish.spring.geek_shop_boot.service.RoleService;
import ru.bulish.spring.geek_shop_boot.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity user) {
        RoleEntity userRole = roleService.findByName("ROLE_USER");

        user.setRoles(new ArrayList<>(Collections.singletonList(userRole)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void setEnable(Long userId, Boolean enable) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        user.setEnabled(enable);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public Page<UserEntity> findAllByPage(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

}