<!--公共提示util 测试页-->
<template>
  <div class="rootDiv">
    <el-button @click="confirmTest1" type="success" >confirm1</el-button>
    <el-button @click="confirmTest2" type="warning">confirm2</el-button>
    <el-button @click="confirmTest3" type="danger">confirm3</el-button>
    <el-button @click="confirmTest4" type="primary">confirm4</el-button>
    <br><br>
    <el-button @click="notify1" type="success">notify1</el-button>
    <el-button @click="notify2" type="warning">notify2</el-button>
    <el-button @click="notify3" type="danger">notify3</el-button>
    <el-button @click="notify4" type="primary">notify4</el-button>
    <el-button @click="notify5" type="info">notify5</el-button>
    <br><br>
    <el-button @click="message1" type="success">message1</el-button>
    <el-button @click="message2" type="warning">message2</el-button>
    <el-button @click="message3" type="danger">message3</el-button>
    <el-button @click="message4" type="primary">message4</el-button>
    <el-button @click="message5" type="primary">message5</el-button>

  </div>
</template>

<script>
export default {
  name: "indexVue",
  data(){
    return{}
  },

  methods:{
    confirmTest1:function () {
      this.$util.Confirm(this,{content:"确定语句?",type:'success'},(sureOrCancel)=>{
        if(sureOrCancel){
          return 'success'
        }
      })
    },
    confirmTest2:function () {
      this.$util.Confirm(this,{content:"确定语句?",type:'warning'},(sureOrCancel)=>{
        if(sureOrCancel){
          return 'success'
        }
      })
    },
    confirmTest3:function () {
      this.$util.Confirm(this,{content:"确定语句?",type:'error'},(sureOrCancel)=>{
        if(sureOrCancel){
          return 'success'
        }
      })
    },
    confirmTest4:function () {
      this.$util.Confirm(this,{content:"确定语句?",type:'info'},(sureOrCancel)=>{
        if(sureOrCancel){
          return 'success'
        }
      })
    },
    notify1:function () {
      this.$util.Notify(this,{message:"右上通知",type:'success',time:3000,position:'top-right',showClose:true})
    },
    notify2:function () {
      this.$util.Notify(this,{message:"右下通知",type:'warning',time:3000,position:'bottom-right',showClose:true})

    },
    notify3:function () {
      this.$util.Notify(this,{message:"左上通知",type:'error',time:3000,position:'top-left',showClose:true})

    },
    notify4:function () {
      this.$util.Notify(this,{message:"左下通知",type:'info',time:3000,position:'bottom-left',showClose:true})

    },
    notify5:function () {
      this.$util.Notify(this,{message:"<a href='http://www.baidu.com'>这是a标签</a>",type:'info', isHtml:true,time:3000,showClose:true})

    },
    message1:function () {
      this.$util.Msg(this,{message:"成功消息",type:'success',showClose:true,time:3000})
    },
    message2:function () {
      this.$util.Msg(this,{message:"警告消息",type:'warning',showClose:true,time:3000})

    },
    message3:function () {
      this.$util.Msg(this,{message:"失败消息",type:'error',showClose:true,time:3000})

    },
    message4:function () {
      this.$util.Msg(this,{message:"普通消息",type:'info',showClose:true,time:3000})

    },
    message5:function () {
        this.$util.Msg(this,{message:"<h1>这是h1标签</h1>",type:'',showClose:true,isHtml:true,center:true})

    },


  }
}
</script>

<style scoped>

</style>
