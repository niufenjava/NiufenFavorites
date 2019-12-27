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
public class WordListQueryForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排序类型
     */
    private Integer sortType;
}
