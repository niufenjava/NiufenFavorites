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
	 * 复数
	 */
	private String plural;
	/**
	 * 第三人称单数
	 */
	private String thirdSingular;
	/**
	 * 现在分词
	 */
	private String presentParticiple;
	/**
	 * 过去分词
	 */
	private String pastParticiple;
	/**
	 * 过去式
	 */
	private String pastTense;
	/**
	 * 其他
	 */
	private String other;
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
