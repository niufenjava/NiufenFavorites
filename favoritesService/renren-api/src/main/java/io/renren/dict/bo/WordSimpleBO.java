package io.renren.dict.bo;

import io.renren.dict.form.WordDefForm;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


/**
 * 单词简单
 * @author niufen
 */
@Data
public class WordSimpleBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;
    /**
     * 单词名称
     */
    private String name;
    /**
     * 单词名称
     */
    private String descp;
}
