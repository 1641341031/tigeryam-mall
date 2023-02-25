package shop.tigeryam.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.tigeryam.admin.dto.LoginParam;
import shop.tigeryam.admin.service.impl.UmsAdminServiceImpl;
import shop.tigeryam.api.CommonResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户管理Controller
 */
@Controller
@RequestMapping("/admin")
public class UmsAdminController {
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsAdminServiceImpl umsAdminService;

    /**
     * 后台管理员登录登录
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody LoginParam loginParam){
        String token = umsAdminService.login(loginParam.getUsername(), loginParam.getPassword());
        if(token == null){
            return CommonResult.validateFailed("用户名或密码有误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }
}
