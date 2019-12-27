/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.controller;


import io.renren.annotation.Login;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.form.LoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 登录接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags="登录接口")
public class ApiLoginController {


    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        return R.ok();
    }

    @Login
    @PostMapping("logout")
    @ApiOperation("退出")
    public R logout(@ApiIgnore @RequestAttribute("userId") long userId){
        return R.ok();
    }

}
