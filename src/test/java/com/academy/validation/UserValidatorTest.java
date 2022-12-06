package com.academy.validation;

import com.academy.dto.UserDto;
import com.academy.service.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author : Volha Salash
 */
@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @InjectMocks
    private UserValidator userValidator;
    @Mock
    private UserService userService;

    private static UserDto userDto;
    private static Errors errors;

    private static final String USERNAME_VALID = "username";
    private static final String EMAIL_VALID = "valid@email.com";
    private static final String PASSWORD_VALID = "password";
    private static final String EMPTY_STRING = "";
    private static final String STRING_SHORT = "short";
    private static final String STRING_LONG = "abcdefghijklnopqrstuvwxyz1234567890";

    @BeforeEach
    public void setUp() {
        userDto = UserDto.builder()
                .username(USERNAME_VALID)
                .email(EMAIL_VALID)
                .password(PASSWORD_VALID)
                .passwordConfirm(PASSWORD_VALID)
                .build();
        errors = new BeanPropertyBindingResult(userDto, "userDto");
        when(userService.findUserByUsername(anyString())).thenReturn(null);
        when(userService.findUserByEmail(anyString())).thenReturn(null);
    }

    @Test
    void validateUserValid() {
        userValidator.validate(userDto, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    void validateUserNameEmpty() {
        userDto.setUsername(EMPTY_STRING);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("username"));
    }
    @Disabled
    @Test
    void validateUserNameShort() {
        userDto.setUsername(STRING_SHORT);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("username"));
    }

    @Test
    void validateUserNameLong() {
        userDto.setUsername(STRING_LONG);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("username"));
    }

    @Test
    void validateUserNameDuplicate() {
        when(userService.findUserByUsername(anyString())).thenReturn(new UserDto());
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("username"));
    }

    @Test
    void validateUserEmailEmpty() {
        userDto.setEmail(EMPTY_STRING);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("email"));
    }

    @Test
    void validateUserEmailShort() {
        userDto.setEmail(STRING_SHORT);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("email"));
    }

    @Test
    void validateUserEmailLong() {
        userDto.setEmail(STRING_LONG);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("email"));
    }

    @Test
    void validateUserEmailDuplicate() {
        when(userService.findUserByEmail(anyString())).thenReturn(new UserDto());
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("email"));
    }

    @Test
    void validateUserPasswordEmpty() {
        userDto.setPassword(EMPTY_STRING);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("password"));
    }

    @Test
    void validateUserPasswordShort() {
        userDto.setPassword(STRING_SHORT);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("password"));
    }

    @Test
    void validateUserPasswordLong() {
        userDto.setPassword(STRING_LONG);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("password"));
    }

    @Test
    void validateUserPasswordConfirmEmpty() {
        userDto.setPasswordConfirm(EMPTY_STRING);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("passwordConfirm"));
    }

    @Test
    void validateUserPasswordDifferent() {
        userDto.setPassword(USERNAME_VALID);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("passwordConfirm"));
    }

    @Test
    void validateUserPasswordConfirmDifferent() {
        userDto.setPasswordConfirm(USERNAME_VALID);
        userValidator.validate(userDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("passwordConfirm"));
    }
}