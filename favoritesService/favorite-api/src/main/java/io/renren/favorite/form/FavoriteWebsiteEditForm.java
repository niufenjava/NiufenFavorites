package io.renren.favorite.form;

import lombok.Data;

import java.io.Serializable;

/**
 * 收藏夹-编辑
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-26 21:29:49
 */
@Data
public class FavoriteWebsiteEditForm implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
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
	 * 站点图标地址
	 */
	private String iconUrl;
	private Boolean favorite;
	/**
	 * 站点描述
	 */
	private String descp;

}
