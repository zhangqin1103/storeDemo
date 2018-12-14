<template>
  <el-alert
    class="conmon-alert"
    v-show="show"
    center
    :description="description"
    :title="message"
    :type="type"
    :closable="closable"
    :show-icon="showIcon"
    @close="callBack()">
  </el-alert>
</template>

<script>
  export default {
    name: "commonAlertBox",
    data(){
      return{
        show:false
      }
    },
    props:{
      message:{     // 消息
        type:String,
        default:``
      },
      description:{   //详细描述
        type:String,
        default:''
      },
      type:{    //样式  默认 info (success/error/warning)
        type:String,
        default:`info`
      },
      closable:{    //是否显示关闭按钮
        type:Boolean,
        default:true
      },
      showIcon:{   //是否显示样式类型图标
        type:Boolean,
        default:true
      },
      callBack:{   //关闭后回调函数
        type:Function,
      }

    },
  }
</script>

<style scoped>
  .conmon-alert{
    margin-left: 40%;
    width:250px
  }

</style>
