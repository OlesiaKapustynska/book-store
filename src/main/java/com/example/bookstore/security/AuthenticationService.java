package com.example.bookstore.security;

import com.example.bookstore.dto.user.UserLoginRequestDto;
import com.example.bookstore.dto.user.UserLoginResponseDto;
import com.example.bookstore.dto.user.UserRegistrationRequestDto;
import com.example.bookstore.dto.user.UserResponseDto;
import com.example.bookstore.exception.RegistrationException;
import com.example.bookstore.mapper.UserMapper;
import com.example.bookstore.model.Role;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.email(), requestDto.password())
        );

        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponseDto(token);
    }

    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("The email is already used "
                    + requestDto.getEmail());
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role role = new Role();
        role.setName(Role.RoleName.USER);
        user.setRoles(Set.of(role));
        return userMapper.toDto(userRepository.save(user));
    }
}
