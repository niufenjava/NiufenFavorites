package io.renren.notebook.service;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.exception.RRException;
import io.renren.common.utils.ListUtils;
import io.renren.common.utils.ObjectTools;
import io.renren.dict.bo.WordSimpleBO;
import io.renren.dict.entity.DictWordEntity;
import io.renren.dict.form.WordDegreeForm;
import io.renren.dict.service.DictService;
import io.renren.notebook.bo.NoteBO;
import io.renren.notebook.bo.NoteDetailBO;
import io.renren.notebook.entity.NoteWordRelEntity;
import io.renren.notebook.form.*;
import io.renren.notebook.entity.NoteContentEntity;
import io.renren.notebook.entity.NoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private NoteWordRelService noteWordRelService;
    @Autowired
    private DictService dictService;

    public void noteDegree(NoteDegreeForm degreeForm){
        NoteEntity noteEntity = noteService.getById(degreeForm.getId());
        noteEntity.setDegree(degreeForm.getDegree());
        noteService.saveOrUpdate(noteEntity);
    }

    public List<WordSimpleBO> listRelWord(Long noteId){
        List wordSimpleBOList = ListUtils.newLinkedList();
        List<NoteWordRelEntity> relEntityList = noteWordRelService.list(new QueryWrapper<NoteWordRelEntity>()
                .eq("note_id", noteId)
        );
        if(!CollectionUtils.isEmpty(relEntityList)){
            List<Long> ids = ListUtils.newLongList();
            for (NoteWordRelEntity noteWordRelEntity : relEntityList) {
                ids.add(noteWordRelEntity.getWordId());
            }
            if(!CollectionUtils.isEmpty(ids)){
                wordSimpleBOList = dictService.listWordByIds(ids);
            }
        }
        return wordSimpleBOList;
    }

    public void noteRelWord(NoteRelWordForm relWordForm){
        NoteWordRelEntity noteWordRelEntity = noteWordRelService.getByNoteAndWordId(relWordForm.getId(), relWordForm.getWordId());
        if(ObjectTools.isNotNull(noteWordRelEntity)){
            return;
        }
        NoteWordRelEntity createNoteWordRelEntity = new NoteWordRelEntity();
        createNoteWordRelEntity.setNoteId(relWordForm.getId());
        createNoteWordRelEntity.setWordId(relWordForm.getWordId());
        noteWordRelService.save(createNoteWordRelEntity);

    }



    public void noteRelWordDel(NoteRelWordForm relWordForm){
        NoteWordRelEntity noteWordRelEntity = noteWordRelService.getByNoteAndWordId(relWordForm.getId(), relWordForm.getWordId());
        if(ObjectTools.isNotNull(noteWordRelEntity)){
            noteWordRelService.removeById(noteWordRelEntity.getId());
        }

    }

    public void noteContentModify(NoteContentForm contentForm){
        NoteContentEntity contentEntity = noteContentService.getByNoteId(contentForm.getId());
        if(ObjectTools.isNull(contentEntity)){
            NoteContentEntity createContentEntity = new NoteContentEntity();
            createContentEntity.setNoteId(contentForm.getId());
            createContentEntity.setContent(contentForm.getContent());
            createContentEntity.setType(0);
            noteContentService.save(createContentEntity);
        }else {
            contentEntity.setContent(contentForm.getContent());
            noteContentService.saveOrUpdate(contentEntity);
        }
    }

    public void noteRename(NoteReNameForm reNameBO){
        NoteEntity noteEntity = noteService.getById(reNameBO.getId());
        noteEntity.setName(reNameBO.getTitle());
        noteService.saveOrUpdate(noteEntity);
    }

    public NoteDetailBO noteDetail(Long id){
        NoteDetailBO detailBO = new NoteDetailBO();
        NoteEntity noteEntity = noteService.getById(id);
        if(ObjectTools.isNull(noteEntity)){
            return detailBO;
        }
        NoteContentEntity contentEntity = noteContentService.getByNoteId(id);
        detailBO.setId(id);
        detailBO.setDegree(noteEntity.getDegree());
        detailBO.setTitle(noteEntity.getName());
        if(ObjectTools.isNotNull(contentEntity)){
            detailBO.setContent(contentEntity.getContent());
        }else {
            detailBO.setContent("");
        }
        return detailBO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void noteDelete(NoteDeleteForm deleteForm){
        List<NoteEntity> children = noteService.listByParentId(deleteForm.getId());
        if(!CollectionUtils.isEmpty(children)){
            throw new RRException("该节点下有子节点，请先删除子节点");
        }
        noteService.removeById(deleteForm.getId());
    }

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