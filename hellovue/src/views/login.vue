<template>
  <div class="login-container">
    <el-form class="login-form" auto-complete="on" label-position="left" :model="loginForm" ref="loginForm">
      <h3 class="title">XXXXXX系统登录</h3>
      <el-form-item prop="loginName"
                    :rules="[{ required: true, message: '请输入账号!', trigger: 'blur' }]"
      >
        <el-input v-model="loginForm.loginName" type="text" auto-complete="on" placeholder="请输入登录用户名" clearable >
          <template slot="prepend">账    号：</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password"
                    :rules="[{ required: true, message: '密码不能为空!', trigger: 'change' }]"
      >
        <el-input v-model="loginForm.password" type="password" auto-complete="on" placeholder="请输入登录密码" @keyup.enter.native="handleLogin" clearable>
          <template slot="prepend">口    令：</template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width:100%;" @click="submitForm('loginForm')">
          登录
        </el-button>
      </el-form-item>
      <!--<div>-->
        <!--<div id="box" ref="mybox">-->
          <!--DEMO-->
        <!--</div>-->
      <!--</div>-->
    </el-form>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data () {
    return {
      loginForm: {
        loginName: '',
        password: '',
      }
    };
  },
  methods: {
    submitForm (formName) {
      this.login();
      /*this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$message({
            message: '恭喜你，成功消息',
            type: 'success'
          });
          this.login();
        } else {
          this.$message.error('失败消息');
          return false;
        }
      });*/
    },
    login () {
    /*  axios.get('/api/user/loginByPwd?loginName='+this.loginForm.loginName+'&password='+this.loginForm.password+'&token=1').then(res => {
        if (res.data) {
          console.log(res)
        }
      })*/
     /* axios.post('/api/user/loginByPwd', {params:{loginName:this.loginForm.loginName}})
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });*/
      axios.post('/api/user/loginByPwd',
        this.loginForm,
        { headers: {token: '99999'}},)
        .then((res)=>{
         if(res.data.meta.code === 1){
           sessionStorage.setItem("loginUser",this.loginForm.loginName)
           sessionStorage.setItem("loginUserId",res.data.data)
           this.$router.push('/');
         }
        })
        .catch((error)=>{

        })

    /*  if(this.loginForm.loginName==`admin` || this.loginForm.loginName ==`y经理`){
        if(this.loginForm.password==`123456`){
          sessionStorage.setItem("loginUser",this.loginForm.username)
          this.$router.push('/');
        }else{
          this.loginForm.password==``;
          this.$alert("密码错误!");

        }
      }else{
        this.loginForm.username=='';
        this.loginForm.password==``;
        this.$alert("账号错误!");
      }*/
    },
    handleLogin () {
      this.$router.push('/');
    }
    // init () {
    //   const self = this;
    //   this.$refs.mybox.style.color = 'red';
    //   setTimeout(() => {
    //     self.$refs.mybox.style.color = 'blue';
    //   }, 2000);
    // }
  },
  mounted () {
    // this.init();
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  $bg: #2d3a4b;
  $dark_gray: #889aa4;
  $light_gray: #eee;
  .login-container {
    position: fixed;
    height: 100%;
    width: 100%;
    background-color: $bg;
    .login-form {
      position: absolute;
      left: 0;
      right: 0;
      width: 400px;
      padding: 35px 35px 15px 35px;
      margin: 120px auto;
    }
    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;
      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }
    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
      &_login {
        font-size: 20px;
      }
    }
    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }
</style>
