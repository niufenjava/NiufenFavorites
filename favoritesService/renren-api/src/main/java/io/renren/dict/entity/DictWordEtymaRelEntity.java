package io.renren.dict.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典-单词词源关联表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Data
@TableName("t_dict_word_etyma_rel")
public class DictWordEtymaRelEntity implements Serializable {
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
	 * 词源表ID
	 */
	private Long etymaId;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
