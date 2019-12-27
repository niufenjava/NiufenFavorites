package io.renren.notebook.form;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author haijun.zhang
 * @date 2019/12/21
 * @time 22:56
 */
@Data
public class NoteSortForm implements Serializable {

    private static final long serialVersionUID = 9096548796645550497L;

    private Long parentId;

    private LinkedList<Long> childrenIds;

}