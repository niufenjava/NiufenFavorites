package io.renren.dict.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 词典-单词释义表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Data
@TableName("t_dict_word_def")
public class DictWordDefEntity implements Serializable {
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
	 * 中文释义
	 */
	private String name;
	/**
	 * 释义单词类型
	 */
	private Integer type;
	/**
	 * 英语解释
	 */
	private String explainEn;
	/**
	 * 中文解释
	 */
	private String explainCh;
	/**
	 * 描述/备注
	 */
	private String descp;
	/**
	 * 顺序号
	 */
	private Integer sn;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改时间
	 */
	private Date modifyTime;

}
