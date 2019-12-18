package io.renren.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.dict.entity.DictBookEntity;

import java.util.Map;

/**
 * 词典-书
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
public interface DictBookService extends IService<DictBookEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

