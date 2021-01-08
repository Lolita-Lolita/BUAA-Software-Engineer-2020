<template>
  <div>
    <div class="dashboard-container">
      <div class="dashboard-text">{{ welcomeString }}</div>
      <el-divider />
      <div>
        <el-row :gutter="40" class="panel-group">
          <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
            <div class="card-panel">
              <div class="card-panel-icon-wrapper icon-dashboard">
                <svg-icon icon-class="dashboard" class-name="card-panel-icon" />
              </div>
              <div class="card-panel-description">
                <div class="card-panel-text">
                  菜品数量
                </div>
                <div class="card-panel-num" >36</div>
              </div>
            </div>
          </el-col>
          <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
            <div class="card-panel">
              <div class="card-panel-icon-wrapper icon-people">
                <svg-icon icon-class="peoples" class-name="card-panel-icon" />
              </div>
              <div class="card-panel-description">
                <div class="card-panel-text">
                  用户数量
                </div>
                <div class="card-panel-num" >2</div>
              </div>
            </div>
          </el-col>
          <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
            <div class="card-panel">
              <div class="card-panel-icon-wrapper icon-star">
                <svg-icon icon-class="star" class-name="card-panel-icon" />
              </div>
              <div class="card-panel-description">
                <div class="card-panel-text">
                  评分最高菜品
                </div>
                <div class="card-panel-num" >麻辣香锅</div>
              </div>
            </div>
          </el-col>
          <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
            <div class="card-panel">
              <div class="card-panel-icon-wrapper icon-shopping">
                <svg-icon icon-class="bug" class-name="card-panel-icon" />
              </div>
              <div class="card-panel-description">
                <div class="card-panel-text">
                  评分最低菜品
                </div>
                <div class="card-panel-num" >干烧鱼块</div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
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
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-dashboard {
        background: #36a3f7;
      }

      .icon-star {
        background: #f4516c;
      }

      .icon-shopping {
        background: rgba(0, 0, 0, .5)
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-dashboard {
      color: #36a3f7;
    }

    .icon-star {
      color: #f4516c;
    }

    .icon-shopping {
      color: rgba(0, 0, 0, .5)
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
