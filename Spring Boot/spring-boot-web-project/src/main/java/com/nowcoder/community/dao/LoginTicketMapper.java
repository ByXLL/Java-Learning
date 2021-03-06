package com.nowcoder.community.dao;

import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/***
 * 登录凭证接口 编写用户对登录凭证操作的行为 在service层中实现
 */
@Mapper
@Deprecated  // 由redis接管 使用@Deprecated 注释 代表不推荐使用
public interface LoginTicketMapper {
    @Insert({
        "insert into login_ticket(user_id,ticket,status,expired) ",
        "values(#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")        // 开启默认生成id，
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({
        "select id,user_id,ticket,status,expired ",
        "from login_ticket where ticket=#{ticket}"
    })
    LoginTicket selectByTicket(String ticket);


    @Update({
        "<script>",
        "update login_ticket set status=#{status} where ticket=#{ticket} ",
        "<if test=\"ticket!=null\"> ",
        "and 1=1 ",
        "</if>",
        "</script>"
    })
    int updateStatus(String ticket,int status);
}
