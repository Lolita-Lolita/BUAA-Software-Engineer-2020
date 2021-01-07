<template>
  <div :class="{'has-logo':showLogo}">
    <logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="route in myRoutes" :key="route.path" :item="route" :base-path="route.path" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'
import { getList,getRBACSession } from '@/api/user'

export default {
  components: { SidebarItem, Logo },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  },
  data() {
    return {
      myRoutes:[],
      userRoute:{}
    }
  },
  mounted() {
    this.myRoutes = JSON.parse(JSON.stringify(this.$router.options.routes))
   
    this.$store.commit('setProcessing', true)

    // this.sleeps(5000)

    let _this=this

    for (var i = 0; i < _this.myRoutes.length; i++) {
      if (_this.myRoutes[i].path === "/user") {
        _this.userRoute = _this.myRoutes[i]
        _this.myRoutes.splice(i, 1)
        break
      }
    }

    setTimeout(function()  {
      getRBACSession()
      _this.$http.get('http://localhost:9528/api/rbac/userCrud/findAll').then(
        response => {
          _this.myRoutes.push(_this.userRoute)
          _this.$store.commit('setProcessing', false)
        },
        response => {
          _this.$store.commit('setProcessing', false)
        },
      )
    }, 1000);
  },
  methods: {
    sleeps(delay){
      var start = new Date().getTime();
      while (new Date().getTime() < start + delay){
      }
    }
  },
}
</script>
