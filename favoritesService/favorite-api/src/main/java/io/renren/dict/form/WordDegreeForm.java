package io.renren.dict.form;

import lombok.Data;

import java.io.Serializable;

/**
 * 词典-单词释义例子表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Data
public class WordDegreeForm implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	private Long id;
	/**
	 * 顺序号
	 */
	private Integer degree;

}
