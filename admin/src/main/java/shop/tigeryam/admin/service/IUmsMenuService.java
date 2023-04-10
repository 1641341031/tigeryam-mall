package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import shop.tigeryam.admin.dto.UmsMenuNode;
import shop.tigeryam.entity.UmsMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台菜单表 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-01
 */
public interface IUmsMenuService extends IService<UmsMenu> {
    /**
     * 树形结构返回所有菜单列表
     */
    List<UmsMenuNode> treeList();

    /**
     * 分页查询后台菜单
     */
    Page<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 修改菜单显示状态
     */
    int updateHidden(Long id, Integer hidden);

    /**
     * 创建后台菜单
     */
    int create(UmsMenu umsMenu);

    /**
     * 根据ID删除菜单
     */
    int delete(Long id);

    /**
     * 修改后台菜单
     */
    int update(Long id, UmsMenu umsMenu);

    /**
     * 根据ID获取菜单详情
     */
    UmsMenu getItem(Long id);

}
