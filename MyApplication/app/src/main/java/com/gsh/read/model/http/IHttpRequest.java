package com.gsh.read.model.http;

import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.vo.request.BookFormVo;
import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.common.vo.request.UserVo;

/**
 * http请求接口
 */
public interface IHttpRequest {

    /**
     * 用户登录
     * @param vo
     * @param callback
     * @throws Exception
     */
    void login(LoginVo vo,HttpCallback callback) throws Exception;

    /**
     * 分页查询表册列表
     * @param vo
     * @param callback
     * @throws Exception
     */
    void queryBookFormList(BookFormVo vo, HttpCallback callback) throws Exception;

    /**
     * 根据用水户CODE查询用水户信息
     * @param vo
     * @param callback
     * @throws Exception
     */
    void queryUserByCode(UserVo vo, HttpCallback callback) throws Exception;
}
