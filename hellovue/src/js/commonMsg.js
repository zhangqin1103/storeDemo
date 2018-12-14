/**
 *公共提示框
 * @author  zhangqin
 * @
 */

/**
 * 公共confirm
 * @param event 哪个实例
 * @param options 内容,类型参数
 * @param callBack 回调函数
 */
const commonConfirm =  (event,options,callBack)=> {
  event.$confirm(options.content, "提示", {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: options.type
  }).then(() => {
    let sureOrCancel = callBack(true);
    event.$message({
      type: sureOrCancel=='success'?'success':'error',
      message: sureOrCancel=='success'?'操作成功!' :'操作失败!'
    });
  }).catch(() => {
    callBack(false)
    event.$message({
      type: 'info',
      message: '操作已取消!'
    });
  });
}
/**
 * 通知
 * @param event
 * @param options
 */
const commonNotify =(event,options)=>{
  event.$notify({
    title:"小提示",
    type:options.type||'',    //类型
    dangerouslyUseHTMLString: options.isHtml || false, //是否是html片段
    message: options.message,  //内容
    duration:options.time||4500,  //显示时间 毫秒
    position: options.position || 'top-right',// 显示的位置 右上top-right/左上top-left/右下bottom-right/左下bottom-left
    showClose: options.showClose||true // 是否显示关闭按钮
  })

}
/**
 * 顶部消息提示
 * @param event
 * @param options
 * @param callBack
 */
const commonMessage =(event,options,callBack)=>{
  event.$message({
    message: options.message,  //内容
    type:options.type||'',    //类型
    center:options.center || false,  // 文字是否居中
    dangerouslyUseHTMLString: options.isHtml || false, //是否是html片段
    duration:options.time||3000,  //显示时间 毫秒  设置为0不会自动关闭
    showClose: options.showClose||true,// 是否显示关闭按钮
    onClose:callBack
  })

}

export {
  commonConfirm,
  commonNotify,
  commonMessage
}

