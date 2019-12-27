package io.renren.notebook.controller;

import io.renren.common.utils.R;
import io.renren.dict.form.WordDegreeForm;
import io.renren.notebook.form.*;
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

    @RequestMapping("/note/sort")
    public R noteSort(@RequestBody NoteSortForm sortForm){
        notebookService.noteSort(sortForm);
        return R.ok();
    }

    @RequestMapping("/note/rel/word/list/{id}")
    public R noteRelWordList(@PathVariable("id") Long id ){
        return R.ok().put("wordList",notebookService.listRelWord(id));
    }

    @RequestMapping("/note/detail/{id}")
    public R noteDetail(@PathVariable("id") Long id ){
        return R.ok().put("note",notebookService.noteDetail(id));
    }

    @RequestMapping("/note/rel/word")
    public R noteRelWord(@RequestBody NoteRelWordForm relWordForm){
        notebookService.noteRelWord(relWordForm);
        return R.ok();
    }

    @RequestMapping("/note/rel/word/delete")
    public R noteRelWordDel(@RequestBody NoteRelWordForm relWordForm){
        notebookService.noteRelWordDel(relWordForm);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/note/create")
    public R noteCreate(@RequestBody NoteCreateForm noteCreateForm){

        return R.ok().put("id",notebookService.noteCreate(noteCreateForm));
    }

    @RequestMapping("/note/rename")
    public R noteRename(@RequestBody NoteReNameForm reNameBO){
        notebookService.noteRename(reNameBO);
        return R.ok();
    }

    @RequestMapping("/note/content")
    public R noteContent(@RequestBody NoteContentForm noteContentForm){
        notebookService.noteContentModify(noteContentForm);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/note/delete")
    public R noteDelete(@RequestBody NoteDeleteForm deleteForm){

        notebookService.noteDelete(deleteForm);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/note/tree")
    public R noteTree(){
        return R.ok().put("tree",notebookService.noteTree());
    }

    @RequestMapping("/note/degree")
    public R wordDegree(@RequestBody NoteDegreeForm degreeForm){
        notebookService.noteDegree(degreeForm);
        return R.ok();
    }

}
