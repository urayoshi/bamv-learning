package bamv.training.microposts.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import bamv.training.microposts.dao.MUserDao;
import bamv.training.microposts.dto.UserDto;
import bamv.training.microposts.entity.MUser;
import bamv.training.microposts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MUserDao mUserDao;

    @Override
    public UserDto findUser(String userId) {
        MUser mUser = mUserDao.findUser(userId);
        return new UserDto(
                mUser.getUserId(),
                mUser.getName()
        );
    }

    @Override
    @Transactional
    public int createNewUser(String userId, String name, String password) {
        return mUserDao.addNewUser(userId, name, password);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<MUser> mUsers = mUserDao.findAllUsers();
        List<UserDto> users = new java.util.ArrayList<>();

        for (MUser mUser : mUsers) {
            UserDto user = new UserDto(
                    mUser.getUserId(),
                    mUser.getName()
            );
            users.add(user);
        }

        return users;
    }


}
