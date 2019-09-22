package edu.uta.movietalk.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import edu.uta.movietalk.constant.Constant;
import edu.uta.movietalk.constant.ErrorMessage;
import edu.uta.movietalk.constant.UserGroup;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.entity.User;
import edu.uta.movietalk.mapper.UserMapper;
import edu.uta.movietalk.service.UserService;
import edu.uta.movietalk.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Value("${user.role}")
    private String initRole;

    @Value("${user.group}")
    private UserGroup initGroup;

    @Value("${user.level}")
    private Integer initLevel;

    @Override
    public String login(UserDto userDto) {

        //验证码
//        Preconditions.checkArgument(captchaService.verify(userDto.getCaptcha()), ErrorMessage.CAPTCHA_ERROR);

        User user = userMapper.selectByAccount(userDto.getAccount());
        //账户未找到
        Preconditions.checkNotNull(user, ErrorMessage.ACCOUNT_NOT_FOUND);
        //账户被封禁
        Preconditions.checkArgument(user.getEnable(), ErrorMessage.ACCOUNT_DISABLED);
        //验证密码
        Preconditions.checkArgument(passwordEncoder.matches(userDto.getPassword(), user.getPassword()), ErrorMessage.ACCOUNT_PASSWORD_ERROR);

        return jwtUtils.encode(user.getId(), user.getRole());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String register(UserDto userDto) {

        //判断账户是否已经存在
        List<String> check = this.checkExist(userDto);
        Preconditions.checkArgument(check.size() == 0, check.toString());

        //手机验证码
//        Preconditions.checkArgument(smsService.verify(userDto.getCode(), userDto.getPhone()), ErrorMessage.SMS_ERROR);

        User user = new User();
        user.setCreateTime(new Date());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPhone(userDto.getPhone());
        user.setGroup(initGroup);
        user.setLevel(initLevel);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        List<String> roles = Lists.newArrayList(Constant.ROLE_PREFIX + Constant.USER);
        if (!Constant.USER.equals(initRole)) {
            roles.add(Constant.ROLE_PREFIX + initRole);
        }
        user.setRole(roles);

        userMapper.insertSelective(user);

        return jwtUtils.encode(user.getId(), user.getRole());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer create(UserDto userDto) {

        //判断账户是否已经存在
        List<String> check = this.checkExist(userDto);
        Preconditions.checkArgument(check.size() == 0, check.toString());
        //判断用户组
        Preconditions.checkArgument(userDto.getGroup() != UserGroup.Admin && userDto.getGroup() != UserGroup.Root, Constant.ERROR);

        User user = new User();

        //拷贝用户
        BeanUtils.copyProperties(userDto, user);

        if (user.getGroup() == null) {
            user.setGroup(initGroup);
        }

        List<String> roles = Lists.newArrayList(Constant.ROLE_PREFIX + Constant.USER);
        if (!Constant.USER.equals(initRole)) {
            roles.add(Constant.ROLE_PREFIX + Constant.VIP);
        }
        user.setRole(roles);

        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userMapper.insertSelective(user);

        return user.getId();
    }

    @Override
    public String refresh(Integer uid) {

        User user = userMapper.selectByPrimaryKey(uid);

        return jwtUtils.encode(uid, user.getRole());
    }

    @Override
    public User oneById(Integer uid) {

        User user = userMapper.selectByPrimaryKey(uid);
        user.setPassword(null);

        return user;
    }

    @Override
    public Page<User> pageByParam(PageDto pageDto) {

        Page<User> userPage = userMapper.listByParam(pageDto.putParam().getParamAsMap());
        if (userPage.getTotal() == 0) {
            return userPage;
        }

        return userPage;
    }
    @Override
    public Integer update(User user) {

        userMapper.updateByPrimaryKeySelective(user);

        return user.getId();
    }

    private List<String> checkExist(UserDto userDto) {

        List<String> result = Lists.newArrayList();

        if (Boolean.TRUE.equals(userMapper.existUsername(userDto.getUsername()))) {
            result.add(ErrorMessage.USERNAME_EXIST);
        }
        if (Boolean.TRUE.equals(userMapper.existEmail(userDto.getEmail()))) {
            result.add(ErrorMessage.EMAIL_EXIST);
        }
        if (Boolean.TRUE.equals(userMapper.existPhone(userDto.getPhone()))) {
            result.add(ErrorMessage.PHONE_EXIST);
        }

        return result;
    }
}
