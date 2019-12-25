package io.renren.dict.controller;

import io.renren.common.utils.R;
import io.renren.dict.entity.DictArticleEntity;
import io.renren.dict.form.WordDegreeForm;
import io.renren.dict.form.WordForm;
import io.renren.dict.form.WordListQueryForm;
import io.renren.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 词典-单词表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@RestController
@RequestMapping("dict/")
public class DictApi {

    @Autowired
    private DictService dictService;

    /**
     * 保存
     */
    @RequestMapping("/word/createOrUpdate")
    public R wordCreateOrUpdate(@RequestBody WordForm wordForm){
        dictService.wordCreateOrUpdate(wordForm);
        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/word/info/{word}")
    public R wordInfo(@PathVariable("word") String word){
        return R.ok().put("word", dictService.getWordForm(word));
    }

    /**
     * 信息
     */
    @RequestMapping("/word/list")
    public R wordList(@RequestBody  WordListQueryForm queryForm){
        return R.ok().put("wordList", dictService.listWordName(queryForm));
    }

    @RequestMapping("/word/degree")
    public R wordDegree(@RequestBody WordDegreeForm degreeForm){
        dictService.wordDegree(degreeForm);
        return R.ok();
    }

}
