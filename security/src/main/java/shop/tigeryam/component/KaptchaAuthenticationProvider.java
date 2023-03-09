package shop.tigeryam.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KaptchaAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (req.getContentType().equals(MediaType.APPLICATION_JSON_VALUE )){
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = new ObjectMapper().readValue(req.getInputStream(), Map.class);
            } catch (IOException e) {
            } finally {
                String kaptcha = loginData.get("code");
                String sessionKaptcha = (String)req.getSession().getAttribute("verify_code");
                if (kaptcha != null && sessionKaptcha != null && kaptcha.equalsIgnoreCase(sessionKaptcha)){
                    return super.authenticate(authentication);
                }
                throw new AuthenticationServiceException("验证码输入错误");
            }
        }else{
            String kaptcha = req.getParameter("verify_code");
            String sessionKaptcha = (String)req.getSession().getAttribute("verify_code");
            if (kaptcha != null && sessionKaptcha != null && kaptcha.equalsIgnoreCase(sessionKaptcha)){
                return super.authenticate(authentication);
            }
            throw new AuthenticationServiceException("验证码输入错误");
        }
    }
}
