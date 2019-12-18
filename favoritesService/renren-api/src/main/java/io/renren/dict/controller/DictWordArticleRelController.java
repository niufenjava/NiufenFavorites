package io.renren.dict.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.dict.entity.DictWordArticleRelEntity;
import io.renren.dict.service.DictWordArticleRelService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 字典-单词-文章关联表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@RestController
@RequestMapping("dict/dictwordarticlerel")
public class DictWordArticleRelController {
    @Autowired
    private DictWordArticleRelService dictWordArticleRelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dictWordArticleRelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DictWordArticleRelEntity dictWordArticleRel = dictWordArticleRelService.getById(id);

        return R.ok().put("dictWordArticleRel", dictWordArticleRel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DictWordArticleRelEntity dictWordArticleRel){
        dictWordArticleRelService.save(dictWordArticleRel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DictWordArticleRelEntity dictWordArticleRel){
        ValidatorUtils.validateEntity(dictWordArticleRel);
        dictWordArticleRelService.updateById(dictWordArticleRel);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dictWordArticleRelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
