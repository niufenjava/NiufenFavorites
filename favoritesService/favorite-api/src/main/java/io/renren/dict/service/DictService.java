package io.renren.dict.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;

import io.renren.common.exception.RRException;
import io.renren.common.utils.ListUtils;
import io.renren.common.utils.ObjectTools;
import io.renren.dict.bo.WordSimpleBO;
import io.renren.dict.entity.DictWordDefEntity;
import io.renren.dict.entity.DictWordDefExampleEntity;
import io.renren.dict.entity.DictWordEntity;
import io.renren.dict.entity.DictWordEtymaRelEntity;
import io.renren.dict.enums.WordDefTypeEnum;
import io.renren.dict.enums.WordSortTypeEnum;
import io.renren.dict.form.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 字典相关服务类
 *
 * @author haijun.zhang
 * @date 2019/12/19
 * @time 11:32
 */
@Service
public class DictService {

    @Autowired
    private DictWordService wordService;
    @Autowired
    private DictWordDefService wordDefService;
    @Autowired
    private DictWordDefExampleService wordDefExampleService;
    @Autowired
    private DictWordEtymaRelService wordEtymaRelService;

    public void wordDegree(WordDegreeForm degreeForm){
        DictWordEntity wordEntity = wordService.getById(degreeForm.getId());
        wordEntity.setDegree(degreeForm.getDegree());
        wordService.saveOrUpdate(wordEntity);
    }

    public List<WordSimpleBO> listWordByIds(List<Long> ids){
        List<WordSimpleBO> wordNameList = ListUtils.newLinkedList();
        List<DictWordEntity> entityList = wordService.list(new QueryWrapper<DictWordEntity>()
                .in("id",ids)
                .orderByAsc("name")
        );
        if(!CollectionUtils.isEmpty(entityList)){
            for (DictWordEntity wordEntity : entityList) {
                WordSimpleBO simpleBO = new WordSimpleBO();
                simpleBO.setId(wordEntity.getId());
                simpleBO.setName(wordEntity.getName());
                simpleBO.setDescp(wordEntity.getDescp());
                simpleBO.setChangeList(this.toChangeList(wordEntity));
                wordNameList.add(simpleBO);
            }
        }
        return wordNameList;
    }

    private List<String> toChangeList(DictWordEntity wordEntity){
        List<String> changeList = ListUtils.newStringList();
        if(ObjectTools.isNotNull(wordEntity.getName())){
            changeList.add(wordEntity.getName());
        }
        if(ObjectTools.isNotNull(wordEntity.getOther())){
            changeList.add(wordEntity.getOther());
        }
        if(ObjectTools.isNotNull(wordEntity.getPastParticiple())){
            changeList.add(wordEntity.getPastParticiple());
        }
        if(ObjectTools.isNotNull(wordEntity.getPastTense())){
            changeList.add(wordEntity.getPastTense());
        }
        if(ObjectTools.isNotNull(wordEntity.getPlural())){
            changeList.add(wordEntity.getPlural());
        }
        if(ObjectTools.isNotNull(wordEntity.getPresentParticiple())){
            changeList.add(wordEntity.getPresentParticiple());
        }
        if(ObjectTools.isNotNull(wordEntity.getThirdSingular())){
            changeList.add(wordEntity.getThirdSingular());
        }
        return changeList;
    }

    public List<WordSimpleBO> listWordName(WordListQueryForm queryForm){
        List<WordSimpleBO> wordNameList = ListUtils.newLinkedList();
        QueryWrapper wrapper = new QueryWrapper<DictWordEntity>();
        if(ObjectTools.in(queryForm.getSortType(),WordSortTypeEnum.DEFAULT.getIndex())){
            wrapper.orderByAsc(WordSortTypeEnum.getSqlByIndex(queryForm.getSortType()));

        }else{
            wrapper.orderByDesc(WordSortTypeEnum.getSqlByIndex(queryForm.getSortType()));
        }
        List<DictWordEntity> entityList = wordService.list(wrapper);
        if(!CollectionUtils.isEmpty(entityList)){
            for (DictWordEntity wordEntity : entityList) {
                WordSimpleBO simpleBO = new WordSimpleBO();
                simpleBO.setId(wordEntity.getId());
                simpleBO.setName(wordEntity.getName());
                simpleBO.setDescp(wordEntity.getDescp());
                wordNameList.add(simpleBO);
            }
        }
        return wordNameList;
    }

