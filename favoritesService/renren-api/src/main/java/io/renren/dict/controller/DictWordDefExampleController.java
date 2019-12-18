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

import io.renren.dict.entity.DictWordDefExampleEntity;
import io.renren.dict.service.DictWordDefExampleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 词典-单词释义例子表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@RestController
@RequestMapping("dict/dictworddefexample")
public class DictWordDefExampleController {
    @Autowired
    private DictWordDefExampleService dictWordDefExampleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dictWordDefExampleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DictWordDefExampleEntity dictWordDefExample = dictWordDefExampleService.getById(id);

        return R.ok().put("dictWordDefExample", dictWordDefExample);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DictWordDefExampleEntity dictWordDefExample){
        dictWordDefExampleService.save(dictWordDefExample);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DictWordDefExampleEntity dictWordDefExample){
        ValidatorUtils.validateEntity(dictWordDefExample);
        dictWordDefExampleService.updateById(dictWordDefExample);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dictWordDefExampleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
