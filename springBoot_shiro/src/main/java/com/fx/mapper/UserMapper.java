package com.fx.mapper;

import com.fx.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from t_user where account=#{account}")
    User selUserInfoByName(@Param("account") String account);
}