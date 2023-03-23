package shop.tigeryam.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.tigeryam.admin.service.IUmsRoleService;
import shop.tigeryam.api.CommonResult;
import shop.tigeryam.entity.UmsRole;

import java.util.List;

/**
 * 后台用户角色管理Controller
 */
@Controller
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    private IUmsRoleService roleService;

    // 获取所有角色
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> listAll() {
        List<UmsRole> roleList = roleService.list();
        return CommonResult.success(roleList);
    }
}
