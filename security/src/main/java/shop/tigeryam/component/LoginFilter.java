package shop.tigeryam.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    public LoginFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 需要是 POST 请求
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        HttpSession session = request.getSession();
        // 获得 session 中的 验证码值
        String sessionVerifyCode = (String) session.getAttribute("verify_code");
        // 判断请求格式是否是 JSON
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE ) || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
            } finally {
                String code = loginData.get("code");
                checkVerifyCode(sessionVerifyCode, code);
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            if (StringUtils.isEmpty(username)) {
                throw new AuthenticationServiceException("用户名不能为空");
            }
            if (StringUtils.isEmpty(password)) {
                throw new AuthenticationServiceException("密码不能为空");
            }
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            checkVerifyCode(sessionVerifyCode, request.getParameter("code"));
            return super.attemptAuthentication(request, response);
        }
    }

    private void checkVerifyCode(String sessionVerifyCode, String code) {
        if (StringUtils.isEmpty(code)) {
            throw new AuthenticationServiceException("验证码不能为空!");
        }
        if (StringUtils.isEmpty(sessionVerifyCode)) {
            throw new AuthenticationServiceException("请重新申请验证码!");
        }
        if (!sessionVerifyCode.equalsIgnoreCase(code)) {
            throw new AuthenticationServiceException("验证码错误!");
        }
    }
}
