package com.example.bookstore.dto.user;

import com.example.bookstore.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@FieldMatch(first = "password", second = "repeatPassword")
@Data
public class UserRegistrationRequestDto {
    private Long id;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email should be valid")
    @Size(max = 64)
    private String email;
    @NotBlank
    @Size(min = 8, max = 35, message =
            "The password must be long between 8 and 35 symbols")
    private String password;
    @NotBlank
    @Size(min = 8, max = 35)
    private String repeatPassword;
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String lastName;
    @Size(max = 255)
    private String shippingAddress;
}
