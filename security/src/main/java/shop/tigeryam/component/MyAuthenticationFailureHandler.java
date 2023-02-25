package shop.tigeryam.component;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 登录失败回调
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String msg = "";
        if (e instanceof LockedException) {
            msg = "账户被锁定，请联系管理员!";
        }
        else if (e instanceof BadCredentialsException) {
            msg = "用户名或者密码输入错误，请重新输入!";
        }
        out.write(e.getMessage());
        out.flush();
        out.close();
    }
}
