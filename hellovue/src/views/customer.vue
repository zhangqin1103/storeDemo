<template>
  <div id="cust" style="background-color:paleturquoise">
    <marquee class="gd"  direction="right" bgcolor="pink" onMouseOut="this.start()" onMouseOver="this.stop()">{{msg}}</marquee>
  </div>

</template>

<script>
  export default {
    name:'customer',
    data(){
      return {msg:'want you'}
    }
  }
</script>
<style>
  .gd{
    width: 160px;
    hight:50px;
    font-size: 30px;

  }


</style>
