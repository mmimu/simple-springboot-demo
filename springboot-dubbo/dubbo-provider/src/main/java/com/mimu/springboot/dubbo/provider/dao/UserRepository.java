package com.mimu.springboot.dubbo.provider.dao;

import com.mimu.springboot.dubbo.api.model.UserData;
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
public class UserRepository {
    private JdbcTemplate userJdbc;

    @Autowired
    public void setUserJdbc(JdbcTemplate userJdbcTemplate) {
        this.userJdbc = userJdbcTemplate;
    }

    public UserData getUserData(long pid) {
        String sql = "select * from user_info where person_id=?";
        List<UserData> userDataList = userJdbc.query(sql, new UserDataMapper(), pid);
        return userDataList.isEmpty() ? new UserData() : userDataList.get(0);
    }

    public boolean addUserData(long pid, String nickName) {
        String sql = "insert into user_info (`person_name`, `person_id`) values (?,?)";
        return userJdbc.update(sql, pid, nickName) > 0;
    }

    public boolean addTermData(long pid, int termId) {
        String sql = "insert into term_info (`term_id`, `person_id`) values (?,?)";
        return userJdbc.update(sql, termId, pid) > 0;
    }

    public class UserDataMapper implements RowMapper<UserData> {

        @Override
        public UserData mapRow(ResultSet resultSet, int i) throws SQLException {
            UserData data = new UserData();
            data.setPid(resultSet.getLong("person_id"));
            data.setNickName(resultSet.getString("person_name"));
            return data;
        }
    }
}
