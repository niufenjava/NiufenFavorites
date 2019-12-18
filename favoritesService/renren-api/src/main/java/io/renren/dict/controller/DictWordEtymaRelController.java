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

import io.renren.dict.entity.DictWordEtymaRelEntity;
import io.renren.dict.service.DictWordEtymaRelService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 字典-单词词源关联表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@RestController
@RequestMapping("dict/dictwordetymarel")
public class DictWordEtymaRelController {
    @Autowired
    private DictWordEtymaRelService dictWordEtymaRelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dictWordEtymaRelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DictWordEtymaRelEntity dictWordEtymaRel = dictWordEtymaRelService.getById(id);

        return R.ok().put("dictWordEtymaRel", dictWordEtymaRel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DictWordEtymaRelEntity dictWordEtymaRel){
        dictWordEtymaRelService.save(dictWordEtymaRel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DictWordEtymaRelEntity dictWordEtymaRel){
        ValidatorUtils.validateEntity(dictWordEtymaRel);
        dictWordEtymaRelService.updateById(dictWordEtymaRel);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dictWordEtymaRelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
