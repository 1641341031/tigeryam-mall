package shop.tigeryam.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.tigeryam.admin.dto.OssCallbackResult;
import shop.tigeryam.admin.dto.OssPolicyResult;
import shop.tigeryam.admin.service.OssService;
import shop.tigeryam.api.CommonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss对象存储管理Controller
 */
@Controller
@RequestMapping("/aliyun/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    // Oss上传签名生成
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return CommonResult.success(result);
    }

    // Oss上传成功回调
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }

}
