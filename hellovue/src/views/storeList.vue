<!--公共表格使用样例-->
<template>
  <el-row>
    <se-com-grid ref="commonTable"
                 :tableTitle="tableTitle"
                 :tableData="tableData"
                 :tableHeader="tableHeader"
                 :isMultiple="isMultiple"
                 :selectRow="selectRow"
                 :globalBtn="globalBtn" >
    </se-com-grid>
    <el-dialog title="订单详情" :visible.sync="isOrder" width="30%" >
      <el-form :model="orderForm" >
        <el-form-item label="订单编号:" >
          <span class="el-detail-class" >{{orderForm.orderNo}}</span>
        </el-form-item>
        <el-form-item label="商品明细:" >
          <span class="el-detail-class" >{{orderForm.proDetail}}</span>
        </el-form-item>
        <el-form-item label="购买人:" >
          <span class="el-detail-class"  >{{orderForm.buyerName}}</span>
        </el-form-item>
        <el-form-item label="支付状态:" >
          <span  class="el-detail-class" >{{orderForm.payStatus}}</span>
        </el-form-item>
        <el-form-item label="交易状态:" >
          <span class="el-detail-class" >{{orderForm.tradeStatus}}</span>
        </el-form-item>
        <el-form-item label="订单金额:" >
          <span style="color: red;font-size: 15px"><b>{{orderForm.oderAmount}} 元</b></span>
        </el-form-item>
        <el-form-item label="收货地址:" >
          <span class="el-detail-class" >{{orderForm.placeAddress}}</span>
        </el-form-item>
        <el-form-item label="下单时间:" >
          <span class="el-detail-class" >{{orderForm.createTime}}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="success" @click="pay">确认支付</el-button>
        <el-button type="warning" @click="cancelOrder">取消订单</el-button>
      </div>
    </el-dialog>

    <el-dialog  :visible.sync="orderList" width="60%">
      <se-com-grid  ref="orderTable"
                    :tableTitle="`订单列表`"
                    :tableData="orderData"
                    :isPagination="false"
                    :tableHeader="oerderHeader"
                    :isMultiple="isMultiple"
                    :globalBtn="[]" >
      </se-com-grid>
    </el-dialog>

    <el-dialog  title="加入购物车" :visible.sync="isAddBuyCar" width="30%">
      <el-form :model="proAddBuyCar">
        <el-form-item  label="样例图:" >
          <img style="width: 200px;height: 120px" :src="proAddBuyCar.imgUrl">
        </el-form-item>
        <el-form-item  label="商品名称:" >
          <span>{{proAddBuyCar.productName}}</span>
        </el-form-item>
        <el-form-item  label="型号/版本:" >
          <el-select  v-model="proAddBuyCar.proSpu.model" placeholder="请选择">
            <el-option
              v-for="item in modelOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item  label="尺码:" >
          <el-select class="buyCar-class" v-model="proAddBuyCar.proSpu.size" placeholder="请选择">
            <el-option
              v-for="item in sizeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item  label="颜色:" >
          <el-select class="buyCar-class" v-model="proAddBuyCar.proSpu.color" placeholder="请选择">
            <el-option
              v-for="item in colorOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item  label="购买数量:" >
          <el-input-number style="margin-left: 5px" controls-position="right" :min="1" v-model="proAddBuyCar.proNum" ></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="success" @click="sureAdd">确定加入</el-button>
        <el-button type="warning" @click="isAddBuyCar =false" >取消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="" :visible.sync="isMyBuyCar" width="80%">
      <se-com-grid
        :tableTitle="`我的购物车`"
        :tableData="buyCarDatas"
        :isPagination="false"
        :tableHeader="buyCarHeader"
        :isMultiple="isMultiple"
        :globalBtn="[]" >
      </se-com-grid>
    </el-dialog>
  </el-row>
</template>

