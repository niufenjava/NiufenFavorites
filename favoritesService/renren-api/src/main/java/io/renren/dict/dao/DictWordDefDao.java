package io.renren.dict.dao;

import io.renren.dict.entity.DictWordDefEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 词典-单词释义表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Mapper
public interface DictWordDefDao extends BaseMapper<DictWordDefEntity> {
	
}
