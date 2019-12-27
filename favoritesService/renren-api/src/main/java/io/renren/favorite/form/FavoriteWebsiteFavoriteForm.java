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
public class FavoriteWebsiteFavoriteForm implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	private Long id;
	/**
	 * 是否喜好
	 */
	private Boolean favorite;

}
