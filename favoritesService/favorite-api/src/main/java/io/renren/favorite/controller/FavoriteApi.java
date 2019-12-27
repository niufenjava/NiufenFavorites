package io.renren.favorite.controller;

import io.renren.common.utils.R;
import io.renren.dict.form.WordDegreeForm;
import io.renren.dict.form.WordForm;
import io.renren.dict.form.WordListQueryForm;
import io.renren.dict.service.DictService;
import io.renren.favorite.form.FavoriteTypeEditForm;
import io.renren.favorite.form.FavoriteWebsiteEditForm;
import io.renren.favorite.form.FavoriteWebsiteFavoriteForm;
import io.renren.favorite.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 词典-单词表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@RestController
@RequestMapping("favorite/")
public class FavoriteApi {

    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping("website/info/{id}")
    public R websiteInfo(@PathVariable("id") Long id){
        return R.ok().put("websiteForm",favoriteService.websiteInfo(id));
    }

    /**
     * 点击
     */
    @RequestMapping("website/click/{id}")
    public R websiteClick(@PathVariable("id") Long id){
        favoriteService.click(id);
        return R.ok();
    }

    @RequestMapping("type/all/list")
    public R typeAllList(){
        return R.ok().put("typeList",favoriteService.allTypeList());
    }

    @RequestMapping("type/list")
    public R typeList(){
        return R.ok().put("typeList",favoriteService.typeList());
    }

    @RequestMapping("website/favorite")
    public R websiteFavorite(@RequestBody FavoriteWebsiteFavoriteForm favoriteForm){
        favoriteService.websiteFavorite(favoriteForm);
        return R.ok();
    }

    @RequestMapping("website/list/{id}")
    public R websiteList(@PathVariable("id") Long id){
        return R.ok().put("websiteList",favoriteService.websiteList(id));
    }

    @RequestMapping("type/edit")
    public R typeEdit(@RequestBody FavoriteTypeEditForm editForm){
        favoriteService.typeEdit(editForm);
        return R.ok();
    }



    @RequestMapping("website/edit")
    public R websiteEdit(@RequestBody FavoriteWebsiteEditForm editForm){
        favoriteService.websiteEdit(editForm);
        return R.ok();
    }

}
