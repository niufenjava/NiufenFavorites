package io.renren.dict.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 词典-单词表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Data
@TableName("t_dict_word")
public class DictWordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 单词名称
	 */
	private String name;
	/**
	 * 音标
	 */
	private String soundmark;
	/**
	 * 熟悉程度，详见枚举
	 */
	private Integer degree;
	/**
	 * 记忆技巧描述
	 */
	private String skillDesc;
	/**
	 * 单词描述/备注
	 */
	private String descp;
	/**
	 * 查询次数
	 */
	private Integer queryCount;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改时间
	 */
	private Date modifyTime;

}
