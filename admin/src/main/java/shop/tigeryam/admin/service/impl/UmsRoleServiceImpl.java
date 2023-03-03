package shop.tigeryam.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import shop.tigeryam.admin.dao.UmsRoleDao;
import shop.tigeryam.admin.service.IUmsRoleService;
import shop.tigeryam.entity.UmsMenu;
import shop.tigeryam.entity.UmsRole;
import shop.tigeryam.mapper.UmsRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-02-28
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {
    @Autowired
    private UmsRoleDao roleDao;

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }
}
