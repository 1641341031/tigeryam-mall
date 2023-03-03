package shop.tigeryam.admin.service.impl;

import shop.tigeryam.admin.service.IUmsMenuService;
import shop.tigeryam.entity.UmsMenu;
import shop.tigeryam.mapper.UmsMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-01
 */
@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {

}
