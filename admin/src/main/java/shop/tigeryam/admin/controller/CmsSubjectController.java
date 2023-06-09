package shop.tigeryam.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.tigeryam.admin.service.ICmsSubjectService;
import shop.tigeryam.api.CommonPage;
import shop.tigeryam.api.CommonResult;
import shop.tigeryam.entity.CmsSubject;

import java.util.List;

/**
 * <p>
 * 专题表 前端控制器
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-30
 */
@Controller
@RequestMapping("/subject")
public class CmsSubjectController {
    @Autowired
    private ICmsSubjectService subjectService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CmsSubject>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<CmsSubject> subjectList = subjectService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(subjectList));
    }


    // 获取全部商品专题
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubject>> listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return CommonResult.success(subjectList);
    }

}
