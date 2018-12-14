<template>
  <div style="background-color: royalblue;color: white">
  <marquee  direction="right"  onMouseOut="this.start()" onMouseOver="this.stop()">{{msg}}</marquee>
  </div>
</template>

<script>
  export default {
    name:'org',
    data(){
      return {
        msg:'今天是晴天'
      }
    }
  }
</script>
