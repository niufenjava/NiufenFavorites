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

import io.renren.notebook.entity.NoteContentEntity;
import io.renren.notebook.service.NoteContentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 笔记本-笔记内容
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-21 21:42:16
 */
@RestController
@RequestMapping("notebook/notecontent")
public class NoteContentController {
    @Autowired
    private NoteContentService noteContentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = noteContentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        NoteContentEntity noteContent = noteContentService.getById(id);

        return R.ok().put("noteContent", noteContent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NoteContentEntity noteContent){
        noteContentService.save(noteContent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody NoteContentEntity noteContent){
        ValidatorUtils.validateEntity(noteContent);
        noteContentService.updateById(noteContent);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        noteContentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
