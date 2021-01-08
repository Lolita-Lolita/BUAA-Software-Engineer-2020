<template>
  <div class="app-container">
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
      <el-table-column label="用户名">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="权限" width="200" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.role | statusFilter">{{ scope.row.role }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="220" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button slot="reference" type="primary" size="mini" :disabled="row.id==1" @click="handleEdit(row)">
            编辑
          </el-button>
          <el-button type="danger" size="mini" :disabled="row.id==1" @click="handleDelete(row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="管理用户" :visible.sync="dialogFormVisible">
      <el-form :model="userForm">
        <el-form-item label="用户id" :label-width="formLabelWidth">
          <el-input v-model="userForm.id" :disabled="true" autocomplete="off" />
        </el-form-item>
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="userForm.newUserName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="用户权限" :label-width="formLabelWidth">
          <el-select v-model="userForm.newRole" placeholder="请选择用户权限">
            <el-option label="user" value="user" />
            <el-option label="admin" value="admin" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleConfirm()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { getList, deleteUser, userRegister, updateUser } from '@/api/user'
export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        'admin': 'success',
        'user': 'primary'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      dialogFormVisible: false,
      formLabelWidth: '120px',
      userForm: {
        id: 0,
        newUserName: '',
        newRole: ''
      },
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id'
      },
      importanceOptions: [1, 2, 3],
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
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogPvVisible: false,
      pvData: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getList().then(response => {
        console.log(1)
        this.list = response
        this.listLoading = false
      }).catch(() => {
        console.log(2)
      })
    },
    // handleFilter() {
    //   this.listQuery.page = 1
    //   this.getList()
    // },
    handleEdit(row) {
      console.log(row)
      this.userForm = {
        id: row.id,
        newUserName: row.username,
        newRole: row.role
      }
      this.dialogFormVisible = true
    },
    handleConfirm() {
      updateUser(this.userForm).then(response => {
        this.$message({
          message: '修改权限成功',
          type: 'success'
        })
        this.dialogFormVisible = false
        this.fetchData()
      }).catch(error => {
        this.$message.error('修改权限失败')
        console.log(error)
      })
    },
    handleDelete(id) {
      deleteUser(id).then(response => {
        this.$message({
          message: '删除用户成功',
          type: 'success'
        })
        this.fetchData()
      }).catch(error => {
        this.$message.error('删除用户失败')
        console.log(error)
      })
    }
  }
}
</script>
