<!--公共表格使用样例-->
<template>
  <el-row>
    <se-com-grid ref="commonTable"
                 :tableTitle="tableTitle"
                 :isPagination="isPagination"
                 :tableData="tableData"
                 :tableHeader="tableHeader"
                 :isMultiple="isMultiple"
                 :selectRow="selectRow"
                 :globalBtn="globalBtn" >
    </se-com-grid>
    <com-win  :visible="visible" @changex="changex" :currentRow="currentRow"  :tableTitle="tableTitle"
              :isPagination="isPagination"
              :tableData="tableData"
              :tableHeader="tableHeader"
              :isMultiple="isMultiple"
              :selectRow="selectRow"
              :globalBtn="globalBtn"></com-win>
  </el-row>
</template>

<script>
import axios from 'axios';
  //import commonGrid from  '@/components/commonGrid';
  export default {
    name: "errlog",
    data(){
      return{
        visible:false,
        tableTitle:'系统配置', //title
        isMultiple:true,  // 是否支持多选
        selectRowArr:[], //选中的所有行
        isPagination:true, //是否分页
        tableData:[],  //表格数据
        tableHeader: [],  //表头数据
        globalBtn: [  //全局按钮  icon 图标  type :primary/success/warning/info/error 默认info
          { text: '新增',icon:'el-icon-circle-plus',type:'primary ',size:`mini`, handle: this.addRow},
          { text: '批量删除',icon:'el-icon-remove',type:'warning ', size:`mini`, handle: this.delRows},
          { text: '导出',icon:'el-icon-download',type:'',size:`mini`, handle:this.exportGrid}
        ],
        currentRow:{}
      }
    },
    mounted(){
      //获取表格配置数据
      this.getHeader();
      this.tableData = this.getDatas();
    },
    methods:{
      getHeader:function(){
        axios.get('./../../static/tableConfig.json').then(res => {
           if(res.data){
           this.tableHeader = res.data
           //如果有操作列
              this.tableHeader.push(
                { field: 'operation', title: '操作', fixed: 'right',minWidth: '140px',
                buttons: [
                  { text: '修改',handle: this.saveOrUpdClick},
                  { text: '删除',handle: this.canselOrDelClick}
                ]})
           for(let item of this.tableHeader) {
             if(item.field=='name'){
               item.isHtml =true
               item.handle =this.openWin

             }
           }
         }
        });
   /*     this.tableHeader =  [    // 表头数据
          { field: 'time', title: '时间',minWidth: '140px',dataType:`Date` },
          { field: 'name', title: '名称', minWidth: '120px',dataType:`String`},
          { field: 'card_no', title: '卡号', minWidth: "150px",dataType:`Number`,goptions:{precision:3,min:0.000,max:99.999} },//其他参数封装
          { field: 'colType', title: '类型',dataType:`String`,minWidth:"150px",dataType:`Array`},
          { field: 'userName', title: '姓名',dataType:`String`, minWidth: '100px' ,format:function (val) {
              return "88888"
            }},
          { field: 'mediaType', title: '付款方式',  minWidth: "150px",dataType:`Array`},
          { field: 'xx', title: 'xx',minWidth: '180px',dataType:`DateTime` },
          { field: 'yy', title: 'yy', minWidth: '100px',dataType:`String`},
        ]*/
      },
      //模拟数据
      getDatas:function(){
        return  [
          {id:1, time:'2018-11-11',card_no:93003885,name:'wxz',colType:'1001',userName:'小王',mediaType:'1002'},
          {id:2, time:'2018-05-08',card_no:79005499,name:'sabi',colType:'1002',userName:'沙比',mediaType:'1003'},
          {id:3, time:'2018-02-21',card_no:78794565,name:'naica',colType:'1003',userName:'奶茶',mediaType:'1001'},
          {id:4, time:'2018-03-09',card_no:21324489,name:'daxiong',colType:'1003',userName:'大熊',mediaType:'1003'},
          {id:5, time:'2018-07-14',card_no:786112,name:'chenan',colType:'1002',userName:'陈安',mediaType:'1001'},
          {id:6, time:'2018-11-03',card_no:971218484,name:'xiaoli',colType:'1002',userName:'小李',mediaType:'1002'},
          {id:7, time:'2018-02-28',card_no:945123800,name:'ahuang',colType:'1001',userName:'阿黄',mediaType:'1002'},
          {id:8, time:'2018-08-15',card_no:13491206,name:'maju',colType:'1003',userName:'马菊',mediaType:'1003'},
          {id:9, time:'2018-09-10',card_no:1095564585,name:'haoyue',colType:'1002',userName:'皓月',mediaType:'1003'},
          {id:10, time:'2018-10-01',card_no:8564732,name:'mingyue',colType:'1003',userName:'明月',mediaType:'1002'},
          {id:11, time:'2018-11-27',card_no:94138472,name:'qfeng',colType:'1001',userName:'清风',mediaType:'1001'},
          {id:12, time:'2018-08-30',card_no:2654847205,name:'xzhu',colType:'1002',userName:'虚竹',mediaType:'1002'},
          {id:13, time:'2018-05-05',card_no:365498447,name:'qfeng',colType:'1001',userName:'乔峰',mediaType:'1003'},
          {id:14, time:'2018-02-10',card_no:55437012,name:'yanzi',colType:'1003',userName:'燕子',mediaType:'1002'},
        ]
      },

      //确定或取消保存
      save:function(index,row){
        this.$util.Confirm(this,{content:"确定要保存吗?",type:'warning'},(sureOrcancel)=> {
          if (sureOrcancel) {
            this.$alert(row);
            this.$set(row, 'isEdit', false)
            return 'success';
          } else {
            this.tableData= this.getDatas();
            this.$set(row, 'isEdit', false)
          }
        })

      },
      //修改和保存按钮事件
      saveOrUpdClick:function (index,row) {
        //点击编辑之前先判断是否存在已经处于编辑状态的行
        for (let i of this.tableData) {
          if (i.isEdit && i.id != row.id) {
            this.$message.warning("请先保存当前编辑项");
            return;
          }
        }
        //row.isEdit为true代表代表已经是编辑状态  此时按钮为保存
        if(row.isEdit){
          this.save(index,row);
        }else{
          this.$set(row, 'isEdit', true)
        }
      },
      //删除和取消按钮
      canselOrDelClick:function(index,row){
        let _this = this;
        //取消编辑状态
        if(row.isEdit){
          _this.tableData= this.getDatas();
          _this.$set(row, 'isEdit', false);
          return;
        }else{
          this.$util.Confirm(_this,{content:"确定要删除吗?", type: 'warning'}, (param)=> {
              if(param){
                _this.tableData.splice(index,1);
                return 'success'
              }else{
                _this.$refs.commonTable.$refs.tableComponent.toggleRowSelection(row,false)
              }
            }
          )
        }
      },
      //新增一行
      addRow:function () {
        let newRow={id:``, time:'',card_no: '',name:'',colType:'',userName:'',mediaType:''}
        newRow.isEdit=true
        this.tableData.push(newRow)

      },
      //选中的行
      selectRow:function (rows) {
        this.selectRowArr = rows;
      },
      //批量删除
      delRows:function(){
        this.$util.Notify(this,{message:'今天星期三',time:2500,position:'top-right'})
        this.$util.Confirm(this, {
            content: '确定要删除选中的数据吗?',
            type: 'waring',
          }, (param)=> {
            if(param){
              let parray = this.tableData;
              let carray = this.selectRowArr;
              for(var i=0;i<parray.length;i++){
                for(var j=0;j<carray.length;j++){
                  if(parray[i]==carray[j]){
                    parray.splice(i,1);
                    i--;
                  }
                }
              }
              //   this.tableData = this.getDatas();
              return 'success'
            }
          }
        )
        /* this.$myAlert({message:"你好啊",description:"我吃西红柿0000",type:'success',
           callBack:function () {
             console.log("*****")
           }})*/

      },
      exportGrid:function(){
        this.$util.Msg(this,{message:"<a href='http://www.baidu.com'>卧槽,即将导出数据</a>",isHtml:true,type:'warning',center:true},()=>{
          console.log('关闭后的回调')
        })
      },

      changex(res){
        this.visible = res;
      },
      openWin(index,row){
        this.visible = true
        this.currentRow = row;
      }
    },
   /* components:{commonGrid}*/
  }
</script>



<style scoped>

</style>
