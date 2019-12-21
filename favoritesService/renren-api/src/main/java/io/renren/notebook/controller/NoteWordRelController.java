package io.renren.notebook.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.notebook.entity.NoteWordRelEntity;
import io.renren.notebook.service.NoteWordRelService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 笔记本-笔记内容-单词关联表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-21 21:42:16
 */
@RestController
@RequestMapping("notebook/notewordrel")
public class NoteWordRelController {
    @Autowired
    private NoteWordRelService noteWordRelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = noteWordRelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        NoteWordRelEntity noteWordRel = noteWordRelService.getById(id);

        return R.ok().put("noteWordRel", noteWordRel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NoteWordRelEntity noteWordRel){
        noteWordRelService.save(noteWordRel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody NoteWordRelEntity noteWordRel){
        ValidatorUtils.validateEntity(noteWordRel);
        noteWordRelService.updateById(noteWordRel);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        noteWordRelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
