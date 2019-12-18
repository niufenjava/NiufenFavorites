package io.renren.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.dict.entity.DictChapterEntity;

import java.util.Map;

/**
 * 词典-书-章节
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
public interface DictChapterService extends IService<DictChapterEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

