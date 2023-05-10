package com.fund.business.mapper;

import com.fund.api.entity.Client;
import org.apache.ibatis.annotations.*;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/08  11:14
 */

@Mapper
public interface TestMapper {
    @Select("select * from client where client_id = #{id}")
    Client getClientById(@Param("id") int id);
}
