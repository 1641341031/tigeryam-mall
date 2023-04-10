package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import shop.tigeryam.entity.UmsResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台资源表 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-01
 */
public interface IUmsResourceService extends IService<UmsResource> {
    /**
     * 查询全部资源
     */
    List<UmsResource> listAll();

    /**
     * 分页查询资源
     */
    Page<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 删除资源
     */
    int delete(Long id);

    /**
     * 修改资源
     */
    int update(Long id, UmsResource umsResource);

    /**
     * 添加资源
     */
    int create(UmsResource umsResource);
}
