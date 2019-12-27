<template>

  <el-container>
    <el-aside
      width="200px"
      style="height:100%"
    >
      <el-menu
        style="border-right:0px"
        :default-active="-1"
        :collapse="false"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <el-menu-item
          v-for="item in typeList"
          :key="item.id"
          :index="item.id"
          @click="clickMenu(item.id)"
        >
          <i :class="item.icon" />
          <span slot="title">{{ item.name }}</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>

      <div
        v-for="item in curItemList"
        :key="item.id"
        style="float: left;margin-left:20px;margin-top:20px;"
      >
        <el-badge
          :value="item.count"
          class="item"
        >
          <el-card
            shadow="hover"
            style="width:240px;height:120px;"
          >
            <div
              slot="header"
              class="clearfix"
            >
              <el-link
                style="float: right; padding: 3px 0;font-size:18px;margin-right:5px"
                icon="el-icon-edit"
                @click="editWebsite(item.id)"
              />
              <el-link
                v-if="item.favorite"
                type="danger"
                style="padding: 3px 0;font-size:20px"
                icon="el-icon-star-on"
                @click="clickFavorite(item,false)"
              />
              <el-link
                v-if="!item.favorite"
                type="danger"
                style="padding: 3px 0;font-size:20px"
                icon="el-icon-star-off"
                @click="clickFavorite(item,true)"
              />
            </div>

            <el-row>
              <el-image
                style="width: 16px; height: 16px"
                :src="item.iconUrl"
              />
              <a
                style="margin-left:6px;color:green"
                href="#"
                @click="openUrl(item)"
              >{{ item.name }}</a>
            </el-row>
            <el-row>
              <span style="font-size:10px;color:#B2B2B2">{{ item.descp }}</span>

            </el-row>
          </el-card>
        </el-badge>
      </div>
      <div style="float: left;margin-left:20px;margin-top:20px;">
        <el-button
          type="primary"
          icon="el-icon-plus"
          @click="editWebsite(null)"
        >添加</el-button>
      </div>
    </el-main>
    <favoriteEditComp
      ref="editCompRef"
      @parentCallback="changeType"
    />
  </el-container>
</template>
<script>
import { favoriteApi } from '@/api/favorite'
import favoriteEditComp from './edit'
export default {
  components: {
    favoriteEditComp
  },
  data () {
    return {
      typeId: null,
      typeList: [],
      curItemList: []
    }
  },
  watch: {
    typeId: function (newValue, oldValue) {
      this.changeType()
    }
  },
  created: function () {
    this.getTypeList()
    this.typeId = -1
  },
  methods: {
    clickFavorite(item, favorite) {
      item.favorite = favorite
      var _ = this
      favoriteApi.websiteFavorite(item).then(response => {
        _.changeType()
      })
    },
    editWebsite(id) {
      this.$refs.editCompRef.open(id, this.typeId)
    },
    getTypeList() {
      favoriteApi.allTypeList().then(response => {
        this.typeList = response.typeList
      })
    },
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    },
    openUrl (item) {
      console.info('item', item)
      var _ = this
      favoriteApi.websiteClick(item.id).then(response => {
        _.changeType()
      })
      window.open(item.url)
    },
    clickMenu (typeId) {
      this.typeId = typeId
    },
    changeType () {
      favoriteApi.websiteList(this.typeId).then(response => {
        this.curItemList = response.websiteList
      })
    }
  }
}
</script>
<style>
.el-card__header {
  padding: 10px 12px;
  border-bottom: 1px solid #ebeef5;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
.el-header {
  padding: 0px !important;
}
.el-card__body {
  padding: 15px !important;
}
</style>
