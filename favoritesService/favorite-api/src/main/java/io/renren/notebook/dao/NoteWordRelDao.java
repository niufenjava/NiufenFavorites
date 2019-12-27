package io.renren.notebook.dao;

import io.renren.notebook.entity.NoteWordRelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 笔记本-笔记内容-单词关联表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-21 21:42:16
 */
@Mapper
public interface NoteWordRelDao extends BaseMapper<NoteWordRelEntity> {
	
}
