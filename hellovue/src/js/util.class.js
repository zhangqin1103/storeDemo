export default class Utils {
  static checkPhone (str) {
    let pattern = /1[34578]\d{9}/;
    return pattern.test(str);
  }

  static checkInteger (str) {
    let pattern = /[1-9]\d*/;
    return pattern.test(str);
  }

  static checkPersonId (str) {
    let pattern = /\d{17}[\d|x]|\d{15}/;
    return pattern.test(str);
  }

  static checkEmail (str) {
    let pattern = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
    return pattern.test(str);
  }

  static getUUID () {
    function S4 () {
      return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }

    return (S4() + S4() + '-' + S4() + '-' + S4() + '-' + S4() + '-' + S4() +
      S4() + S4());
  }

  static checkPrice (str) {
    let pattern = /^(\d|[1-9]\d*){1}(\.\d{1,2})?$/;
    return pattern.test(str);
  }
  // 并集 a[1,2,3], b [1,2,5,4] 结果 a[1,2,3], b [1,2,5,4]
  static getArrUnionSet (arr1, arr2) {
    let a = new Set(arr1);
    let b = new Set(arr2);
    let unionSet = new Set([...a, ...b]);
    return Array.from(unionSet);
  }

  // 差集 a[1,2,3], b [1,2,5,4]  结果 [5, 4]
  static getArrDiffSet (arr1, arr2) {
    let a = new Set(arr1);
    let b = new Set(arr2);
    let differenceABSet = new Set([...b].filter(x => !a.has(x)));
    return Array.from(differenceABSet);
  }

  // 非空字符串
  static isNotEmptyStr (str) {
    return str !== undefined && str !== null &&
      ((str + '').replace(/(^\s+)|(\s+$)/g, '')) !== '';
  }

  // 空字符串
  static isEmptyStr (str) {
    return !this.isNotEmptyStr(str);
  }

  // 非空对象
  static isNotEmptyObject (obj) {
    for (let t in obj) {
      return true;
    }
    return false;
  }

  // 空对象
  static isEmptyObject (obj) {
    return !this.isNotEmptyObject(obj);
  }

  // 获取时间撮
  static getTimesTamp (time = null) {
    if (time) {
      return new Date(time).getTime();
    }
    return new Date().getTime();
  }

  // 将时间搓转换为时间格式 如 2017-11-27
  static formatDate (dateTime, symbol = '-') {
    let time = new Date(dateTime);
    let y = time.getFullYear();
    let m = time.getMonth() + 1;
    let d = time.getDate();
    return y + symbol + this.addZera(m) + symbol + this.addZera(d);
  }
  static getLocalDate(format) {
    let date = new Date();
    let y = date.getFullYear();
    let m = date.getMonth() + 1;
    let d = date.getDate();
    let h = date.getHours();
    let min = date.getMinutes();
    let s = date.getSeconds();
    if(h<10){
      h  = '0'+ h
    }
    if(min<10){
      min  = '0'+ min
    }
    if(s<10){
      s  = '0'+ s
    }
    if (format === "yyyy-MM-dd") {
      return y + "-" + m + "-" + d;
    } else if (format === "hh:mm:ss") {
      return h + ":" + min + ":" + s;
    } else {
      return y + "-" + m + "-" + d + " " + h + ":" + min + ":" + s;
    }
  }

  static addZera (str) {
    return Number(str) < 10 ? '0' + str : str;
  }

  static deepClone (obj) {
    if (obj === null || typeof obj !== 'object') {
      return obj;
    }
    if (obj instanceof Array) {
      let copy = [];
      for (let i = 0; i < obj.length; i++) {
        copy.push(obj[i]);
      }
      return copy;
    }
    if (obj instanceof Object) {
      let copy = {};
      for (let key in obj) { // 递归
        if (obj.hasOwnProperty(key)) {
          copy[key] = this.deepClone(obj[key]);
        }
      }
      return copy;
    }
  }

  static getMoney (str) {
    if (this.isEmptyStr(str)) {
      return 0;
    }
    return '￥' + (str / 100).toFixed(2);
  }

  static getQueryString (name) {
    let reg = new RegExp('(^|\\?|&)' + name + '=([^&]*)(\\s|&|$)', 'i');
    if (reg.test(window.location.href)) {
      let val = unescape(RegExp.$2.replace(/\+/g, ''));
      this.setSessionStorage(name, val);
      return unescape(val);
    }
    return this.getSessionStorage(name);
  }

  static setSessionStorage (key, val) {
    sessionStorage.setItem(key, val);
  }

  static getSessionStorage (key) {
    return sessionStorage.getItem(key);
  }

  // 设置过期缓存
  static setLocalStorage (key, val, expireTime) {
    localStorage.setItem(key, JSON.stringify({data: val, expireTime: expireTime}));
  }

  static getLocalStorage (key) {
    let data = localStorage.getItem(key);
    if (!data) {
      return null;
    }
    let obj = JSON.parse(data);
    obj.expireTime = obj.expireTime ? obj.expireTime : 0;
    console.log(new Date().getTime() - obj.expireTime);
    if (new Date().getTime() - obj.expireTime > 0) {
      return null;
    } else {
      return obj.data;
    }
  }

  static browserType () {
    let userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
    let isOpera = false;
    if (userAgent.indexOf('Edge') > -1) {
      return 'Edge';
    }
    if (userAgent.indexOf('.NET') > -1) {
      return 'IE';
    }
    if (userAgent.indexOf('Opera') > -1 || userAgent.indexOf('OPR') > -1) {
      isOpera = true;
      return 'Opera';
    }
    // 判断是否Opera浏览器
    if (userAgent.indexOf('Firefox') > -1) {
      return 'FF';
    } // 判断是否Firefox浏览器
    if (userAgent.indexOf('Chrome') > -1) {
      return 'Chrome';
    }
    if (userAgent.indexOf('Safari') > -1) {
      return 'Safari';
    } // 判断是否Safari浏览器
    if (userAgent.indexOf('compatible') > -1 && userAgent.indexOf('MSIE') > -1 && !isOpera) {
      return 'IE';
    }// 判断是否IE浏览器
  }

  // 获取当前时间
  static getCurrentSecondTime () {
    return new Date().getTime();
  }

  // 判断是否为字符串
  static isString (str) {
    if (typeof (str) === 'string') {
      return true;
    }
    return false;
  }
  // 是否是数组
  static isArray (arr) {
    return Array.isArray(arr);
  }


  /**
   * 确认框
   * @param event
   * @param options
   * @param callBack
   */
  static Confirm (event,options,callBack) {
    event.$confirm(options.content, "提示", {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: options.type||'warning'
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
  static Notify(event,options){
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
   * 提示
   * @param event
   * @param options
   * @param callBack
   */
  static  Msg (event,options,callBack){
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

}
