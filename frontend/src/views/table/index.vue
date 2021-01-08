<template>
  <div class="app-container">

    <div class="filter-container">
      <el-input v-model="listQuery.dishName" placeholder="菜品名称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model="listQuery.taste" placeholder="口味" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.dishType" placeholder="种类" clearable style="width: 150px" class="filter-item">
        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item" />
      </el-select>
      <el-select v-model="listQuery.location" placeholder="位置" clearable style="width: 150px" class="filter-item">
        <el-option v-for="item in locationOptions" :key="item" :label="item" :value="item" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" style="width: 100px" @click="handleFilter">
        搜索
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column align="center" label="ID" width="100">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="菜品名称" align="center" >
        <template slot-scope="scope">
          {{ scope.row.dishName }}
        </template>
      </el-table-column>
      <el-table-column label="价格" width="200" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.dishPrice }}</span>
        </template>
      </el-table-column>
      <el-table-column label="口味" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.taste }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="种类" width="200" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.dishType | statusFilter">{{ scope.row.dishType }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="位置" width="200" align="center">
      <template slot-scope="scope">
        {{ scope.row.location }}
      </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleCheck(row)">
            查看评分
          </el-button>
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            评分
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="block" style="padding-top: 30px">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        background
        layout="total, prev, pager, next"
        :total="total"
        :page-size="20">
      </el-pagination>
    </div>

    <el-dialog title="评分" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="80px" style="width: 400px; margin-left:50px;">
        <el-form-item label="菜品名称" prop="dishName" >
          <el-input v-model="temp.dishName" disabled="true"/>
        </el-form-item>
        <el-form-item label="菜品味道" prop="taste" >
          <el-input v-model="temp.taste" disabled="true"/>
        </el-form-item>
        <el-form-item label="菜品位置" prop="location" >
          <el-input v-model="temp.location" disabled="true"/>
        </el-form-item>
        <el-form-item label="外表评分" prop="lookCredit" required="true" >
          <el-rate style="margin:10px 0 10px 0;" v-model="temp.lookCredit" show-score></el-rate>
        </el-form-item>
        <el-form-item label="香味评分" prop="smellCredit" required="true" >
          <el-rate style="margin:10px 0 10px 0;" v-model="temp.smellCredit" show-score></el-rate>
        </el-form-item>
        <el-form-item label="味道评分" prop="tasteCredit" required="true" >
          <el-rate style="margin:10px 0 10px 0;" v-model="temp.tasteCredit" show-score></el-rate>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          确定
        </el-button>
      </div>
    </el-dialog>

    <el-dialog title="查看评分" :visible.sync="dialogFormVisible2">
      <el-form ref="dataTable" :model="temp2,temp" label-position="left" label-width="80px" style="width: 400px; margin-left:50px;">
        <el-form-item label="菜品名称" prop="dishName" >
          <el-input v-model="temp.dishName" disabled="true"/>
        </el-form-item>
        <el-form-item label="菜品味道" prop="taste" >
          <el-input v-model="temp.taste" disabled="true"/>
        </el-form-item>
        <el-form-item label="菜品位置" prop="location" >
          <el-input v-model="temp.location" disabled="true"/>
        </el-form-item>
        <el-form-item label="平均评分" prop="lookCredit" >
          <el-rate style="margin:10px 0 10px 0;" v-model="temp2.averageCredit" disabled ></el-rate>
        </el-form-item>
        <el-form-item label="外表评分" prop="lookCredit" >
          <el-rate style="margin:10px 0 10px 0;" v-model="temp2.lookCredit" disabled ></el-rate>
        </el-form-item>
        <el-form-item label="香味评分" prop="smellCredit">
          <el-rate style="margin:10px 0 10px 0;" v-model="temp2.smellCredit" disabled ></el-rate>
        </el-form-item>
        <el-form-item label="味道评分" prop="tasteCredit">
          <el-rate style="margin:10px 0 10px 0;" v-model="temp2.tasteCredit" disabled ></el-rate>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">
          确定
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import { getFoodList, setCredit, getFoodDetail } from '@/api/user'
  export default {
    filters: {
      statusFilter(status) {
        const statusMap = {
          素菜: 'success',
          荤菜: 'gray',
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        tableKey: 0,
        list: null,
        total: 0,
        currentPage:0,
        listLoading: true,
        listQuery: {
          page: 0,
          id:undefined,
          dishName: undefined,
          dishPrice:undefined,
          taste: undefined,
          dishType: undefined,
        },
        importanceOptions: ['荤菜', '素菜'],
        locationOptions: ['学一食堂', '学二食堂','学四食堂'],
        showReviewer: false,
        temp: {
          id: undefined,
          dishName: undefined,
          taste: undefined,
          location: undefined,
          lookCredit: '',
          smellCredit: '',
          tasteCredit: '',
        },
        temp2: {
          averageCredit: undefined,
          lookCredit: undefined,
          smellCredit: undefined,
          tasteCredit: undefined,
        },
        dialogFormVisible: false,
        dialogFormVisible2: false,
        dialogStatus: '',
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        getFoodList(this.listQuery).then(response => {
          console.log(response)
          this.total = response.totalElements
          console.log(this.total)
          this.list= response.content
          this.listLoading = false
        })
      },
      handleFilter() {
        this.handleCurrentChange(1)
        //this.fetchData()
      },
      handleUpdate(row) {
        console.log(row)
        this.temp = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetField ()
        })
      },
      handleCheck(row) {
        getFoodDetail(row.id).then(response => {
          console.log(response)
          this.temp = Object.assign({}, response)
          this.temp2 = Object.assign({}, response.credit) // copy obj
          this.temp2['averageCredit'] = this.fitScoreRange(this.temp2['averageCredit'])
          this.temp2['lookCredit'] = this.fitScoreRange(this.temp2['lookCredit'])
          this.temp2['smellCredit'] = this.fitScoreRange(this.temp2['smellCredit'])
          this.temp2['tasteCredit'] = this.fitScoreRange(this.temp2['tasteCredit'])
          this.dialogFormVisible2 = true
          this.$nextTick(() => {
            this.$refs['dataTable'].resetField ()
          })
        }).catch(error => {
          console.log(error)
          this.temp2['averageCredit'] =0
          this.temp2['lookCredit'] = 0
          this.temp2['smellCredit'] = 0
          this.temp2['tasteCredit'] = 0
        })
      },
      fitScoreRange(score){
        if(score=="NaN"){
          return 0
        }
        if(score>5){
          return 5
        }
        else if(score<0){
          return 0
        }
        else {
          return score
        }
      },
      updateData() {
            const tempData = Object.assign({}, this.temp)
            const params = {dishID:tempData.id, lookCredit:tempData.lookCredit, smellCredit: tempData.smellCredit, tasteCredit: tempData.tasteCredit}
            setCredit(params).then(response => {
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '评分成功',
                type: 'success',
                duration: 2000
              })
            })
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.listQuery.page = val-1
        getFoodList(this.listQuery).then(response => {
          console.log(response)
          this.list= response.content
          this.listLoading = false
        })
      }
    }
  }
</script>
