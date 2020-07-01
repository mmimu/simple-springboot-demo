package com.mimu.springboot.demo.dao;

import com.mimu.springboot.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * author: mimu
 * date: 2019/7/7
 */
@Repository
public class UserInfoRepository {
    private JdbcTemplate userInfoJdbcTemplate;

    @Autowired
    public void setUserInfoJdbcTemplate(JdbcTemplate userInfoJdbcTemplate) {
        this.userInfoJdbcTemplate = userInfoJdbcTemplate;
    }

    public UserInfo getUserInfo(long pid) {
        String sql = "select * from user_info where person_id=?";
        List<UserInfo> userInfoList = userInfoJdbcTemplate.query(sql, new UserInfoMapper(), pid);
        return userInfoList.isEmpty() ? new UserInfo() : userInfoList.get(0);
    }

    public boolean addUserInfo(long pid, String nickName) {
        String sql = "insert into user_info (`person_name`, `person_id`) values (?,?)";
        return userInfoJdbcTemplate.update(sql, pid, nickName) > 0;
    }

    public boolean deleteUserInfo(long pid) {
        String sql = "delete from user_info where person_id=?";
        return userInfoJdbcTemplate.update(sql, pid) > 0;
    }

    public boolean addTermInfo(long pid, int termId) {
        String sql = "insert into term_info (`term_id`, `person_id`) values (?,?)";
        return userInfoJdbcTemplate.update(sql, termId, pid) > 0;
    }

    public boolean updateUserInfo(UserInfo userInfo) {
        String sql = "update user_info set person_name=? where person_id=?";
        return userInfoJdbcTemplate.update(sql, userInfo.getPersonName(), userInfo.getPersonId()) > 0;
    }

    public class UserInfoMapper implements RowMapper<UserInfo> {

        @Override
        public UserInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(resultSet.getInt("id"));
            userInfo.setPersonId(resultSet.getInt("person_id"));
            userInfo.setPersonName(resultSet.getString("person_name"));
            return userInfo;
        }
    }
}
