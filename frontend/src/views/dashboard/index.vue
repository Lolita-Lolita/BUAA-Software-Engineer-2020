<template>
  <div>
    <div class="dashboard-container">
      <div class="dashboard-text">{{ welcomeString }}</div>
      <el-divider />
      <div class="dashboard-quote">
        <div class="dashboard-quote-text">君来坐树下，饱食携其余。</div>
        <div class="dashboard-quote-text">归舍遗儿子，怀抱不可虚。</div>
        <div class="dashboard-quote-text">——苏轼《和陶归园田居》</div>
      </div>
    </div>
    <div class="background" :style="backgroundStyle" />
  </div>
</template>

<script>

export default {
  name: 'Dashboard',
  data() {
    return {
      backgroundStyle: {
        backgroundImage: 'url(' + require('@/assets/background.png') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: 'cover'
      }
    }
  },
  computed: {
    username: function() {
      // console.log(this.$store.getters)
      return this.$store.state.name
    },
    welcomeString: function() {
      if (this.username === '用户') {
        return '人是铁，饭是钢，吃得饱，睡得香。'
      } else {
        return '欢迎您，' + this.username + '！今天您恰了吗？'
      }
    }
  }
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
  &-quote {
    padding: 8px 16px;
    background-color: #ecf8ff;
    border-radius: 4px;
    border-left: 5px solid #50bfff;
    margin: 20px 0;
    &-text{
      margin: 20px 0;
      font-size: 2em;
      font-family: '华文仿宋','Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    }
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
