package shop.tigeryam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Component;
import shop.tigeryam.entity.PmsProduct;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tigeryam
 * @since 2022-12-15
 */
public interface IPmsProductService extends IService<PmsProduct> {
    List<PmsProduct> getProcutById(Integer id);
}
