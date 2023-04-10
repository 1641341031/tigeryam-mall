package shop.tigeryam.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import shop.tigeryam.entity.CmsPrefrenceArea;
import shop.tigeryam.mapper.CmsPrefrenceAreaMapper;
import shop.tigeryam.admin.service.ICmsPrefrenceAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优选专区 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-30
 */
@Service
public class CmsPrefrenceAreaServiceImpl extends ServiceImpl<CmsPrefrenceAreaMapper, CmsPrefrenceArea> implements ICmsPrefrenceAreaService {

    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectList(new QueryWrapper<CmsPrefrenceArea>());
    }
}
