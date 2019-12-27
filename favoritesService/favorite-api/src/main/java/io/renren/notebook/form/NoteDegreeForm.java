package io.renren.notebook.form;

import lombok.Data;

import java.io.Serializable;

/**
 * 笔记本-笔记-标记
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Data
public class NoteDegreeForm implements Serializable {
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
