package io.renren.notebook.service;
import java.util.Date;
import java.util.List;

import io.renren.common.exception.RRException;
import io.renren.common.utils.ListUtils;
import io.renren.common.utils.ObjectTools;
import io.renren.notebook.bo.NoteBO;
import io.renren.notebook.entity.NoteContentEntity;
import io.renren.notebook.entity.NoteEntity;
import io.renren.notebook.form.NoteCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author haijun.zhang
 * @date 2019/12/21
 * @time 21:45
 */
@Service
public class NotebookService {

    @Autowired
    private NoteService noteService;
    @Autowired
    private NoteContentService noteContentService;

    @Transactional(rollbackFor = Exception.class)
    public Long noteCreate(NoteCreateForm noteCreateForm){
        NoteEntity patientNote = noteService.getById(noteCreateForm.getPatientId());
        if(ObjectTools.isNull(patientNote)){
            throw new RRException("父节点不存在！");
        }
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setParentId(patientNote.getId());
        noteEntity.setName(noteCreateForm.getName());
        noteEntity.setType(0);
        noteEntity.setDescp("");
        noteService.save(noteEntity);

        NoteContentEntity contentEntity = new NoteContentEntity();
        contentEntity.setNoteId(noteEntity.getId());
        contentEntity.setContent("");
        contentEntity.setType(0);
        contentEntity.setDescp("");
        noteContentService.save(contentEntity);

        return noteEntity.getId();
    }

    public List<NoteBO> noteTree(){
        Long rootId = 1L;
        NoteEntity rootNoteEntity = noteService.getById(rootId);
        NoteBO root = new NoteBO();
        List<NoteBO> rootNoteChildren = ListUtils.newLinkedList();
        root.setId(rootNoteEntity.getId());
        root.setLabel(rootNoteEntity.getName());

        List<NoteEntity> noteEntityList = noteService.list();
        for (NoteEntity noteEntity : noteEntityList) {
            if(ObjectTools.equals(noteEntity.getId(),rootId)){
                continue;
            }
            if(ObjectTools.equals(noteEntity.getParentId(),rootId)){
                rootNoteChildren.add(entityToBo(noteEntity,noteEntityList));
            }
        }
        root.setChildren(rootNoteChildren);
        List<NoteBO> tree = ListUtils.newLinkedList();
        tree.add(root);
        return tree;
    }

    private NoteBO entityToBo(NoteEntity entity,List<NoteEntity> entityList){
        NoteBO noteBO = new NoteBO();
        List<NoteBO> rootNoteChildren = ListUtils.newLinkedList();
        noteBO.setId(entity.getId());
        noteBO.setLabel(entity.getName());
        for (NoteEntity noteEntity : entityList) {
            if(ObjectTools.equals(entity.getId(),noteEntity.getParentId())){
                rootNoteChildren.add(entityToBo(noteEntity,entityList));
            }
        }
        noteBO.setChildren(rootNoteChildren);
        return noteBO;
    }
}