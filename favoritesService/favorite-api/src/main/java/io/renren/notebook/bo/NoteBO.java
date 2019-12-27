package io.renren.notebook.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author haijun.zhang
 * @date 2019/12/21
 * @time 22:56
 */
@Data
public class NoteBO implements Serializable ,Comparable<NoteBO>{

    private static final long serialVersionUID = 9096548796645550497L;

    private Long id;

    private Long patientId;

    private String label;

    private Integer sn;

    private List<NoteBO> children;

    @Override
    public int compareTo(NoteBO o) {
        return this.sn - o.sn;
    }
}