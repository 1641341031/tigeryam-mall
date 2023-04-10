package shop.tigeryam.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import shop.tigeryam.admin.dao.*;
import shop.tigeryam.admin.dto.PmsProductParam;
import shop.tigeryam.admin.dto.PmsProductQueryParam;
import shop.tigeryam.admin.service.IPmsProductMallService;
import shop.tigeryam.entity.*;
import shop.tigeryam.mapper.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author tigeryam
 * @since 2023-03-27
 */
@Service
@Slf4j
public class PmsProductMallServiceImpl extends ServiceImpl<PmsProductMallMapper, PmsProductMall> implements IPmsProductMallService {
    @Autowired
    private PmsProductMallMapper pmsProductMapper;
    @Autowired
    private PmsMemberPriceMapper memberPriceMapper;
    @Autowired
    private PmsMemberPriceDao memberPriceDao;
    @Autowired
    private PmsProductLadderDao productLadderDao;
    @Autowired
    private PmsProductLadderMapper productLadderMapper;
    @Autowired
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private PmsProductFullReductionDao productFullReductionDao;
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsSkuStockDao skuStockDao;
    @Autowired
    private PmsProductAttributeMallMapper productAttributeValueMapper;
    @Autowired
    private PmsProductAttributeValueDao productAttributeValueDao;
    @Autowired
    private CmsSubjectProductRelationMapper subjectProductRelationMapper;
    @Autowired
    private CmsSubjectProductRelationDao subjectProductRelationDao;
    @Autowired
    private CmsPrefrenceAreaProductRelationMapper prefrenceAreaProductRelationMapper;
    @Autowired
    private CmsPrefrenceAreaProductRelationDao prefrenceAreaProductRelationDao;

