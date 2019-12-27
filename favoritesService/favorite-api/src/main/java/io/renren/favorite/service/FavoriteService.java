package io.renren.favorite.service;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.enums.YesOrNoEnum;
import io.renren.common.utils.ListUtils;
import io.renren.common.utils.ObjectTools;
import io.renren.common.utils.UrlDomainUtils;
import io.renren.favorite.bo.FavoriteTypeBO;
import io.renren.favorite.bo.FavoriteWebsiteBO;
import io.renren.favorite.entity.FavoriteTypeEntity;
import io.renren.favorite.entity.FavoriteWebsiteEntity;
import io.renren.favorite.form.FavoriteTypeEditForm;
import io.renren.favorite.form.FavoriteWebsiteEditForm;
import io.renren.favorite.form.FavoriteWebsiteFavoriteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteTypeService favoriteTypeService;
    @Autowired
    private FavoriteWebsiteService favoriteWebsiteService;

    public void click(Long id){
        FavoriteWebsiteEntity entity = favoriteWebsiteService.getById(id);
        if(ObjectTools.isNull(entity)){
            return;
        }
        entity.setCount(entity.getCount()+1);
        favoriteWebsiteService.saveOrUpdate(entity);
    }

    public List<FavoriteTypeBO> allTypeList(){
        List<FavoriteTypeBO> typeBOList = ListUtils.newLinkedList();
        FavoriteTypeBO favoriteTypeBO = new FavoriteTypeBO();
        favoriteTypeBO.setId(-1L);
        favoriteTypeBO.setName("我的收藏");
        favoriteTypeBO.setIcon("el-icon-star-on");
        favoriteTypeBO.setSn(0);
        typeBOList.add(favoriteTypeBO);

        FavoriteTypeBO allTypeBO = new FavoriteTypeBO();
        allTypeBO.setId(0L);
        allTypeBO.setName("全部收藏");
        allTypeBO.setIcon("el-icon-s-help");
        allTypeBO.setSn(1);
        typeBOList.add(allTypeBO);

        typeBOList.addAll(this.typeList());
        return typeBOList;
    }

    public List<FavoriteTypeBO> typeList(){
        List<FavoriteTypeBO> typeBOList = ListUtils.newLinkedList();

        List<FavoriteTypeEntity> entityList = favoriteTypeService.list(
                new QueryWrapper<FavoriteTypeEntity>()
                        .orderByAsc("sn")
        );
        if(!CollectionUtils.isEmpty(entityList)){
            for (FavoriteTypeEntity entity : entityList) {
                FavoriteTypeBO typeBO = new FavoriteTypeBO();
                typeBO.setId(entity.getId());
                typeBO.setName(entity.getName());
                typeBO.setIcon(entity.getIcon());
                typeBOList.add(typeBO);
            }
        }
        return typeBOList;
    }

    public void websiteFavorite(FavoriteWebsiteFavoriteForm favoriteForm){
        FavoriteWebsiteEntity entity = favoriteWebsiteService.getById(favoriteForm.getId());
        if(ObjectTools.isNull(entity)){
            return;
        }
        entity.setFavorite(YesOrNoEnum.getIndex(favoriteForm.getFavorite()));
        favoriteWebsiteService.saveOrUpdate(entity);
    }

    public List<FavoriteWebsiteBO> websiteList(Long typeId){
        List<FavoriteWebsiteBO> websiteBOList = ListUtils.newLinkedList();
        QueryWrapper<FavoriteWebsiteEntity> wrapper = new QueryWrapper<>();
        if(typeId==0L){
            wrapper.orderByAsc("sn");
        }else if(typeId==-1L){
            wrapper.eq("favorite",1);
            wrapper.orderByDesc("count");
        }else {
            wrapper.eq("type_id",typeId);
            wrapper.orderByDesc("count");
        }

        List<FavoriteWebsiteEntity> entityList = favoriteWebsiteService.list(wrapper);
        if(!CollectionUtils.isEmpty(entityList)){
            for (FavoriteWebsiteEntity entity : entityList) {
                websiteBOList.add(this.websiteEntityToBO(entity));
            }
        }
        return websiteBOList;
    }

    public FavoriteWebsiteBO websiteInfo(Long id){
        FavoriteWebsiteBO websiteBO = new FavoriteWebsiteBO();

        FavoriteWebsiteEntity entity = favoriteWebsiteService.getById(id);
        if(ObjectTools.isNull(entity)){
            return websiteBO;
        }
        return this.websiteEntityToBO(entity);
    }

    public FavoriteWebsiteBO websiteEntityToBO(FavoriteWebsiteEntity entity){
        FavoriteWebsiteBO websiteBO = new FavoriteWebsiteBO();
        websiteBO.setId(entity.getId());
        websiteBO.setTypeId(entity.getTypeId());
        websiteBO.setName(entity.getName());
        websiteBO.setUrl(entity.getUrl());
        websiteBO.setIconUrl(entity.getIconUrl());
        websiteBO.setDegree(entity.getDegree());
        websiteBO.setDescp(entity.getDescp());
        websiteBO.setCount(entity.getCount());
        websiteBO.setFavorite(YesOrNoEnum.getFlag(entity.getFavorite()));
        websiteBO.setSn(0);
        return websiteBO;
    }

    public void typeEdit(FavoriteTypeEditForm editForm){
        FavoriteTypeEntity entity = favoriteTypeService.getById(editForm.getId());
        if(ObjectTools.isNull(entity)){
            entity = new FavoriteTypeEntity();
        }
        entity.setName(editForm.getName());
        entity.setIcon(editForm.getIcon());
        favoriteTypeService.saveOrUpdate(entity);
    }

    public void websiteEdit(FavoriteWebsiteEditForm editForm){
        FavoriteWebsiteEntity entity = favoriteWebsiteService.getById(editForm.getId());

        if(ObjectTools.isNull(entity)){
            entity = new FavoriteWebsiteEntity();
        }
        entity.setTypeId(editForm.getTypeId());
        entity.setName(editForm.getName());
        entity.setUrl(editForm.getUrl());
        entity.setFavorite(YesOrNoEnum.getIndex(editForm.getFavorite()));
        entity.setIconUrl(UrlDomainUtils.getDomainHost(editForm.getUrl())+"favicon.ico");
        entity.setDescp(editForm.getDescp());
        favoriteWebsiteService.saveOrUpdate(entity);
    }

}
