package edu.uta.movietalk.controller;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.ErrorResponse;
import edu.uta.movietalk.base.PageResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.dto.ChangePass;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.entity.User;
import edu.uta.movietalk.entity.UserFollow;
import edu.uta.movietalk.entity.UserInfo;
import edu.uta.movietalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static edu.uta.movietalk.constant.ErrorMessage.REVIEW_LIKE_NOT_FOUND;
import static edu.uta.movietalk.constant.ErrorMessage.USER_FOLLOWING_ITSELF;

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

        return new ResultResponse<>(userService.oneByIdWithInfo((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/follow")
    public BaseResponse<User> follow(Authentication auth) {

        return new ResultResponse<>(userService.oneByIdWithInfo((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/friend")
    public BaseResponse<User> friend(@RequestParam int id) {

        User friendInfo = userService.oneByIdWithInfo(id);
//        friendInfo.setPassword(null);
//        friendInfo.setCreateTime(null);
//        friendInfo.setGroup(null);
//        friendInfo.setRole(null);
//        friendInfo.setEmail(null);
//        friendInfo.setLevel(null);
//        friendInfo.setPhone(null);
//        friendInfo.setEnable(null);
//        friendInfo.setUpdateTime(null);
//        UserInfo fiendUserInfo = friendInfo.getUserInfo();
//        fiendUserInfo.setCreateTime(null);
//        fiendUserInfo.setJob(null);
//        fiendUserInfo.setState(null);
//        fiendUserInfo.setUpdateTime(null);

        return new ResultResponse<>(friendInfo);
    }

    @GetMapping(value = "/refresh")
    public BaseResponse<String> refresh(Authentication auth) {

        return new ResultResponse<>(userService.refresh((Integer) auth.getPrincipal()));
    }

    @PutMapping(value = "/password")
    public BaseResponse<Integer> modifyPassword(@RequestBody @Validated(value = ChangePass.class) UserDto userDto, Authentication auth) {

        userDto.setId((Integer) auth.getPrincipal());
        return new ResultResponse<>(userService.changePass(userDto));
    }

    @PutMapping(value = "/info")
    public BaseResponse<Integer> modifyInfo(@RequestBody UserInfo userInfo, Authentication auth) {

        userInfo.setUid((Integer) auth.getPrincipal());
        return new ResultResponse<>(userService.updateInfo(userInfo));
    }

    @GetMapping(value = "/following")
    public BaseResponse<Page> getFollowing(PageDto pageDto, Authentication auth) {

        pageDto.getParamAsMap().put("fromId", auth.getPrincipal());
        Page<UserFollow> userFollowPage = userService.pageUserFollow(pageDto);
        return new PageResponse<>(userFollowPage, userFollowPage.getTotal());
    }

    @PutMapping(value = "/following")
    public BaseResponse<Integer> createFollowing(@RequestParam int toId, Authentication auth) {

        Preconditions.checkArgument(toId != (Integer) auth.getPrincipal(),USER_FOLLOWING_ITSELF);
        Integer result = userService.followUser((Integer) auth.getPrincipal(), toId);
        return new ResultResponse<>(result);
    }

    @DeleteMapping(value = "/following")
    public BaseResponse<Boolean> deleteFollowing(@RequestParam int toId, Authentication auth) {

        Boolean result = userService.unfollowUser((Integer) auth.getPrincipal(), toId);
        return new ResultResponse<>(result);
    }
}
