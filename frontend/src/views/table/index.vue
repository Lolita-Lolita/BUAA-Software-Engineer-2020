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
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        Search
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
      <el-table-column label="查看评分" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            评分
          </el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  import { getFoodList } from '@/api/user'
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
        listLoading: true,
        listQuery: {
          dishName: undefined,
          dishPrice:undefined,
          taste: undefined,
          dishType: undefined,
        },
        importanceOptions: ['荤菜', '素菜'],
        locationOptions: ['学一食堂', '学二食堂'],
        sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
        statusOptions: ['published', 'draft', 'deleted'],
        showReviewer: false,
        temp: {
          id: undefined,
          importance: 1,
          remark: '',
          timestamp: new Date(),
          title: '',
          type: '',
          status: 'published'
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: 'Edit',
          create: 'Create'
        },
        dialogPvVisible: false,
        pvData: [],
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        getFoodList(this.listQuery).then(response => {
          //console.log(response)
          this.list = response.content
          this.listLoading = false
        })
      },

      handleFilter() {
        console.log(this.listQuery)
        this.fetchData()
      },
    }
  }
</script>
