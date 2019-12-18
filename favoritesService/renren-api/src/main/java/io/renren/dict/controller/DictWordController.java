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

import io.renren.dict.entity.DictWordEntity;
import io.renren.dict.service.DictWordService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 词典-单词表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@RestController
@RequestMapping("dict/dictword")
public class DictWordController {
    @Autowired
    private DictWordService dictWordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dictWordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DictWordEntity dictWord = dictWordService.getById(id);

        return R.ok().put("dictWord", dictWord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DictWordEntity dictWord){
        dictWordService.save(dictWord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DictWordEntity dictWord){
        ValidatorUtils.validateEntity(dictWord);
        dictWordService.updateById(dictWord);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dictWordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
