package com.fx.mapper;

import com.fx.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from t_user where account=#{account}")
    User selUserInfoByAccount(@Param("account") String account);

    @Select("SELECT t3.rtag FROM t_user t1\n" +
            "LEFT JOIN t_user_role t2 ON t1.uid = t2.uid\n" +
            "LEFT JOIN t_role t3 ON t2.rid = t3.rid\n" +
            "WHERE t1.account = #{account}")
    List<String> seluserRolesByAccount(@Param("account") String account);

    @Select("SELECT DISTINCT t5.pinfo FROM t_user t1\n" +
            "LEFT JOIN t_user_role t2 ON t1.uid = t2.uid\n" +
            "LEFT JOIN t_role t3 ON t2.rid = t3.rid\n" +
            "LEFT JOIN t_role_permission t4 ON t3.rid = t4.rid\n" +
            "LEFT JOIN t_permission t5 ON t4.pid = t5.pid\n" +
            "WHERE t5.pid IS NOT NULL AND t1.account = #{account}")
    List<String> seluserPsInfoByAccount(@Param("account") String account);
}
