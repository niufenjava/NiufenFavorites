package io.renren.favorite.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏夹-类型
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-26 21:29:49
 */
@Data
@TableName("t_favorite_type")
public class FavoriteTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 收藏夹类型名称
	 */
	private String name;
	/**
	 * 收藏夹图标名称
	 */
	private String icon;
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
