package io.renren.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.dict.entity.DictWordEtymaRelEntity;

import java.util.Map;

/**
 * 字典-单词词源关联表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
public interface DictWordEtymaRelService extends IService<DictWordEtymaRelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

