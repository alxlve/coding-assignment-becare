package com.alexislavie.coding.assignment.becare.service;

import com.alexislavie.coding.assignment.becare.entity.User;
import com.alexislavie.coding.assignment.becare.exception.UserEmailAlreadyExistsException;
import com.alexislavie.coding.assignment.becare.exception.UserNotFoundException;
import com.alexislavie.coding.assignment.becare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(
                        "No user was found with the following email: " + email
                )
        );
    }

    public User create(User user) {
        String email = user.getEmail();

        if (userRepository.countByEmail(email) > 0) {
            throw new UserEmailAlreadyExistsException(
                    "An user already exists with the following email: " + email
            );
        }

        return userRepository.save(user);
    }
}
