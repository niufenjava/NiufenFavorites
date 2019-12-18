package io.renren.dict.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典-单词-文章关联表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Data
@TableName("t_dict_word_article_rel")
public class DictWordArticleRelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 单词表ID
	 */
	private Long wordId;
	/**
	 * 文章ID
	 */
	private Long articleId;
	/**
	 * 顺序号
	 */
	private Integer sn;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
