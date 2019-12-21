package io.renren.notebook.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.notebook.entity.NoteWordRelEntity;

import java.util.Map;

/**
 * 笔记本-笔记内容-单词关联表
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-21 21:42:16
 */
public interface NoteWordRelService extends IService<NoteWordRelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