<script>
import axios from 'axios';
export default {
  name: "storeList",
  data(){
    return{
      userInfo:{
        id:'',
        realName:'',
        loginName:'',
        phoneNo:'',
        email:'',
        address:''
      },
      tableTitle:'商品列表', //title
      isAddBuyCar:false,// 购物车dialog
      isMyBuyCar:false, // 购物车列表
      isMultiple:true,  // 是否支持多选
      selectRowArr:[], //选中的所有行
      tableData:[],  //表格数据
      tableHeader: [],  //表头数据
      proAddBuyCar:{
        userId:sessionStorage.getItem("loginUserId"),
        userName:sessionStorage.getItem("loginUser"),
        productId:'',
        imgUrl:'',
        productName:'',
        status:'待付款',
        proNum:0,
        proSpu:{model:'',size:'',color:''}
      },
      colorOptions:[],
      sizeOptions:[],
      modelOptions:[],
      isOrder:false,
      globalBtn: [  //全局按钮  icon 图标  type :primary/success/warning/info/error 默认info
        { text: '我的购物车',icon:'el-icon-circle-plus',type:'primary ',size:`mini`, handle: this.myBuyCar},
        { text: '我的订单',icon:'el-icon-remove',type:'success ', size:`mini`, handle: this.myOrder},
      ],
      orderList:false,
      orderData:[],
      oerderHeader:[
        { "field": "id", "title": "ID","minWidth": "120px","dataType":"String","hidden":true},
        { "field": "orderNo", "title": "订单编号","minWidth": "120px","dataType":"String"},
        { "field": "buyerId", "title": "买家id", "minWidth": "100px","dataType":"String","hidden":true},
        { "field": "tradeStatus", "title": "交易状态","dataType":"String","minWidth":"120px"},
        { "field": "oderAmount", "title": "订单金额","dataType":"Number", "minWidth": "120px" },
        { "field": 'operation', title: '操作', fixed: 'right',minWidth: '80px',
          buttons: [
            { text: '查看',type:'text',handle: this.orderDetail},
          ]}
      ],
      orderForm:{
        orderNo:'',
        buyerId:'',
        buyerName:"",
        oderAmount:"",
        proDetail:"",
        tradeStatus:'',
        payStatus:'',
        placeAddress:'',
        createTime:'',
      },
      buyCarDatas:[],
      buyCarHeader:[{ "field": "id", "title": "ID","minWidth": "120px","dataType":"String","hidden":true},
        { "field": "userId", "title": "用户id","minWidth": "120px","dataType":"String","hidden":true},
        { "field": "productId", "title": "商品id", "minWidth": "100px","dataType":"String","hidden":true},
        { "field": "productName", "title": "商品名称", "minWidth": "100px","dataType":"String"},
        { "field": "proSpu", "title": "商品属性","dataType":"String","minWidth":"120px"},
        { "field": "proNum", "title": "购买数量", "minWidth": "100px","dataType":"Number",},
        { "field": "status", "title": "状态","dataType":"Number", "minWidth": "120px" },
        { "field": 'operation', title: '操作', fixed: 'right',minWidth: '80px',
          buttons:[
            { text: '确认下单',type:'text',handle: this.toOrder},
            { text: '移除',type:'text',handle:this.delFormBuyCar},
          ]}],

      yfModel:[{ value: '鸭绒',label: '鸭绒'}, {value: '鹅绒',label: '鹅绒' }],
      DlModel:[{ value: 'air',label: 'mac-air'}, {value: 'pro',label: 'mac-pro' }],
      yfSize:[{ value: 'M',label: 'M'}, {value: 'L',label: 'L' },{value: 'XL',label: 'XL' }],
      yfColors:[{ value: '藏青',label: '藏青'}, {value: '米白',label: '米白' },{value: '黑色',label: '黑色' }],
      DlSize:[{ value: '13寸',label: '13寸'}, {value: '14寸',label: '14寸' },],
      DlColors:[{ value: '月光白',label: '月光白'}, {value: '暗夜灰',label: '暗夜灰' }]

    }
  },
  mounted(){
    //获取表格配置数据
    this.getHeader();
    this.getDatas();
    // 获取登陆用户信息
    axios.get('/api/pro/getUserInfo',
      {
        headers: {token: '99999'},
        params: {loginUser:sessionStorage.getItem('loginUser')},
      }).then((res)=>{
      if(res.data.meta.code === 1){
        this.userInfo.id = res.data.data.id;
        this.userInfo.realName = res.data.data.realName;
        this.userInfo.address = res.data.data.address
      }else{
        this.$util.Msg(this,{message:"<span style='color: red'>"+res.data.meta.message+"</span>",isHtml:true})
      }
    }).catch((error)=>{ })
  },
  methods:{
    getHeader:function(){
      axios.get('./../../static/storeConfig.json').then(res => {
        if(res.data){
          this.tableHeader = res.data
          //如果有操作列
          this.tableHeader.push(
            { field: 'operation', title: '操作', fixed: 'right',minWidth: '140px',
              buttons: [
                { text: '加入购物车',handle: this.addBuyCar},
                { text: '购买',handle: this.buy}
              ]})
          for(let item of this.tableHeader) {
            if(item.field=='proName'){
              item.isHtml =true
              item.handle =(index,row)=>{
                console.log(index)
              }
            }

          }
        }
      });

    },
    // 获取商品数据
    getDatas:function(){
      axios.get('/api/pro/getAllPro',
        {
          headers: {token: '99999'},
          params: {pageIndex: 1, pageSize: 20},
        }).then((res)=>{
        if(res.data.meta.code === 1){
          this.tableData =res.data.data;
        }
      }).catch((error)=>{ })
    },

    //购买
    buy:function(index,row){
      this.$util.Confirm(this,{content:"确定要买吗?", type: 'warning'}, (param)=> {
        if(param){
          this.$refs.commonTable.$refs.tableComponent.toggleRowSelection(row,true)
          return 'success'
        }else{
          this.$refs.commonTable.$refs.tableComponent.toggleRowSelection(row,false)
        }
      })
    },
    //购物车
    myBuyCar:function () {
      axios.get('/api/pro/getBuyCars',
        {
          headers: {token: '99999'},
          params: {userId:this.userInfo.id,pageIndex: 1, pageSize: 20},
        }).then((res)=>{
        if(res.data.meta.code === 1){
          this.buyCarDatas =res.data.data;
          this.isMyBuyCar =true
        }
      }).catch((error)=>{ })
    },
    //我的订单
    myOrder(){
      axios.get('/api/pro/getOrders',
        {
          headers: {token: '99999'},
          params: {buyerId:this.userInfo.id,pageIndex: 1, pageSize: 20},
        }).then((res)=>{
        if(res.data.meta.code === 1){
          this.orderData =res.data.data;
          this.orderList =true
        }
      }).catch((error)=>{ })
    },
    //选中的行
    selectRow:function (rows) {
      this.selectRowArr = rows;
    },
    selectRow1:function (rows) {

    },
    // 加入购物车
    addBuyCar:function(index,row){
      this.proAddBuyCar.imgUrl = row.imgUrl
      this.proAddBuyCar.proSpu={size:'',color:'',model:''}
      if(row.proType==='服装'){
        this.colorOptions = this.yfColors
        this.sizeOptions = this.yfSize
        this.modelOptions = this.yfModel
      }else {
        this.colorOptions = this.DlColors
        this.sizeOptions = this.DlSize
        this.modelOptions =this.DlModel
      }
      this.isAddBuyCar = true;
      this.proAddBuyCar.productId = row.id;
      this.proAddBuyCar.productName=row.proName;

      // this.$util.Msg(this,{message:'加入购物车成功',time:2500})
      /*this.$util.Confirm(this, {content: '确定要加入吗?', type: 'waring',}, (param)=> {
        if(param){
          return 'success'
        }
      })*/
    },
    pay(){
      this.isOrder = true
    },
    cancelOrder(){

    },
    sureAdd(){
      this.isAddBuyCar = false;
      let tempSpu = this.proAddBuyCar.proSpu;
      this.proAddBuyCar.proSpu = JSON.stringify(tempSpu);
      axios.post('/api/pro/addToBuyCar',
        this.proAddBuyCar,
        { headers: {token: '99999'}},)
        .then((res)=>{
          if(res.data.meta.code === 1){
            this.$util.Msg(this,{message:'加入购物车成功',type:'success',time:2500})
          }
        })
        .catch((error)=>{

        })
    },
    //购物车下单
    toOrder(index,row){
      //获取当前商品单价
      axios.get('/api/pro/getProPrice',
        {
          headers: {token: '99999'},
          params: {proId:row.productId,proSpu:row.proSpu},
        }).then((res)=>{
        if(res.data.meta.code === 1){
          //生成订单信息
          this.orderForm.oderAmount =(res.data.data) * (row.proNum);
          this.orderForm.orderNo   = this.$util.getUUID();
          this.orderForm.proDetail = row.productName+"/"+row.proSpu + "/数量" + row.proNum;
          this.orderForm.payStatus = "待支付";
          this.orderForm.placeAddress =this.userInfo.address;
          this.orderForm.tradeStatus="待付款";
          this.orderForm.createTime =  this.$util.getLocalDate();
          this.orderForm.buyerId = this.userInfo.id
          this.orderForm.buyerName = this.userInfo.realName;
          this.isOrder =true
          axios.post('/api/pro/addToOrder',
            this.orderForm,
            { headers: {token: '99999'}},)
            .then((res)=>{
              if(res.data.meta.code === 1){
                this.$util.Msg(this,{message:'已生成订单',type:'success',time:2500})
              }
            })
            .catch((error)=>{

            })
        }
      }).catch((error)=>{ })
    },
    delFormBuyCar(index,row){
      this.$util.Confirm(this,{content:"确定要移除吗?",type:"warning"},(param)=>{
        if(param){
          axios.delete('/api/pro/delFormBuyCar/'+row.id,
            {headers: {token: '99999'}}).then((res)=>{
            if(res.data.meta.code === 1){
              this.myBuyCar();
              this.$util.Msg(this,{message:'已成功从购物车移除',type:'success',time:2500})
            }
          }).catch((error)=>{ })
        }
      })
    },
    /**
     * 单个订单明细
     * @param index
     * @param row
     */
    orderDetail(index,row){
      axios.get('/api/pro/getOrderDetail/'+row.id,
        {headers: {token: '99999'}}
      ).then((res)=>{
        if(res.data.meta.code === 1){
          debugger
          this.orderForm =res.data.data;
          this.orderForm.buyerName = this.userInfo.realName
          this.isOrder = true;
        }
      }).catch((error)=>{ })
    }

  }
}
</script>



<style scoped>

  .buyCar-class{
    margin-left: 33px ;
  }
</style>
