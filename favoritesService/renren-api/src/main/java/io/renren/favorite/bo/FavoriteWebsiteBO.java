package io.renren.favorite.bo;

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
public class FavoriteWebsiteBO implements Serializable {
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
	 * 站点地址
	 */
	private String iconUrl;
	/**
	 * 喜爱程度
	 */
	private Boolean favorite;
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
	 * 顺序号
	 */
	private Integer sn;

}
