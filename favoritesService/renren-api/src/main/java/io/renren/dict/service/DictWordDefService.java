package io.renren.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.dict.entity.DictWordDefEntity;

import java.util.List;
import java.util.Map;

/**
 * 词典-单词释义表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
public interface DictWordDefService extends IService<DictWordDefEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void delByWordId(Long wordId);

    List<DictWordDefEntity> listByWordId(Long wordId);

    List<Long> listIdsByWordId(Long wordId);
}