    @Transactional
    public WordForm getWordForm(String word) {
        WordForm wordForm = new WordForm();

        List<Long> eytmaIds = ListUtils.newLongList();
        DictWordEntity wordEntity = wordService.getByName(word);

        if (ObjectTools.isNotNull(wordEntity)) {

            wordEntity.setQueryCount(wordEntity.getQueryCount()+1);
            wordService.saveOrUpdate(wordEntity);

            wordForm.setId(wordEntity.getId());
            wordForm.setName(wordEntity.getName());
            wordForm.setSoundmark(wordEntity.getSoundmark());
            wordForm.setDegree(wordEntity.getDegree());

            wordForm.setPlural(wordEntity.getPlural());
            wordForm.setThirdSingular(wordEntity.getThirdSingular());
            wordForm.setPresentParticiple(wordEntity.getPresentParticiple());
            wordForm.setPastParticiple(wordEntity.getPastParticiple());
            wordForm.setPastTense(wordEntity.getPastTense());
            wordForm.setOther(wordEntity.getOther());
            wordForm.setSkillDesc(wordEntity.getSkillDesc());

            List<DictWordDefEntity> defEntityList = wordDefService.listByWordId(wordEntity.getId());
            if (!CollectionUtils.isEmpty(defEntityList)) {
                String wordDescp = "";
                List<WordDefForm> defFormList = ListUtils.newLinkedList();
                for (DictWordDefEntity defEntity : defEntityList) {
                    WordDefForm defForm = new WordDefForm();
                    defForm.setId(defEntity.getId());
                    defForm.setName(defEntity.getName());
                    defForm.setType(defEntity.getType());
                    defForm.setTypeDesc(WordDefTypeEnum.getNameByIndex(defEntity.getType()));
                    defForm.setExplainEn(defEntity.getExplainEn());
                    defForm.setExplainCh(defEntity.getExplainCh());
                    defForm.setDescp(defEntity.getDescp());
                    defForm.setSn(defEntity.getSn());
                    wordDescp+= WordDefTypeEnum.getNameByIndex(defEntity.getType())+" "+defEntity.getName()+"；";
                    List<DictWordDefExampleEntity> defExampleEntityList = wordDefExampleService.listByDefId(defEntity.getId());
                    if (!CollectionUtils.isEmpty(defExampleEntityList)) {
                        List<WordDefExampleForm> defExampleFormList = ListUtils.newLinkedList();
                        for (DictWordDefExampleEntity exampleEntity : defExampleEntityList) {
                            WordDefExampleForm exampleForm = new WordDefExampleForm();
                            exampleForm.setId(exampleEntity.getId());
                            exampleForm.setExampleEn(exampleEntity.getExampleEn());
                            exampleForm.setExampleCh(exampleEntity.getExampleCh());
                            exampleForm.setDescp(exampleEntity.getDescp());
                            exampleForm.setSn(exampleEntity.getSn());
                            defExampleFormList.add(exampleForm);
                        }
                        defForm.setExampleList(defExampleFormList);
                    }
                    defFormList.add(defForm);
                }
                wordForm.setDescp(wordDescp);
                wordForm.setDefList(defFormList);
            }
        }
        return wordForm;
    }

