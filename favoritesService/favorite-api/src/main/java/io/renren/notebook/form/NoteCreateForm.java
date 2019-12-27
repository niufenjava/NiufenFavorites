/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.notebook.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建笔记-表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class NoteCreateForm {
    @NotBlank(message="笔记名称不能为空")
    private String name;

    @NotBlank(message="父节点ID不能为空")
    private Long patientId;

}
