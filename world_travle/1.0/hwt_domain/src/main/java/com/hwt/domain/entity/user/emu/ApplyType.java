package com.hwt.domain.entity.user.emu;

/**
 * Created by RO on 2018/3/12.
 * 好友操作类型
 */
public enum ApplyType {
    apply(1,"好友申请"),defriend(2,"拉黑"),agree(3,"通过申请"),refuse(4,"拒绝申请");

    ApplyType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    int type;
    String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
