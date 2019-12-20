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
public class WordDefExampleForm implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	private Long id;
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

}
