package shop.tigeryam.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证码服务接口
 * Create by tiger on 2022/11/30.
 */
public interface IVerifyCodeService {

    /**
     * 获取验证码
     * @param resp
     * @param session
     * @throws IOException
     */
    void getVerifyCode(HttpServletResponse resp, HttpSession session) throws IOException;
}
