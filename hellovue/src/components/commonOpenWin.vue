<template>
  <el-row class="common-win">
    <el-dialog title="表单" :visible.sync="currVisible" center width="300px">
 <!--     <el-form :model="currentRow" >
        <template v-for="(val, key, index) in currentRow">
          <el-form-item :label="key+`:`" >
            <span class="el-detail-class" >{{val}}</span>
          </el-form-item>
        </template>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="success" @click="currVisible = false" >确定</el-button>
        <el-button type="warning" @click="currVisible = false" >取消</el-button>
      </div>-->
      <el-form>
        <fieldset>
          <legend>个人信息</legend>
          <el-form-item label="身高:" >
            <el-input type="text" />
          </el-form-item>
          <el-form-item label="体重:" >
            <el-input type="text" />
          </el-form-item>
        </fieldset>
        <fieldset>
          <legend>学业信息</legend>
          <el-form-item label="学校:" >
            <el-input type="text" />
          </el-form-item>
          <el-form-item label="班级:" >
            <el-input type="text" />
          </el-form-item>
        </fieldset>
      </el-form>

    </el-dialog>
    <el-dialog title="表格" :visible.sync="isGrid" width="60%">
      <se-com-grid
        :tableTitle="tableTitle"
        :tableData="tableData"
        :isPagination="isPagination"
        :tableHeader="tableHeader"
        :isMultiple="isMultiple"
        :globalBtn="globalBtn" >
      </se-com-grid>
    </el-dialog>

  </el-row>
</template>

<script>
export default {
  name: "commonOpenWin",
  data(){
    return{
      currVisible: false,
      cisGrid:false,
      orderForm:{
        orderNo:'',
        proDetail:"",
        tradeStatus:'',
        oderAmount:'',
        placeAddress:'',
        createTime:''
      }
    }
  },
  watch: {
    //监听父组件的值
    visible(newValue, oldValue) {
      this.currVisible = newValue;
    },
    // 本组件的值 来控制父组件的值
    currVisible(newValue,oldValue){
      if(newValue==false){
        this.$emit('changex',newValue)
      }
    }
  },
  props:{
    visible:{
      type:Boolean,
      default:false
    },
    isGrid:{
      type:Boolean,
      default:false
    },
    currentRow:{
      type:Object,
      default:{}
    },
    tableTitle:{
      type:String,
      default:null
    },
    isMultiple:{
      type:Boolean,
      default:false
    },
    isPagination:{
      type:Boolean,
      default:true,
    },
    tableData: {
      type: Array,
      default: function () {
        return []
      }
    },
    tableHeader: {
      type: Array,
      default: function () {
        return []
      }
    },
    selectRow:{
      type:Function,
      default:function () {}
    },
    globalBtn:{
      type:Array,
      default: function () {
        return []
      }
    }
  },
  methods:{
  }
}
</script>

<style scoped>
fieldset{
  border-radius: 4px;
  border: 1px solid #dcdfe6;
}

</style>
