package com.gsh.read.model.http;

import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.common.vo.request.SelectBookFormsVo;
import com.gsh.read.common.vo.request.SelectUserInfoVo;
import com.gsh.read.common.vo.request.UploadDataVo;

/**
 * http请求接口
 */
public interface IHttpRequest {

    /**
     * 登录
     * @param vo
     * @param callback
     * @throws Exception
     */
    void login(LoginVo vo,HttpCallback callback) throws Exception;

    /**
     * 查询表册信息
     * @param vo
     * @param callback
     * @throws Exception
     */
    void selectBookforms(SelectBookFormsVo vo, HttpCallback callback) throws Exception;

    /**
     * 获取用水户详情
     * @param vo
     * @param callback
     * @throws Exception
     */
    void selectUserInfo(SelectUserInfoVo vo, HttpCallback callback) throws Exception;

    /**
     * 上传用水户止码
     * @param vo
     * @param callback
     * @throws Exception
     */
    void uploadData(UploadDataVo vo, HttpCallback callback) throws Exception;
}
