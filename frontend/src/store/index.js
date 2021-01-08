import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import user from './modules/user'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    user
  },
  getters,
  state: {
    name: '用户',
    processing: false,
    userNum: 2,
    session:{
      rbac: '',
      credit: '',
      dish: '',
    }
  },
  mutations: {
    setName(state, newName) {
      state.name = newName
    },
    setSession(state, newSession){
      state.session[newSession.name] = newSession.id
    },
    setProcessing(state, processing){
      state.processing = processing
    },
    setUserNum(state, num){
      state.userNum = num;
    }
  }
})

export default store
