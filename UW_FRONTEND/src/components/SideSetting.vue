<!--侧边功能导航-->
<template>
  <div class="side-setting">
    <div class="setting-container">
      <div class="icon-container" :class="activeItem === 'material' ? 'icon-active' : '' " @click="initData('material')">
        <div class="setting-icon">
          <icon name="table" scale="1.6" style="color: #fff;"></icon>
        </div>
        <span>物料</span>
      </div>
      <div class="icon-container" :class="activeItem === 'tasks' ? 'icon-active' : ''" @click="initData('tasks')">
        <div class="setting-icon">
          <icon name="tasks" scale="1.6" style="color: #fff;"></icon>
        </div>
        <span>任务</span>
      </div>
      <div class="icon-container" :class="activeItem === 'logs' ? 'icon-active' : ''" @click="initData('logs')">
        <div class="setting-icon">
          <icon name="logs" scale="1.6" style="color: #fff;"></icon>
        </div>
        <span>日志</span>
      </div>
      <div class="icon-container" :class="activeItem === 'robot' ? 'icon-active' : ''" @click="initData('robot')">
        <div class="setting-icon">
          <icon name="forklift" scale="4" style="color: #fff;"></icon>
        </div>
        <span>叉车</span>
      </div>
      <div class="icon-container" :class="activeItem === 'io' ? 'icon-active' : ''" @click="initData('io')">
        <div class="setting-icon">
          <icon name="transfer" scale="1.6" style="color: #fff;"></icon>
        </div>
        <span>出入库</span>
      </div>
      <!--<div class="icon-container">-->
      <!--<div class="setting-icon"></div>-->
      <!--</div>-->
      <div class="mt-auto w-100">
        <div class="icon-container " :class="activeItem === 'user' ? 'icon-active' : ''"
             @click="initData('user')">
          <div class="setting-icon">
            <icon name="users" scale="1.6" style="color: #fff;"></icon>
          </div>
          <span>用户</span>
        </div>
        <div class="icon-container" @click="logout">
          <div class="setting-icon">
            <icon name="power" scale="1.6" style="color: #fff;"></icon>
          </div>
          <span>登出</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {mapGetters, mapActions} from 'vuex'
  import {axiosPost} from "../utils/fetchData";
  import {logoutUrl} from "../config/globalUrl";
  import {errHandler} from "../utils/errorHandler";

  export default {
    name: "SideSetting",
    data() {
      return {
        activeItem: 'material' //活动项目
      }
    },
    watch: {
      // $route: function (val) {
      //   console.log(val.path)
      //   //this.activeItem = val.path
      // }
    },
    mounted() {
      if (this.$route.path !== '/') {
        let tempPath = this.$route.path.slice(1);
        let index = tempPath.indexOf('/');
        if (index > -1) {
          this.activeItem = tempPath.slice(0, tempPath.indexOf('/'))
        } else {
          this.activeItem = tempPath
        }
      }
    },
    computed: {
      ...mapGetters(['routerIn', 'token'])
    },
    methods: {
      ...mapActions(['setTableRouter', 'setLoading', 'setLoginToken']),
      /*点击切换项目、路由导航*/
      initData: function (item) {
        if (item !== this.activeItem) {
          this.toggleState(item);
          this.linkTo(item);
        }
      },
      /*切换当前活动项目*/
      toggleState: function (val) {
        this.activeItem = val;
      },
      /*路由导航*/
      linkTo: function (val) {
        //this.setTableRouter('default');
        this.setLoading(true);
        this.$router.push({
          path: '/' + val,
        })
      },
      logout: function () {
        let options = {
          url: logoutUrl,
          data: {}
        };
        axiosPost(options).then(res => {
          if (res.data.result === 200 || res.data.result === 400) {
            this.setLoginToken('');
            localStorage.removeItem('token');
            window.location.href = window.g.SYSTEM_PATH + '/#/login'
          } else {
            errHandler(res.data.result)
          }
        }).catch(err => {
          console.log(JSON.stringify(err));
        })

      }
    }
  }
</script>

<style scoped>
  .side-setting {
    width: 60px;
    background: #458aff;
    position: fixed;
    height: 100%;
    z-index: 10;

  }

  .setting-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100%;
  }

  .setting-icon {
    border-radius: 7px;
    width: 28px;
    height: 28px;
    background: #ffb85b;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;

  }

  .icon-container span {
    display: inline-block;
    font-size: 12px;
    line-height: 16px;
    color: #ffffff;
  }

  .icon-container {
    display: flex;
    align-items: center;
    flex-direction: column;
    margin: 20px 0;
    padding: 5px 0;
    cursor: pointer;
    width: 100%;
  }

  .setting-container .icon-active {
    border-left: #a4efff 3px solid;
    background: #4f97ff;
  }
</style>
