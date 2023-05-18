package com.fund.client.mapper;

import com.fund.api.dto.ClientDTO;
import com.fund.api.entity.Client;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/08  11:14
 */

@Mapper
public interface ClientMapper {
    /**
     * @param client 前端提交的关于客户的表单信息
     */
    @Insert("insert into client(client_name, client_type, card_type, card_number, sex, age, phone_number, risk_level)" +
            "values(#{clientName}, #{clientType}, #{cardType}, #{cardNumber}, #{sex}, #{age}, #{phoneNumber}, #{riskLevel})")
    void addClient(Client client);

    /**
     * @param client 前端提交的对客户信息修改的表单信息
     */
    @Update("update client set client_name = #{clientName}, client_type = #{clientType}, " +
            "card_type = #{cardType}, card_number = card_number, sex = #{sex}, age = #{age}, " +
            "phone_number = #{phoneNumber}, risk_level = #{riskLevel}")
    void updateClient(Client  client);

    /**
     * @param id 客户编号
     * @return 返回客户对象
     */
    @Delete("delete from client where client_id = #{id}")
    Client deleteClientById(@Param("id") int id);

    /**
     * @param id 客户编号
     * @return 返回客户对象
     */
    @Select("select client_id, client_name, client_type, card_type, card_number, sex, age, phone_number, risk_level, create_time " +
            "from client where client_id = #{id}")
    Client getClientById(@Param("id") int id);

    @Select("select client_name from client where client_name like #{likeName}")
    List<String> getClientByLikeName(String likeName);

    /**
     * @return  返回查询的所有客户对象，注意要使用分页插件。
     */
    @Select("select client_id, client_name, client_type, card_type, card_number, sex, age, phone_number, risk_level, create_time from client")
    List<Client> getClientAll();

    List<ClientDTO> getClientLikely(String keyword);
}
