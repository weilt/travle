package com.hwt.domain.entity.user;

import java.io.Serializable;

/**
 * 淮信用户配置
 * @author 
 */
public class HxUserConfig implements Serializable {
    /**
     * 用户id
     */
    private Long user_id;

    /**
     * 加我需要验证   0-不需要   1-需要
     */
    private Byte communication_add_me_validate;

    /**
     * 向我推荐通讯录朋友  0-不可以  1-可以
     */
    private Byte communication_to_me_recommend_communication;

    /**
     * 陌生人能看10条  0-不可以 1-可以
     */
    private Byte friend_circle_stranger_see_10;

    /**
     * 允许朋友查看朋友圈的范围 0-全部 1-3天 2-半年
     */
    private Byte friend_circle_friend_see_day;

    private static final long serialVersionUID = 1L;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Byte getCommunication_add_me_validate() {
        return communication_add_me_validate;
    }

    public void setCommunication_add_me_validate(Byte communication_add_me_validate) {
        this.communication_add_me_validate = communication_add_me_validate;
    }

    public Byte getCommunication_to_me_recommend_communication() {
        return communication_to_me_recommend_communication;
    }

    public void setCommunication_to_me_recommend_communication(Byte communication_to_me_recommend_communication) {
        this.communication_to_me_recommend_communication = communication_to_me_recommend_communication;
    }

    public Byte getFriend_circle_stranger_see_10() {
        return friend_circle_stranger_see_10;
    }

    public void setFriend_circle_stranger_see_10(Byte friend_circle_stranger_see_10) {
        this.friend_circle_stranger_see_10 = friend_circle_stranger_see_10;
    }

    public Byte getFriend_circle_friend_see_day() {
        return friend_circle_friend_see_day;
    }

    public void setFriend_circle_friend_see_day(Byte friend_circle_friend_see_day) {
        this.friend_circle_friend_see_day = friend_circle_friend_see_day;
    }
}