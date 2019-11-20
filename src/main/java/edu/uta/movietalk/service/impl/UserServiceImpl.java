package edu.uta.movietalk.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import edu.uta.movietalk.constant.Constant;
import edu.uta.movietalk.constant.ErrorMessage;
import edu.uta.movietalk.constant.UserGroup;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.entity.*;
import edu.uta.movietalk.mapper.UserFollowMapper;
import edu.uta.movietalk.mapper.UserInfoMapper;
import edu.uta.movietalk.mapper.UserMapper;
import edu.uta.movietalk.service.UserService;
import edu.uta.movietalk.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;
    private final UserFollowMapper userFollowMapper;

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

        // CAPTCHA
        // Preconditions.checkArgument(captchaService.verify(userDto.getCaptcha()), ErrorMessage.CAPTCHA_ERROR);

        User user = userMapper.selectByAccount(userDto.getAccount());
        // Account wan't found
        Preconditions.checkNotNull(user, ErrorMessage.ACCOUNT_NOT_FOUND);
        // Account was banned
        Preconditions.checkArgument(user.getEnable(), ErrorMessage.ACCOUNT_DISABLED);
        // verify username and password
        Preconditions.checkArgument(passwordEncoder.matches(userDto.getPassword(), user.getPassword()), ErrorMessage.ACCOUNT_PASSWORD_ERROR);

        return jwtUtils.encode(user.getId(), user.getRole());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String register(UserDto userDto) {

        // check if username, phone, or email already exist
        List<String> check = this.checkExist(userDto);
        Preconditions.checkArgument(check.size() == 0, check.toString());

        // phone sms code
        // Preconditions.checkArgument(smsService.verify(userDto.getCode(), userDto.getPhone()), ErrorMessage.SMS_ERROR);

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

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfoMapper.insertSelective(userInfo);

        return jwtUtils.encode(user.getId(), user.getRole());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer create(UserDto userDto) {

        // check if username, phone, or email already exist
        List<String> check = this.checkExist(userDto);
        Preconditions.checkArgument(check.size() == 0, check.toString());
        // check if operator was Admin or Root.
        Preconditions.checkArgument(userDto.getGroup() != UserGroup.Admin && userDto.getGroup() != UserGroup.Root, Constant.ERROR);

        User user = new User();

        // copy object
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

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfoMapper.insertSelective(userInfo);

        return user.getId();
    }

    @Override
    public String refresh(Integer uid) {

        User user = userMapper.selectByPrimaryKey(uid);

        return jwtUtils.encode(uid, user.getRole());
    }

    @Override
    public Integer changePass(UserDto userDto) {

        User user = userMapper.selectByPrimaryKey(userDto.getId());
        // Account wasn't found
        Preconditions.checkNotNull(user, ErrorMessage.ACCOUNT_NOT_FOUND);
        // verify username and password
        Preconditions.checkArgument(passwordEncoder.matches(userDto.getPassword(), user.getPassword()), ErrorMessage.ACCOUNT_PASSWORD_ERROR);

        user = new User();
        user.setId(userDto.getId());
        user.setPassword(passwordEncoder.encode(userDto.getNewPass()));

        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User oneById(Integer uid) {

        User user = userMapper.selectByPrimaryKey(uid);
        user.setPassword(null);

        return user;
    }

    @Override
    public User oneByIdWithInfo(Integer uid) {

        User user = userMapper.selectByPrimaryKey(uid);
        user.setPassword(null);

        UserInfo userInfo = userInfoMapper.selectByUid(uid);
        user.setUserInfo(userInfo);

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

        user.setPassword(null);
        userMapper.updateByPrimaryKeySelective(user);

        return user.getId();
    }

    @Override
    public Integer updateInfo(UserInfo userInfo) {

        userInfoMapper.updateByUidSelective(userInfo);

        return userInfo.getUid();
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

    @Override
    public Integer followUser(int fromId, int toId) {

        UserFollow userFollow = new UserFollow();
        userFollow.setFromId(fromId);
        userFollow.setToId(toId);
        userFollow.setCreateTime(new Date());

        userFollowMapper.insert(userFollow);
        return userFollow.getId();
    }

    @Override
    public Boolean unfollowUser(int fromId, int toId) {


        Map<String, Object> map = ImmutableMap.of("fromId", fromId, "toId", toId);

        userFollowMapper.selectBySelective(map).forEach(item -> userFollowMapper.deleteByPrimaryKey(item.getId()));

        return Boolean.TRUE;
    }

    @Override
    public Page<UserFollow> pageUserFollow(PageDto pageDto) {

        return userFollowMapper.selectBySelective(pageDto.putParam().getParamAsMap());
    }

    @Override
    public UserRecord selectUserRecord(Integer uid) {
        return userMapper.selectUserRecord(uid);
    }

    @Override
    public AdminRecord selectAdminRecord() {
        AdminRecord adminRecord = userMapper.selectAdminRecord();
        List<WebDataRecord> webDataRecordList = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("MM/dd");
        Date today = new Date();
        for(int i=0;i<5;i++) {

            long ms = today.getTime() - (4-i)*24*3600*1000L;
            Date prevDay = new Date(ms);

            WebDataRecord webDataRecord = new WebDataRecord();
            webDataRecord.setDate(sf.format(prevDay));
            webDataRecord.setVisitor(new Random().nextInt(100));
            webDataRecord.setLiker(new Random().nextInt(10));
            webDataRecord.setRatio(new Random().nextDouble());
            webDataRecordList.add(webDataRecord);
        }
        adminRecord.setWebDataRecordList(webDataRecordList);
        return adminRecord;
    }
}