    @Transactional(rollbackFor = Exception.class)
    public void wordCreateOrUpdate(WordForm wordForm) {

        // 如果ID为空，则新建
        DictWordEntity wordEntity = wordService.getByName(wordForm.getName());

        if (ObjectTools.isNull(wordForm.getId())) {
            if (ObjectTools.isNotNull(wordEntity)) {
                throw new RRException("该单词已存在");
            }
        } else {
            DictWordEntity existEntity = wordService.getById(wordForm.getId());
            if (ObjectTools.notEquals(wordForm.getName(), existEntity.getName())) {
                if (ObjectTools.isNotNull(wordEntity)) {
                    throw new RRException("该单词已存在");
                }
            }
        }

        DictWordEntity createOrUpdateWordEntity = new DictWordEntity();
        createOrUpdateWordEntity.setId(wordForm.getId());
        createOrUpdateWordEntity.setName(wordForm.getName());
        createOrUpdateWordEntity.setSoundmark(wordForm.getSoundmark());
        createOrUpdateWordEntity.setPlural(wordForm.getPlural());
        createOrUpdateWordEntity.setThirdSingular(wordForm.getThirdSingular());
        createOrUpdateWordEntity.setPresentParticiple(wordForm.getPresentParticiple());
        createOrUpdateWordEntity.setPastParticiple(wordForm.getPastParticiple());
        createOrUpdateWordEntity.setPastTense(wordForm.getPastTense());
        createOrUpdateWordEntity.setOther(wordForm.getOther());
        createOrUpdateWordEntity.setDegree(wordForm.getDegree());
        createOrUpdateWordEntity.setSkillDesc(wordForm.getSkillDesc());
        String descp = "";
        if(!CollectionUtils.isEmpty(wordForm.getDefList())){
            for (WordDefForm defForm : wordForm.getDefList()) {
                descp = descp+"；"+WordDefTypeEnum.getNameByIndex(defForm.getType())+defForm.getName();
            }
            descp = descp.replaceFirst("；","");
        }
        createOrUpdateWordEntity.setDescp(descp);
        createOrUpdateWordEntity.setQueryCount(0);
        wordService.saveOrUpdate(createOrUpdateWordEntity);
        this.batchSaveWordDef(wordForm.getDefList(), createOrUpdateWordEntity);
        this.batchSaveWordEtymaRel(wordForm.getEtymaIdList(), createOrUpdateWordEntity);

    }

    private void batchSaveWordEtymaRel(List<Long> etymaIds, DictWordEntity wordEntity) {
        if (CollectionUtils.isEmpty(etymaIds)) {
            return;
        }
        wordEtymaRelService.delByWordId(wordEntity.getId());
        for (Long etymaId : etymaIds) {
            DictWordEtymaRelEntity relEntity = new DictWordEtymaRelEntity();
            relEntity.setWordId(wordEntity.getId());
            relEntity.setEtymaId(etymaId);
            wordEtymaRelService.save(relEntity);
        }
    }

    public void batchSaveWordDef(List<WordDefForm> wordDefFormList, DictWordEntity wordEntity) {
        wordDefService.delByWordId(wordEntity.getId());
        if (CollectionUtils.isEmpty(wordDefFormList)) {
            return;
        }
        for (WordDefForm defForm : wordDefFormList) {
            if(ObjectTools.isNull(defForm.getName())){
                defForm.setName("");
            }
            DictWordDefEntity defEntity = new DictWordDefEntity();
            defEntity.setWordId(wordEntity.getId());
            defEntity.setName(defForm.getName());
            defEntity.setType(defForm.getType());
            defEntity.setExplainEn(defForm.getExplainEn());
            defEntity.setExplainCh(defForm.getExplainCh());
            defEntity.setDescp(defForm.getDescp());
            defEntity.setSn(defForm.getSn());
            wordDefService.save(defEntity);

            List<WordDefExampleForm> exampleFormList = defForm.getExampleList();
            if (CollectionUtils.isEmpty(exampleFormList)) {
                continue;
            }
            for (WordDefExampleForm exampleForm : exampleFormList) {
                DictWordDefExampleEntity exampleEntity = new DictWordDefExampleEntity();
                exampleEntity.setDefId(defEntity.getId());
                exampleEntity.setExampleEn(exampleForm.getExampleEn());
                exampleEntity.setExampleCh(exampleForm.getExampleCh());
                exampleEntity.setDescp(exampleForm.getDescp());
                exampleEntity.setSn(exampleForm.getSn());
                wordDefExampleService.save(exampleEntity);
            }
        }
    }
}