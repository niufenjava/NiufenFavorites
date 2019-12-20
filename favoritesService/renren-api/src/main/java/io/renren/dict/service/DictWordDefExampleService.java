package io.renren.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.dict.entity.DictWordDefExampleEntity;

import java.util.List;
import java.util.Map;

/**
 * 词典-单词释义例子表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
public interface DictWordDefExampleService extends IService<DictWordDefExampleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void delByDefIds(List<Long> defIds);

    List<DictWordDefExampleEntity> listByDefId(Long defId);
}

