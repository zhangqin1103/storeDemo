<template>
  <div>
    <!--dialog提示框-->
    <div class="el-dialog__body">
      <a href="#" v-show="showBtn" @click="showDialog=true">{{dialogMsg}}</a>
      <el-dialog
        title="提示"
        :visible.sync="showDialog"
        width="30%"
        :before-close="handleClose">
        <div>{{dialogMsg}}</div>
        <span slot="footer" class="dialog-footer">
    <el-button @click="openOrclose(false)">取 消</el-button>
    <el-button type="primary" @click="openOrclose(false)">确 定</el-button>
  </span>
      </el-dialog>
    </div>

    <!--tooltip 文字提示框-->
    <div class="tooltipDiv">
      <el-tooltip placement="right" effect="light">
        <div class="el-tooltip" slot="content">{{tooltipMsg}}</div>
        <span>{{tooltipMsg}}</span>
      </el-tooltip>
    </div>
    <!--popover 提示框-->
    <div class="el-popover__reference">
      <el-popover
        placement="top-start"
        title="标题"
        width="200"
        trigger="hover"
        content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">
        <el-button slot="reference">hover 激活</el-button>
      </el-popover>

      <el-popover
        placement="right"
        width="160"
        v-model="currVisible">
        <p>确定删除吗？</p>
        <div style="text-align: right; margin: 0">
          <el-button size="mini" type="text" @click="currVisible = false">取消</el-button>
          <el-button type="text" size="mini" @click="currVisible = false">确定</el-button>
        </div>
        <!--<el-button slot="reference">删除</el-button>-->
      </el-popover>
    </div>

  </div>
</template>

<script>
  export default {
    name: "commonTooltip",
    props: {
      dialogMsg: String,
      showBtn: Boolean,
      tooltipMsg: String,
      visible: Boolean
    },
    data() {
      return {
        showDialog: false,
        currVisible: false
      }
    },
    computed: {

    },
    watch: {
        visible(newValue, oldValue) {
          this.currVisible = newValue;
      },
        currVisible(newValue,oldValue){
          if(newValue==false){
            this.$emit('success',newValue)
          }
      }
    },
    methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      openOrclose: function (flag) {
        this.showDialog = flag;
      }
    },
    computed: {
      // 计算属性

    }
  }
</script>

<style scoped>
  .el-tooltip{
    /* width: 200px;*/
  }
</style>
