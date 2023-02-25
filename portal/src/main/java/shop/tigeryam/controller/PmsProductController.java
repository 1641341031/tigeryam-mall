package shop.tigeryam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.tigeryam.api.CommonResult;
import shop.tigeryam.entity.PmsProduct;
import shop.tigeryam.service.IPmsProductService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tigeryam
 * @since 2022-12-15
 */
@Controller
@RequestMapping("/pms-product")
public class PmsProductController {
    @Autowired
    private IPmsProductService iPmsProductService;

    @ResponseBody
    @GetMapping("/product/{id}")
    public CommonResult<List<PmsProduct>> getProductById(@PathVariable("id") Integer productId){
        List<PmsProduct> products = iPmsProductService.getProcutById(productId);
        return CommonResult.success(products);
    }

    @ResponseBody
    @GetMapping("/getAllRecommend")
    public CommonResult<List<PmsProduct>> getAllRecommendProduct(){
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("recommand_status", 1);
        List<PmsProduct> productList = iPmsProductService.list(queryWrapper);
        if(productList.equals(null)){
            return CommonResult.failed();
        }
        return CommonResult.success(productList);
    }

    @ResponseBody
    @GetMapping("/getAllHot")
    public CommonResult<List<PmsProduct>> getAllHotProduct(){
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("new_status", 1);
        List<PmsProduct> productList = iPmsProductService.list(queryWrapper);
        if(productList.equals(null)){
            return CommonResult.failed();
        }
        return CommonResult.success(productList);
    }
}
