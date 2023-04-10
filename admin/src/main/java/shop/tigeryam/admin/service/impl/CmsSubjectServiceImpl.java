package shop.tigeryam.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import shop.tigeryam.admin.service.ICmsSubjectService;
import shop.tigeryam.entity.CmsSubject;
import shop.tigeryam.mapper.CmsSubjectMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 专题表 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-30
 */
@Service
public class CmsSubjectServiceImpl extends ServiceImpl<CmsSubjectMapper, CmsSubject> implements ICmsSubjectService {
    @Autowired
    private CmsSubjectMapper subjectMapper;

    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.selectList(new QueryWrapper<CmsSubject>());
    }

    @Override
    public Page<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        Page<CmsSubject> subjectPage = new Page<>();
        QueryWrapper<CmsSubject> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isEmpty(keyword)) {
            queryWrapper.like("title", keyword);
        }
        return subjectMapper.selectPage(subjectPage, queryWrapper);
    }

}
