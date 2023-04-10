package shop.tigeryam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import shop.tigeryam.api.CommonResult;
import shop.tigeryam.dto.CategorySubLevelParam;
import shop.tigeryam.entity.PmsProductCategory;
import shop.tigeryam.service.IPmsProductCategoryService;

import java.util.List;

/**
 * <p>
 *  产品分类Controller
 * </p>
 *
 * @author tigeryam
 * @since 2022-12-10
 */
@Controller
@RequestMapping(value = "/pms-product-category" ,method = RequestMethod.GET)
public class PmsProductCategoryController {
    @Autowired
    private IPmsProductCategoryService pmsProductCategoryService;

    @ResponseBody
    @RequestMapping(value = "/fatherLevel")
    public CommonResult<List<PmsProductCategory>> getFatherLevel(){
        List<PmsProductCategory> fatherLevel = pmsProductCategoryService.getFatherLevel();
        if(fatherLevel.size() != 0){
            return CommonResult.success(fatherLevel);
        }
        return CommonResult.failed("获取数据异常！");
    }

    @ResponseBody
    @RequestMapping(value = "/subLevel", method = RequestMethod.POST )
    public CommonResult<List<PmsProductCategory>> getSubLever(@RequestBody CategorySubLevelParam categorySubLevelParam){
        List<PmsProductCategory> subLevel = pmsProductCategoryService.getSubLevel(categorySubLevelParam.getParentId());
        if(subLevel.size() > 0 ){
            return CommonResult.success(subLevel);
        }
        return CommonResult.failed();

    }

    @ResponseBody
    @RequestMapping(value = "/getCategory")
    public CommonResult<List<PmsProductCategory>> getCategory(@RequestParam Integer parentId){
        List<PmsProductCategory> categoryTree = pmsProductCategoryService.getCategoryTree(parentId);
        return CommonResult.success(categoryTree);
    }

}
