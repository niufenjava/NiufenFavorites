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

import io.renren.dict.entity.DictArticleEntity;
import io.renren.dict.service.DictArticleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 词典-章节-文章
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@RestController
@RequestMapping("dict/dictarticle")
public class DictArticleController {
    @Autowired
    private DictArticleService dictArticleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dictArticleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DictArticleEntity dictArticle = dictArticleService.getById(id);

        return R.ok().put("dictArticle", dictArticle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DictArticleEntity dictArticle){
        dictArticleService.save(dictArticle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DictArticleEntity dictArticle){
        ValidatorUtils.validateEntity(dictArticle);
        dictArticleService.updateById(dictArticle);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dictArticleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
