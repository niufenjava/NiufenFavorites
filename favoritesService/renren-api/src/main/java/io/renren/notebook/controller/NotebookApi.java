package io.renren.notebook.controller;

import io.renren.common.utils.R;
import io.renren.notebook.form.NoteCreateForm;
import io.renren.notebook.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 笔记本-笔记
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-21 21:42:16
 */
@RestController
@RequestMapping("notebook")
public class NotebookApi {
    @Autowired
    private NotebookService notebookService;

    /**
     * 保存
     */
    @RequestMapping("/note/create")
    public R noteCreate(@RequestBody NoteCreateForm noteCreateForm){

        return R.ok().put("id",notebookService.noteCreate(noteCreateForm));
    }

    /**
     * 保存
     */
    @RequestMapping("/note/tree")
    public R noteTree(){
        return R.ok().put("tree",notebookService.noteTree());
    }

}
