package shop.tigeryam.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import shop.tigeryam.admin.service.IPmsSkuStockService;
import shop.tigeryam.api.CommonResult;
import shop.tigeryam.entity.PmsSkuStock;

import java.util.List;

/**
 * <p>
 * sku的库存 前端控制器
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
@Controller
@RequestMapping("sku")
public class PmsSkuStockController {

    @Autowired
    private IPmsSkuStockService skuStockService;

    // 根据商品ID及sku编码模糊搜索sku库存
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsSkuStock>> getList(@PathVariable Long pid, @RequestParam(value = "keyword",required = false) String keyword) {
        List<PmsSkuStock> skuStockList = skuStockService.getList(pid, keyword);
        return CommonResult.success(skuStockList);
    }

    // 批量更新sku库存信息
    @RequestMapping(value ="/update/{pid}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long pid,@RequestBody List<PmsSkuStock> skuStockList){
        int count = skuStockService.update(pid,skuStockList);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
}
