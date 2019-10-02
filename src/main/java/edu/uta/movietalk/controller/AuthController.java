package edu.uta.movietalk.controller;

import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.client.TestClient;
import edu.uta.movietalk.dto.Login;
import edu.uta.movietalk.dto.Register;
import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author housirvip
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final TestClient testClient;

    @PostMapping(value = "/login")
    public BaseResponse<String> login(@RequestBody @Validated(value = Login.class) UserDto userDto) {

        return new ResultResponse<>(userService.login(userDto));
    }

    @PostMapping(value = "/signup")
    public BaseResponse<String> signUp(@RequestBody @Validated(value = Register.class) UserDto userDto) {

        return new ResultResponse<>(userService.register(userDto));
    }

    @GetMapping(value = "/test")
    public BaseResponse<Object> test() {

        return new ResultResponse<>(testClient.test());
    }
}
