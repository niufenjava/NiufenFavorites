package io.renren.dict.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 词典-单词释义例子表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Data
@TableName("t_dict_word_def_example")
public class DictWordDefExampleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 释义表ID
	 */
	private Long defId;
	/**
	 * 英语解释
	 */
	private String exampleEn;
	/**
	 * 中文解释
	 */
	private String exampleCh;
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
