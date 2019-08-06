package com.cwh.bbs.mapper;

import com.cwh.bbs.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, create_time, create_modified) values (#{name}, #{account_id}, #{token}, #{create_time}, #{create_modified})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User getUserByToken(@Param("token") String token);
}
