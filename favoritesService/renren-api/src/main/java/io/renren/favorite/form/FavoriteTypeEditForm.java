package io.renren.favorite.form;

import lombok.Data;

import java.io.Serializable;


/**
 * 创建或更新单词表单
 * @author niufen
 */
@Data
public class FavoriteTypeEditForm implements Serializable {

    private static final long serialVersionUID = 1L;

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
}
