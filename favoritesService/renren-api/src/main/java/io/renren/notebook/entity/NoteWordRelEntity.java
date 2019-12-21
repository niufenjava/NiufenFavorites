package io.renren.notebook.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 笔记本-笔记内容-单词关联表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-21 21:42:16
 */
@Data
@TableName("t_note_word_rel")
public class NoteWordRelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 笔记节点ID
	 */
	private Long noteId;
	/**
	 * 笔记内容
	 */
	private String wordId;
	/**
	 * 笔记内容类型
	 */
	private Integer type;
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
