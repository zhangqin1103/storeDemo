<template>
  <div class="container">
    <el-tabs type="border-card">
      <el-tab-pane label="部署查看">
        <el-table :data="flowData" :height="600"  border :style="{'width': '100%','height':tableHeight,'float':'left'}">
          <el-table-column type="index" label="序号" width="50"></el-table-column>
          <el-table-column  prop="deployId" label="部署ID" width="120"></el-table-column>
          <el-table-column  prop="deployName" label="部署名称" width="120"></el-table-column>
          <el-table-column prop="id" label="流程定义ID" width="100"></el-table-column>
          <el-table-column  prop="key" label="流程定义key" width="120"></el-table-column>
          <el-table-column label="操作" fixed="right">
            <template slot-scope="scope">
              <el-button size="mini" @click="seeFlowPng(scope.$index, scope.row)">查看流程图</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="任务管理">
        <el-table :data="tasksData" :height="600"  border :style="{'width': '100%','height':tableHeight}">
          <el-table-column type="index" label="序号" width="50"></el-table-column>
          <el-table-column prop="taskId" label="任务ID" width="100"></el-table-column>
          <el-table-column  prop="taskName" label="任务名称" width="120"></el-table-column>
          <el-table-column  prop="complete" label="办理人" width="120"></el-table-column>
          <el-table-column label="操作" fixed="right">
            <template slot-scope="scope">
              <el-button size="mini" v-if="scope.row.complete===null" @click="claimTask(scope.$index, scope.row)">领取任务</el-button>
              <el-button size="mini" v-if="scope.row.complete!==null" @click="completeTask(scope.$index, scope.row)">办理任务</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
    <el-dialog title="" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="选择办理人" :label-width="formLabelWidth">
          <el-input v-model="form.name"></el-input>
         <!-- <el-select v-model="form.name" placeholder="选择办理人">
            <el-option label="日本人" value="日本人1"></el-option>
            <el-option label="美国人" value="美国人2"></el-option>
          </el-select>-->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="claimSure()">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="" :visible.sync="dialogFormVisible1">
      <el-form :model="form2">
        <el-form-item label="申报金额" :label-width="formLabelWidth">
          <el-input-number v-model="form2.money"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="completeSure('sbje')">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="" :visible.sync="dialogFormVisible2">
      <el-form :model="form3">
        <el-form-item label="是否通过" :label-width="formLabelWidth">
          <el-radio v-model="form3.ok" label="yes">YES</el-radio>
          <el-radio v-model="form3.ok" label="no">NO</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="completeSure('sftg')">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="" :visible.sync="dialogFormVisible0">
      <el-form :model="form4">
        <el-form-item label="姓名:" :label-width="formLabelWidth">
          <div style="width: 400px">
            <el-input  v-model="form4.userName"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="请假天数:" :label-width="formLabelWidth">
          <div style="margin-top: 15px;width: 400px" >
            <el-input v-model="form4.days"></el-input>
          </div>
        </el-form-item>

        <el-form-item label="请假原因:" :label-width="formLabelWidth">
          <div style="margin-top: 15px;width:400px ">
            <el-input  v-model="form4.cause" class="input-with-select">
              <el-select v-model="form4.leaveType" slot="prepend" placeholder="请选择" style="width: 100px">
                <el-option label="事假" value="事假"></el-option>
                <el-option label="病假" value="病假"></el-option>
              </el-select>
            </el-input>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible0 = false">取 消</el-button>
        <el-button type="primary" @click="completeSure(`qjyy`)">确 定</el-button>
      </div>
    </el-dialog>



  </div>
</template>

<script>
  import axios from 'axios';
  export default {
    name: "workFlow",
    data(){
      return{
        lastTaskId:``,
        tableHeight: document.body.clientHeight - 110,
        flowData:[],
        tasksData:[],
        currTaskId:"",
        dialogFormVisible: false,
        dialogFormVisible0:false,
        dialogFormVisible1: false,
        dialogFormVisible2:false,
        form: {
          name: sessionStorage.getItem("loginUser"),
        },
        form2:{
          money: '',
        },
        form3:{
          ok: ``,
        },
        form4:{
          userName:``,
          days:0,
          leaveType:``,
          cause:``
        },
        formLabelWidth: '120px'
      }
    },
    mounted(){
     /* {
        "FM": "FM0004",
        "TEXT": "流程管理",
        "URL": "/workFlow",
        "ICON": "el-icon-document",
        "POWER": true,
        "LEAF": true
      },*/
      this.queryworkFlow();
      this.queryTasks();
    },

    methods:{
      seeFlowPng:function (index,row) {
        let pngSrc = "/wf/seePng?deployId="+row.deployId+"&key="+row.key
        this.$message({
          iconClass:'o',
          type:'success',
          dangerouslyUseHTMLString: true,
          message: `<img style="width: 500px;height: 400px" src="`+pngSrc+`"/>`,
          showClose:true,
        });
      },

      queryworkFlow:function(){
        axios.get('/wf/queryWorkFlow').then(res => {
          this.flowData = res.data.data;
        });
      },

      queryTasks:function(){
        let loginUser = sessionStorage.getItem("loginUser");
        axios.get('/wf/queryTasks?loginUser='+loginUser).then(res => {
          this.tasksData = res.data;
        });
      },
      claimTask:function (index,row) {
        this.dialogFormVisible = true;
        this.currTaskId=row.taskId;
      },

      claimSure:function(){
        this.dialogFormVisible = false;
        axios.get('/wf/claimTask?taskId='+this.currTaskId+`&userName=`+this.form.name).then(res => {
          this.$notify({
            title: '成功',
            message: '任务领取成功',
            type: 'success'
          });
          this.queryTasks();
        });
      },
      completeTask:function (index,row) {
        if(row.taskName=="申请请假" ||row.taskName=="发起请假申请" ){
          this.dialogFormVisible0 = true;
          this.form4.userName = row.complete
        }
        if(row.taskName=="班主任批准"){
          this.dialogFormVisible2 = true;
        }
        if(row.taskName=="发起报销申请"){
          this.dialogFormVisible1 = true;
        }
        if(row.taskName=="老板审批"||row.taskName=="经理审批"){
          this.dialogFormVisible2 = true;
        }

        this.currTaskId = row.taskId;
      },
      completeSure:function (val) {
        let params =null;
        if(val=='sftg'){
          params = this.form3
   /*      if(params.ok ==`2`){
           axios.get(`/wf/taskRollback?taskId=`+this.lastTaskId).then(res => {
             this.$notify({
               title: '成功',
               message: '任务回退成功',
               type: 'success'
             });
             this.queryTasks();
             this.queryworkFlow();
             this.dialogFormVisible = false;
             this.dialogFormVisible0 = false;
             this.dialogFormVisible1 = false;
             this.dialogFormVisible2 = false;
           });
           return;
         }*/
        }
        if(val=='qjyy'){
          params = this.form4
        }
        if(val=='sbje'){
          params = this.form2
        }
        params.taskId = this.currTaskId;
        axios.get(`/wf/completeTask`,{params :params}).then(res => {
          this.$notify({
            title: '成功',
            message: '任务办理完毕',
            type: 'success'
          });
          this.lastTaskId = this.currTaskId;
          this.queryTasks();
          this.queryworkFlow();
          this.dialogFormVisible = false;
          this.dialogFormVisible0 = false;
          this.dialogFormVisible1 = false;
          this.dialogFormVisible2 = false;
        });

      }
    }
  }
</script>

<style scoped>
  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>
