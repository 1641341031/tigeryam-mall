package shop.tigeryam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.tigeryam.api.CommonResult;
import shop.tigeryam.dto.ForgetPwdParm;
import shop.tigeryam.dto.ForgetPwdUpdatePwdParam;
import shop.tigeryam.dto.LoginParam;
import shop.tigeryam.dto.RegisterParam;
import shop.tigeryam.entity.UmsUser;
import shop.tigeryam.service.impl.UmsUserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录注册管理 Controller
 * Create by tiger on 2022/11/26
 */
@Controller
@RequestMapping("/sso")
@Slf4j
public class UmsUserController {
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsUserServiceImpl umsUserServiceImpl;

    /**
     * 用户登录
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody LoginParam loginParam,HttpSession session){
        if(loginParam.getCode() != null){
            String verify_code = (String)session.getAttribute("verify_code");
            if(!loginParam.getCode().equals(verify_code)){
                return CommonResult.validateFailed("验证码有误！");
            }
        }
        String token = umsUserServiceImpl.login(loginParam.getUsername(), loginParam.getPassword());
        if(token == null){
            return CommonResult.validateFailed("用户名或密码有误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    /**
     * 注册验证码
     * @param mailAddress
     * @param session
     * @return
     */
    @RequestMapping(value = "/registerCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult registerCode(String mailAddress, HttpSession session){
        try{
            umsUserServiceImpl.sendMail(mailAddress, session, "registerCode", "注册验证码: ");
            log.info("注册邮件发送成功");
            return CommonResult.success("", "注册邮件发送成功");
        }catch (Exception e){
            log.error("注册邮件发送异常！！！");
            return CommonResult.failed("注册邮件发送异常！！！");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestBody RegisterParam registerParam, HttpSession session){
        String registerCode = (String) session.getAttribute("registerCode");
        if ( !registerParam.getRegisterCode().equals(registerCode)){
            log.error("验证码不一致！");
            return CommonResult.failed("验证码不一致！");
        }
        Integer result = umsUserServiceImpl.register(registerParam);
        if(result > 0){
            return CommonResult.success("", "成功添加用户");
        }else {
            return CommonResult.failed("新添加用户失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/fgPwdVerifyCode", method = RequestMethod.POST)
    public CommonResult getForgetPwdVerifyCode(@RequestBody ForgetPwdParm pwdParm, HttpSession session){
        UmsUser user = umsUserServiceImpl.loadUserByEmail(pwdParm.getMailAddress());
        if(user == null){
            return CommonResult.failed("用户邮箱不存在!");
        } else {
            try{
                umsUserServiceImpl.sendMail(pwdParm.getMailAddress(), session, "forgetPwdVerifyCode", "验证码: ");
                log.info("忘记密码操作获取验证码发送邮件成功");
                return CommonResult.success("验证码已发送！");
            }catch (Exception e){
                log.error("忘记密码操作获取验证码发送邮件失败");
                return CommonResult.failed("验证码发送失败！");
            }
        }
    }

    /**
     * 用户忘记密码操作密码更新
     * @param forgetPwdUpdatePwdParam
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/updateForgetPassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult forgetPwd(@RequestBody ForgetPwdUpdatePwdParam forgetPwdUpdatePwdParam, HttpSession httpSession) {
        String forgetPwdVerifyCode = (String) httpSession.getAttribute("forgetPwdVerifyCode");
        if(!forgetPwdUpdatePwdParam.getVerifyCode().equals(forgetPwdVerifyCode)){
            log.error("验证码不一致！");
            return CommonResult.failed("验证码不一致！");
        }
        int userboolean = umsUserServiceImpl.updatePassword(forgetPwdUpdatePwdParam.getNewPassword(),
                forgetPwdUpdatePwdParam.getMailAddress(), forgetPwdUpdatePwdParam.getUsername());
        if (userboolean > 0){
            log.info("更改密码操作成功");
            return CommonResult.success("更改密码操作成功");
        }else {
            log.error("更改密码操作失败");
            return CommonResult.failed("更改密码操作失败");
        }
    }

}
