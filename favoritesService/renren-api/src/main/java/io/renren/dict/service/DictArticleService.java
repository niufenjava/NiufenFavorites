package io.renren.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.dict.entity.DictArticleEntity;

import java.util.Map;

/**
 * 词典-章节-文章
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
public interface DictArticleService extends IService<DictArticleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

