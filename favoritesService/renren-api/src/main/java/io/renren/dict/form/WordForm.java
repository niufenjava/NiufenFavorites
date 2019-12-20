package io.renren.dict.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


/**
 * 创建或更新单词表单
 * @author niufen
 */
@Data
public class WordForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;
    /**
     * 单词名称
     */
    @NotBlank(message="单词不能为空")
    private String name;
    /**
     * 音标
     */
    private String soundmark;
    /**
     * 熟悉程度，详见枚举
     */
    private Integer degree;
    /**
     * 记忆技巧描述
     */
    private String skillDesc;
    /**
     * 备注/描述
     */
    private String descp;

    /**
     * 关联词源ID列表
     */
    private List<Long> etymaIdList;

    /**
     * 释义List
     */
    private List<WordDefForm> defList;
}