    @Override
    public int create(PmsProductParam productParam) {
        int count;
        //创建商品
        PmsProductMall product = productParam;
        product.setId(null);
        pmsProductMapper.insert(product);
        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();
        //会员价格
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), productId);
        //阶梯价格
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), productId);
        //满减价格
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), productId);
        //处理sku的编码
        handleSkuStockCode(productParam.getSkuStockList(),productId);
        //添加sku库存信息
        relateAndInsertList(skuStockDao, productParam.getSkuStockList(), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), productId);
        //关联专题
        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), productId);
        //关联优选
        relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), productId);
        count = 1;
        return count;
    }

    @Override
    public Page<PmsProductMall> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        Page<PmsProductMall> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsProductMall> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_status", 0);
        if (productQueryParam.getPublishStatus() != null) {
            queryWrapper.eq("publish_status", productQueryParam.getPublishStatus());
        }
        if (productQueryParam.getVerifyStatus() != null) {
            queryWrapper.eq("verify_status", productQueryParam.getVerifyStatus());
        }
        if (!StrUtil.isEmpty(productQueryParam.getKeyword())) {
            queryWrapper.like("name", productQueryParam.getKeyword());
        }
        if (!StrUtil.isEmpty(productQueryParam.getProductSn())) {
            queryWrapper.eq("product_sn", productQueryParam.getProductSn());
        }
        if (productQueryParam.getBrandId() != null) {
            queryWrapper.eq("brand_id", productQueryParam.getBrandId());
        }
        if (productQueryParam.getProductCategoryId() != null) {
            queryWrapper.eq("product_category_id", productQueryParam.getProductCategoryId());
        }
        return pmsProductMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        PmsProductMall productMall = new PmsProductMall();
        productMall.setPublishStatus(publishStatus);
        QueryWrapper<PmsProductMall> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return pmsProductMapper.update(productMall, queryWrapper);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProductMall record = new PmsProductMall();
        record.setNewStatus(newStatus);
        QueryWrapper<PmsProductMall> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return pmsProductMapper.update(record, queryWrapper);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        PmsProductMall record = new PmsProductMall();
        record.setRecommandStatus(recommendStatus);
        QueryWrapper<PmsProductMall> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return pmsProductMapper.update(record, queryWrapper);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        PmsProductMall record = new PmsProductMall();
        record.setDeleteStatus(deleteStatus);
        QueryWrapper<PmsProductMall> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return pmsProductMapper.update(record, queryWrapper);
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        int count;
        //更新商品信息
        PmsProductMall product = productParam;
        product.setId(id);
        pmsProductMapper.updateById(product);
        //会员价格
        QueryWrapper<PmsMemberPrice> priceQueryWrapper = new QueryWrapper<>();
        priceQueryWrapper.eq("product_id", id);
        memberPriceMapper.delete(priceQueryWrapper);
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), id);
        //阶梯价格
        QueryWrapper<PmsProductLadder> productLadderWrapper = new QueryWrapper<>();
        productLadderWrapper.eq("product_id", id);
        productLadderMapper.delete(productLadderWrapper);
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), id);
        //满减价格
        QueryWrapper<PmsProductFullReduction> fullReductionWrapper = new QueryWrapper<>();
        fullReductionWrapper.eq("product_id", id);
        productFullReductionMapper.delete(fullReductionWrapper);
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), id);
        //修改sku库存信息
        handleUpdateSkuStockList(id, productParam);
        //修改商品参数,添加自定义商品规格
        QueryWrapper<PmsProductAttributeMall> attributeMallQueryWrapper= new QueryWrapper<>();
        attributeMallQueryWrapper.eq("product_id", id);
        productAttributeValueMapper.delete(attributeMallQueryWrapper);
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), id);
        //关联专题
        QueryWrapper<CmsSubjectProductRelation> subjectProductRelationQueryWrapper = new QueryWrapper<>();
        subjectProductRelationQueryWrapper.eq("product_id", id);
        subjectProductRelationMapper.delete(subjectProductRelationQueryWrapper);
        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), id);
        //关联优选
        QueryWrapper<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationWrapper = new QueryWrapper<>();
        prefrenceAreaProductRelationWrapper.eq("product_id",id);
        prefrenceAreaProductRelationMapper.delete(prefrenceAreaProductRelationWrapper);
        relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), id);
        count = 1;
        return count;
    }

    private void handleUpdateSkuStockList(Long id, PmsProductParam productParam) {
        //当前的sku信息
        List<PmsSkuStock> currSkuList = productParam.getSkuStockList();
        //当前没有sku直接删除
        if(CollUtil.isEmpty(currSkuList)){
            QueryWrapper<PmsSkuStock> stockQueryWrapper = new QueryWrapper<>();
            stockQueryWrapper.eq("product_id",id);
            skuStockMapper.delete(stockQueryWrapper);
            return;
        }
        //获取初始sku信息
        QueryWrapper<PmsSkuStock> stockQueryWrapper1 = new QueryWrapper<>();
        stockQueryWrapper1.eq("product_id",id);
        List<PmsSkuStock> oriStuList = skuStockMapper.selectList(stockQueryWrapper1);
        //获取新增sku信息
        List<PmsSkuStock> insertSkuList = currSkuList.stream().filter(item->item.getId()==null).collect(Collectors.toList());
        //获取需要更新的sku信息
        List<PmsSkuStock> updateSkuList = currSkuList.stream().filter(item->item.getId()!=null).collect(Collectors.toList());
        List<Long> updateSkuIds = updateSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
        //获取需要删除的sku信息
        List<PmsSkuStock> removeSkuList = oriStuList.stream().filter(item-> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());
        handleSkuStockCode(insertSkuList,id);
        handleSkuStockCode(updateSkuList,id);
        //新增sku
        if(CollUtil.isNotEmpty(insertSkuList)){
            relateAndInsertList(skuStockDao, insertSkuList, id);
        }
        //删除sku
        if(CollUtil.isNotEmpty(removeSkuList)){
            List<Long> removeSkuIds = removeSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
            QueryWrapper<PmsSkuStock> stockQueryWrapper2 = new QueryWrapper<>();
            stockQueryWrapper2.in("id", removeSkuIds);
            skuStockMapper.delete(stockQueryWrapper2);
        }
        //修改sku
        if(CollUtil.isNotEmpty(updateSkuList)){
            for (PmsSkuStock pmsSkuStock : updateSkuList) {
                skuStockMapper.updateById(pmsSkuStock);
            }
        }

    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        if(CollectionUtils.isEmpty(skuStockList))return;
        for(int i=0;i<skuStockList.size();i++){
            PmsSkuStock skuStock = skuStockList.get(i);
            if(StrUtil.isEmpty(skuStock.getSkuCode())){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i+1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    /**
     * 建立和插入关系表操作
     *
     * @param dao       可以操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            log.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
