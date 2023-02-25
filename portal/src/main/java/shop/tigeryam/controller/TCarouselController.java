package shop.tigeryam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.tigeryam.api.CommonResult;
import shop.tigeryam.entity.TCarousel;
import shop.tigeryam.service.ITCarouselService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tigeryam
 * @since 2022-12-05
 */
@Controller
@RequestMapping("/t-carousel")
public class TCarouselController {
    @Autowired
    private ITCarouselService itCarouselService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TCarousel>> list(){
        List<TCarousel> list = itCarouselService.list();
        if(list.size() == 0){
            return CommonResult.failed();
        }else {
            return CommonResult.success(list);
        }
    }

}
