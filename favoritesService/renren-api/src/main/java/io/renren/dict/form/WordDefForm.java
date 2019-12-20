package io.renren.dict.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 词典-单词释义表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Data
public class WordDefForm implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	private Long id;
	/**
	 * 中文释义
	 */
	private String name;
	/**
	 * 释义单词类型
	 */
	private Integer type;
	/**
	 * 释义单词类型描述
	 */
	private String typeDesc;
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

	private List<WordDefExampleForm> exampleList;

}
