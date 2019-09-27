package edu.uta.movietalk.controller;

import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.dto.ChangePass;
import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.entity.User;
import edu.uta.movietalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/detail")
    public BaseResponse<User> detail(Authentication auth) {

        return new ResultResponse<>(userService.oneById((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/refresh")
    public BaseResponse<String> refresh(Authentication auth) {

        return new ResultResponse<>(userService.refresh((Integer) auth.getPrincipal()));
    }

    @PutMapping(value = "/change-pass")
    public BaseResponse<Integer> refresh(@RequestBody @Validated(value = ChangePass.class) UserDto userDto, Authentication auth) {

        userDto.setId((Integer) auth.getPrincipal());
        return new ResultResponse<>(userService.changePass(userDto));
    }
}
