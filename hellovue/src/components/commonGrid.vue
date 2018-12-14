<!--
公共表格组件
@author zhangqin  2018-11-22
@version  1.0
-->
<template>
  <el-row class="container">
    <el-row  class="header-class">
      <el-col class="header-col-class">
        <!--title-->
        <span v-if="tableTitle" class="title-class">▶ {{tableTitle}}</span>
        <!--全局按钮-->
        <el-row v-if="globalBtn.length>0" class="globalBtn-class">
          <el-button v-for="(item,index) in globalBtn"  :key="index" :icon="item.icon" :type="item.type" @click="item.handle" :size="item.size ||'mini'">{{item.text}}</el-button>
        </el-row>
      </el-col>
    </el-row>
    <!--表格-->
    <el-row>
      <el-table
        :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
        ref="tableComponent"
        size="mini"
        fit
        highlight-current-row
        :height="505"
        @row-dblclick="curentRowClick"
        @selection-change='selectRow'>
        <el-table-column v-if="isMultiple" type="selection" width="35px"></el-table-column>
        <el-table-column type="index" label="序号" align="center" fixed></el-table-column>
        <el-table-column
          v-for="(th, key) in tableHeader"
          v-if="!th.hidden"
          :key="key"
          :prop="th.field"
          :label="th.title"
          :fixed="th.fixed"
          :min-width="th.minWidth" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            <!--编辑状态列数据类型兼容 日期/数字/下拉框...-->
            <span v-if="scope.row.isEdit && !th.buttons"  >
              <!--日期-->
              <span v-if="th.dataType===`Date`">
                 <el-date-picker
                   prefix-icon=" "
                   class="input-date-class"
                   v-model="scope.row[th.field]"
                   type="date"
                   format="yyyy-MM-dd"
                   value-format="yyyy-MM-dd"
                   placeholder="选择日期"
                   size="mini">
                 </el-date-picker>
              </span>
              <span v-else-if="th.dataType===`DateTime`">
                 <el-date-picker
                   prefix-icon=" "
                   class="input-datetime-class"
                   v-model="scope.row[th.field]"
                   value-format="yyyy-MM-dd HH:mm:ss"
                   type="datetime"
                   placeholder="选择日期时间"
                   size="mini">
                 </el-date-picker>
              </span>
              <!--数字类型-->
               <span v-else-if="th.dataType===`Number`">
               <el-input-number
                 size="mini"
                 controls-position="right"
                 :precision="th.goptions.precision"
                 :min="th.goptions.min" :max="th.goptions.max"
                 :placeholder="'请输入'+th.title"
                 v-model="scope.row[th.field]">
               </el-input-number>
               </span>
              <!--下拉选项-->
                <span v-else-if="th.dataType===`Array`">
               <el-select
                 size="mini"
                 placeholder="请选择"
                 v-model="scope.row[th.field]">
                 <el-option
                   v-for="i in th.selectOptions"
                   :label="i.text" :value="i.value"
                   :key="i.value">{{i.text}}</el-option>
               </el-select>
               </span>
              <!--其他-->
              <span v-else>
               <el-input
                 size="mini"
                 @change="check"
                 :placeholder="'请输入'+th.title"
                 v-model="scope.row[th.field]">
               </el-input>
               </span>
             </span>
            <!--下拉框显示和值处理-->
            <span v-else-if="th.dataType==`Array`"  v-model="scope.row[th.field]" >
               <template  v-for="i in th.selectOptions">
               <span v-if="i.value==scope.row[th.field]">{{i.text}}</span>
               </template>
               </span>
            <!--如果有详情链接  列里需自己定义 isHtml=true 和item.handle 事件 -->
            <span v-else-if="th.isHtml" @click="th.handle(forMatterRowIndex(scope.$index),scope.row)" v-html="detailLink(scope.row[th.field])">{{scope.row[th.field]| fieldFormat(th.format)}}</span>
            <span v-else>{{scope.row[th.field]| fieldFormat(th.format)}}</span>
            <!--操作列 行内操作按钮默认size =mini -->
            <div v-if="th.buttons">
              <template v-for="(item, index) in th.buttons" >
                <el-button v-if="scope.row.isEdit && item.text=='修改'" :key="index" @click="item.handle(forMatterRowIndex(scope.$index),scope.row)" :type="item.type"  :size="item.size|| 'mini'" >保存</el-button>
                <el-button v-else-if="scope.row.isEdit && item.text=='删除'" :key="index" @click="item.handle(forMatterRowIndex(scope.$index),scope.row)" :type="item.type" :size="item.size|| 'mini'">取消</el-button>
                <el-button v-else @click="item.handle(forMatterRowIndex(scope.$index),scope.row)" :key="index" :type="item.type" :size="item.size|| 'mini'">{{item.text}}</el-button>
              </template>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <!--分页组件-->
    <el-row v-if="isPagination" class="pagination-class">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total=tableData.length>
      </el-pagination>
    </el-row>
  </el-row>
</template>
<script>
export default {
  name:'commonGrid',
  data(){
    return{
      currentPage:1,
      pageSize:20,
      name:"",
      rules: {
        name:{ type:"string",required:true,message:"必填字段",trigger:"change"},
      },
    }
  },
  props: {
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
    //翻页之后行scope.$index 索引累加
    forMatterRowIndex:function(index){
      return this.pageSize * (this.currentPage - 1)+index;
    },
    //切换页
    handleCurrentChange: function(currentPage){
      this.currentPage = currentPage;
    },
    //页条数设置
    handleSizeChange: function (size) {
      this.pageSize = size;
    },
    //行双击事件  复选框选中
    curentRowClick:function (row) {
      this.$nextTick(function () {
        this.$refs.tableComponent.toggleRowSelection(row)
      })
    },
    check:(val)=>{
      if(val.length>6){

      }
    }
  },
}
</script>

<style scoped>
  .input-date-class{
    width: 110px;
  }
  .input-datetime-class{
    width: 155px;
  }
  .header-class{
    height: 40px;
    background-color: white;
  }
  .header-col-class{
    margin-top: 6px;
  }
  .title-class{
    color: #3a8ee6;
    font-family: 等线;
    margin-left: 10px;
  }
  .globalBtn-class{
    float: right;
    margin-right: 17px;
  }

  .pagination-class{
    float: right;
  }
</style>
<style>
  .el-tabs--border-card>.el-tabs__content {
    padding: 5px;
  }
  .el-input--prefix .el-input__inner {
    padding-left: 10px;
  }
  .el-date-picker {
    width: 290px;
  }
  .el-picker-panel{
    line-height:5px;
  }
  .el-picker-panel__content {
    position: relative;
    margin: 1px;
  }
  .el-date-picker__header {
    margin: 5px;
  }
  a{
    text-decoration:none;
  }

  /*  .el-date-table td {
      width:20px
    }*/
  .el-picker-panel__footer {
    text-align: center;
  }
  /*.el-input--mini .el-input__inner {
    height: 20px;
  }*/
</style>
