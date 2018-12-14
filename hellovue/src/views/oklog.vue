<template>
  <div  class="content-container">
    <se-com-grid   :tableTitle="tableTitle"
                   :tableData="logData"
                   :isPagination="isPagination"
                   :tableHeader="tableHeader"
                   :selectRow="selectRow"
                   :globalBtn="globalBtn">
    </se-com-grid>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: "oklog",
    data(){
      return{
        tableTitle:'系统日志',
        isPagination:true,
        logData:[],
        globalBtn:[/*{name:'boom',size:'mini'}*/],
        tableHeader:[
          {field:'id',title:'ID',minWidth:'100px'},
          {field:'url',title:'路径',minWidth:'100px'},
          {field:'ip',title:'IP地址',minWidth:'100px'},
          {field:'classMethod',title:'请求方法',minWidth:'100px'},
          {field:'isException',title:'是否异常',minWidth:'100px',format:function(val){
              return val=='1001'?'是':'否';
            }},
          {field:'exceptionCause',title:'异常原因',minWidth:'100px'},
          {field:'operation',title:'操作',minWidth:'140px',
            buttons:[
              {text:'查看详情',handle:this.detail}
            ]},
        ],
        currentPage:1,
        pagesize:20,
      }
    },
    mounted(){
      axios.get('/api/tgz/hello/getAllLogs').then(res => {
        this.logData = res.data;

      });
    },
    methods:{
      selectRow:function(rows){
        console.log(rows)
      },
      detail(index,row){
        this.$alert(row)
      }
    },
  }
</script>

<style scoped>

</style>
