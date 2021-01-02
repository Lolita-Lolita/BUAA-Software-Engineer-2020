<template>
  <div>
    <div class="dashboard-container">
      <div class="dashboard-text">name: {{ name }}</div>
    </div>
    <div class="background" :style="backgroundStyle">
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      backgroundStyle: {
            backgroundImage:'url(' + require('@/assets/background.png') + ')',
            backgroundRepeat:'no-repeat',
            backgroundSize:'cover',
      }
    }
  },
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
.background{
  position: fixed;
  right: 0;
  bottom: 0;
  height: 420px;
  width: 300px;
  opacity: 0.5;
}
</style>
