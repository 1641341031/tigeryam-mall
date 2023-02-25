package shop.tigeryam.util;

import shop.tigeryam.api.CommonResult;

/**
 * 邮件的工具服务类
 */
public interface IMailService {

    void sendregisterMail(String to, String subject, String content) throws Exception;
}
