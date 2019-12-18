package io.renren.dict.dao;

import io.renren.dict.entity.DictBookEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 词典-书
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Mapper
public interface DictBookDao extends BaseMapper<DictBookEntity> {
	
}
