<template>
  <div  class="content-container" style="background: -webkit-linear-gradient(white,floralwhite,lavender )">

    <el-table :data="chlStatusData" :height="tableHeight" border :style="{'width': '100%','height': tableHeight}">
      <el-table-column type="index" label="序号" width="50"></el-table-column>
      <el-table-column prop="col1" label="字段1" width="180"></el-table-column>
      <el-table-column prop="col2" label="字段2" width="180">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.col2==='RUNNING'" type="success" size="medium" class="status-tag-radius">{{ scope.row.col2 }}</el-tag>
          <el-tag v-if="scope.row.col2==='DOWN'" type="danger" size="medium" class="status-tag-radius">{{ scope.row.col2 }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="col3" label="字段3" width="180"></el-table-column>
      <el-table-column prop="col4" label="字段4"></el-table-column>
    </el-table>
    <el-form :inline="true"  style="height: 200px;">
      <el-upload
        class="upload-demo"
        ref="upload"
        action="/tgz/export/upload"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :limit="3"
        :before-upload="beforeUpload"
        :onSuccess="successUpload"
        :on-error="onError"
        :on-exceed="exceedNo"
        :file-list="fileList"
        list-type="picture"
        :auto-upload="false"
        accept=".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.xlsx,.xls,.txt">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
        <!--<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
      </el-upload>
    </el-form>


    <router-link to="/news">热搜{{a}}</router-link>
    <router-view/>
  </div>

</template>

<script >

  export default {
    name:'org',
    data(){
      return {
        fileisOk:true,
        fileList: [],
        ruleForm: {
          fileList: []
        },
        tableHeight: document.body.clientHeight - 120,
        formInline: {
          region: '1'
        },
        chlStatusData: [{
          col1: 'LZ.OP.CH',
          col2: 'RUNNING',
          col3: 'asdsd',
          col4: '111111'
        }, {
          col1: 'LZ.OP.CH',
          col2: 'DOWN',
          col3: 'asdsd',
          col4: '111111'
        }, {
          col1: 'LZ.OP.CH',
          col2: 'RUNNING',
          col3: 'asdsd',
          col4: '111111'
        }, {
          col1: 'LZ.OP.CH',
          col2: 'RUNNING',
          col3: 'asdsd',
          col4: '111111'
        }]
      };
    },
    mounted: function() {
      this.play()
    },
    methods:{
      play:function () {
        this.a = 'wqnmlgb'
      },
      submitUpload() {
        this.$refs.upload.submit();
      },
      successUpload (response, file, fileList, $event) {
      this.$alert("文件上传成功!")
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      beforeUpload(file){
        var fileName=new Array()
        fileName =file.name.split('.');
        const extension = fileName[fileName.length-1] === 'xls'
        const extension2 =  fileName[fileName.length-1]=== 'xlsx'
        const isLt2M = file.size / 1024 / 1024 < 10
        if (!extension && !extension2) {
          this.$message({
            message: '上传模板只能是xls、xlsx格式!',
            type: 'warning'
          });
          return false;
        }
        if (!isLt2M) {
          this.$message({
            message: '上传模板大小不能超过 10MB!',
            type: 'warning'
          });
          return false;
        }
        return true;
      },
      onError(){
        this.$message({
          message: "网络繁忙!请稍后再试!",
          type: 'warning'
        });
      },
      exceedNo:function (file, fileList) {
        this.$message({
          message: '一次最多上传三个文件哦!',
          type: 'warning'
        });
      }
    }
  }
</script>

