package shop.tigeryam.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import shop.tigeryam.admin.dto.PmsProductAttributeParam;
import shop.tigeryam.admin.dto.ProductAttrInfo;
import shop.tigeryam.admin.service.IPmsProductAttributeMallService;
import shop.tigeryam.api.CommonPage;
import shop.tigeryam.api.CommonResult;
import shop.tigeryam.entity.PmsProductAttributeMall;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
@Controller
@RequestMapping("/productAttribute")
public class PmsProductAttributeMallController {
    @Autowired
    private IPmsProductAttributeMallService productAttributeService;

    // 根据分类查询属性列表或参数列表
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttributeMall>> getList(@PathVariable Long cid,
                                                                     @RequestParam(value = "type") Integer type,
                                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<PmsProductAttributeMall> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productAttributeList));
    }

    // 根据商品分类的id获取商品属性及属性分类
    @RequestMapping(value = "/attrInfo/{productCategoryId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<ProductAttrInfo>> getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return CommonResult.success(productAttrInfoList);
    }

    // 批量删除商品属性
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = productAttributeService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    // 添加商品属性信息
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.create(productAttributeParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    // 查询单个商品属性
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProductAttributeMall> getItem(@PathVariable Long id) {
        PmsProductAttributeMall productAttribute = productAttributeService.getItem(id);
        return CommonResult.success(productAttribute);
    }

    // 修改商品属性信息
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.update(id, productAttributeParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
