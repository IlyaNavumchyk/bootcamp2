package by.bootcamp.service.impl;

import by.bootcamp.domain.Role;
import by.bootcamp.domain.User;
import by.bootcamp.domain.UserRoles;
import by.bootcamp.exception.EntityAlreadyExistException;
import by.bootcamp.repository.RoleRepository;
import by.bootcamp.repository.UserRepository;
import by.bootcamp.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Page<User> findAll(Pageable page) {
        return userRepository.findAll(page);
    }

    @Transactional
    @Override
    public User create(User user, UserRoles roleName) {

        if (!checkUserEmailForNotExistInDB(user)) {
            throw new EntityAlreadyExistException(
                    String.format("User with this email \"%s\" already exists", user.getEmail()));
        }

        Role role = roleRepository.findByRoleName(roleName);
        user.setRoles(Set.of(role));
        role.getUsers().add(user);

        return userRepository.save(user);
    }

    private boolean checkUserEmailForNotExistInDB(final User user) {

        String userEmail = user.getEmail();
        Optional<User> userByEmail = userRepository.findByEmail(userEmail);

        return userByEmail.isEmpty();
    }
}
