package io.renren.dict.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 词典-章节-文章
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Data
@TableName("t_dict_article")
public class DictArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 章节ID
	 */
	private Long chapterId;
	/**
	 * 文章名-英文
	 */
	private String nameEn;
	/**
	 * 文章名-中文
	 */
	private String nameCh;
	/**
	 * 文章内容-英文
	 */
	private String contentEn;
	/**
	 * 文章内容-中文
	 */
	private String contentCh;
	/**
	 * 描述/备注
	 */
	private String descp;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改时间
	 */
	private Date modifyTime;

}
