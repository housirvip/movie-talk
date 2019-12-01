package edu.uta.movietalk.controller;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.PageResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.dto.ChangePass;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.entity.*;
import edu.uta.movietalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static edu.uta.movietalk.constant.ErrorMessage.USER_FOLLOWING_ITSELF;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Value("${file.avatar.root}")
    String rootPath;

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

    @GetMapping(value = "/follower")
    public BaseResponse<Page> getFollowers(PageDto pageDto, Authentication auth) {

        pageDto.getParamAsMap().put("toId", auth.getPrincipal());
        Page<UserFollow> userFollowPage = userService.pageUserFollow(pageDto);
        return new PageResponse<>(userFollowPage, userFollowPage.getTotal());
    }

    @PutMapping(value = "/following")
    public BaseResponse<Integer> createFollowing(@RequestParam int toId, Authentication auth) {

        Preconditions.checkArgument(toId != (Integer) auth.getPrincipal(), USER_FOLLOWING_ITSELF);
        Integer result = userService.followUser((Integer) auth.getPrincipal(), toId);
        return new ResultResponse<>(result);
    }

    @DeleteMapping(value = "/following")
    public BaseResponse<Boolean> deleteFollowing(@RequestParam int toId, Authentication auth) {

        Boolean result = userService.unfollowUser((Integer) auth.getPrincipal(), toId);
        return new ResultResponse<>(result);
    }

    @GetMapping(value = "/userRecord")
    public BaseResponse<UserRecord> selectUserRecord(Authentication auth) {

        return new ResultResponse<>(userService.selectUserRecord((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Page> allByAdmin(PageDto pageDto) {

        Page<User> userPage = userService.pageByParam(pageDto);

        return new PageResponse<>(userPage, userPage.getTotal());
    }

    @GetMapping(value = "/getById")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<User> getById(@RequestParam Integer uid) {

        return new ResultResponse<>(userService.oneByIdWithInfo(uid));
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Integer> updateByAdmin(@RequestBody User user) {

        return new ResultResponse<>(userService.update(user));
    }

    @GetMapping(value = "/adminRecord")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<AdminRecord> getAdminRecord() {

        return new ResultResponse<>(userService.selectAdminRecord());
    }

    @PostMapping(value = "/avatar")
    public BaseResponse<String> uploadAvatar(@RequestParam(value = "upFile", required = true) MultipartFile[] upFile, Authentication auth) {

        if (upFile != null && upFile.length > 0) {
            MultipartFile uploadFile = upFile[0];

            File dir = new File(rootPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            Integer uid = (Integer) auth.getPrincipal();
            String url = uid + ".jpg";
            File saveFile = new File(dir, url);
            try {
                uploadFile.transferTo(saveFile);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultResponse<>("error:" + e.getMessage());
            }

            User user = userService.oneByIdWithInfo(uid);
            UserInfo userInfo = user.getUserInfo();
            userInfo.setAvatar(url);
            userService.updateInfo(userInfo);
            return new ResultResponse<>("success");
        }

        return new ResultResponse<>("error: nothing to do");
    }
}
