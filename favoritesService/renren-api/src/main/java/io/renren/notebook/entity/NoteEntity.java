package io.renren.notebook.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 笔记本-笔记
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-21 21:42:16
 */
@Data
@TableName("t_note")
public class NoteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 父节点ID
	 */
	private Long parentId;
	/**
	 * 笔记名称
	 */
	private String name;
	/**
	 * 节点类型
	 */
	private Integer type;
	/**
	 * 查询次数
	 */
	private Integer queryCount;
	/**
	 * 熟悉程度
	 */
	private Integer degree;
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
