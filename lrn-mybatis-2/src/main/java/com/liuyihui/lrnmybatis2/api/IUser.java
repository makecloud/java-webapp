package com.liuyihui.lrnmybatis2.api;

import com.liuyihui.lrnmybatis2.entity.User;

/**
 * 映射文件对应的接口
 *
 * @author liuyh
 */
public interface IUser {
    /**
     * 方法名为映射文件中的id
     *
     * @param id
     * @return
     */
    User selectUserByID(int id);
}
