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

import io.renren.notebook.entity.NoteEntity;
import io.renren.notebook.service.NoteService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 笔记本-笔记
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-21 21:42:16
 */
@RestController
@RequestMapping("notebook/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = noteService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        NoteEntity note = noteService.getById(id);

        return R.ok().put("note", note);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NoteEntity note){
        noteService.save(note);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody NoteEntity note){
        ValidatorUtils.validateEntity(note);
        noteService.updateById(note);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        noteService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
