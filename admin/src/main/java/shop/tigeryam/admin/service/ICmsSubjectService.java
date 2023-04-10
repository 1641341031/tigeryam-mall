package shop.tigeryam.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import shop.tigeryam.entity.CmsSubject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 专题表 服务类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-30
 */
public interface ICmsSubjectService extends IService<CmsSubject> {

    /**
     * 分页查询专题
     */
    Page<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 查询所有专题
     */
    List<CmsSubject> listAll();
}
