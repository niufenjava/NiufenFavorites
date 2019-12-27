package io.renren.favorite.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏夹-站点
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-26 21:29:49
 */
@Data
@TableName("t_favorite_website")
public class FavoriteWebsiteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 搜藏夹类型ID
	 */
	private Long typeId;
	/**
	 * 站点名称
	 */
	private String name;
	/**
	 * 站点地址
	 */
	private String url;
	/**
	 * 站点地址
	 */
	private String iconUrl;
	/**
	 * 喜爱程度
	 */
	private Integer degree;
	/**
	 * 站点描述
	 */
	private String descp;
	/**
	 * 点击次数
	 */
	private Integer count;
	/**
	 * 是否收藏
	 */
	private Integer favorite;
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
