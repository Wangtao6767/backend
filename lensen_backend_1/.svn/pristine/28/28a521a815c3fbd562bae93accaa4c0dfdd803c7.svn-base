//弹出层时间
var alertTime = 2;
//弹出层标题
var alertTitle = "温馨提示";
/**
 *
 *  弹出框架
 *  url     :  链接路径
 *  title   :  窗口标题
 *  width   :  宽
 *  height  :  高
 *
 */
var iframePop = function(url, title, width, height) {
    var index = parent.$.layer({
        type: 2, //类型 
        title: title, //标题
        area: [width, height], //宽高
        iframe: {src: url}
    });
    return index;
};
/**
 *
 *  关闭弹出层
 *
 */
var alertClose = function() {
    parent.layer.closeAll();
};
/**
 * 弹出HTML内容层
 * @param {Object} title 标题
 * @param {Object} width 宽度
 * @param {Object} height  高度
 * @param {Object} html  显示内容
 */
var alertHtml = function(title, width, height, html) {
    var index = parent.$.layer({
        type: 1, //类型 
        title: title, //标题
        area: [width, height], //宽高
        page: {html: html}
    });
    return index;
};
/**
 * 弹出指定选择器内容
 * @param {Object} title 标题
 * @param {Object} width 宽度
 * @param {Object} height 高度
 * @param {Object} id 选择器ID
 */
var alertDom = function(title, width, height, id) {
    var index = parent.$.layer({
        type: 1, //类型 
        title: title, //标题
        area: [width, height], //宽高
        page: {html: $(id).html()}
    });
    return index;
};
/**
 * 弹出指定AJAX
 * @param {Object} url 路径异步路径
 * @param {Object} width  宽度
 * @param {Object} height  高度
 * @param {Object} ok_callback 成功回调路径
 */
var alertAjax = function(url, width, height, ok_callback) {
    parent.$.layer({
        type: 1, //类型 
        title: title, //标题
        loading: {type: 2}, area: [width, height], //宽高
        page: {
            url: url, ok: function() {
                ok_callback();
            }
        }
    });
};
window.alert = function(msg, icon, callback) {
    /**
     *       用例：
     *        eq1：alert("我是一个测试弹出层");
     *        eq2：alert("我是一个测试弹出层",-1,function(){
     *               alertMsg("我要刷新页面");
     *             });
     */
    if (icon == undefined || icon == null) {
        icon = -1;
    }
    var index = 0;
    if (callback != undefined && callback != null) {
        index = parent.layer.alert(msg, icon, function() {
            callback();
        });
    } else {
        index = parent.layer.alert(msg, icon);
    }
    parent.layer.title(alertTitle, index);
};
/**
 *
 *  简单的弹出层
 *  msg       :  消息内容（必填）
 *  icon      :  图标(可选) -1没符号 1正确 2错误
 *  callback  :  回调方法(可选)
 *;
 */
var alertMsg = function(msg, icon, callback, time) {
    /**
     *       用例：
     *        eq1：alertMsg("我是一个测试弹出层");
     *        eq2：alertMsg("我是一个测试弹出层",-1,function(){
     *               alertMsg("我要刷新页面");
     *             });
     */
    if (icon == undefined || icon == null) {
        icon = -1;
    }
    if (time == undefined || time == null) {
        time = alertTime;
    }
    if (callback != undefined && callback != null) {
        parent.layer.msg(msg, time, icon, function() {
            callback();
        });
    } else {
        parent.layer.msg(msg, time, icon);
    }
};
/**
 *
 *  弹出确认弹出层
 *  msg  :  消息内容
 *  yes_callback  : 正确的回调方法 (可选)
 *  no_callback  :  错误的回调方法(可选)
 *
 */
var alertConfirm = function(msg, yes_callback, no_callback) {
    /**
     *       用例：
     *        eq1：alertConfirm("弹出确认提示层");
     *        eq2：alertConfirm("弹出确认提示层",function(){
     *               alertMsg("确认");
     *             });
     *        eq3：alertConfirm("弹出确认提示层",function(){
     *               alertMsg("确认");
     *             },function(){
     *               alertMsg("取消"); 
     *             });
     */
    if (yes_callback == undefined || yes_callback == null) {
        yes_callback = function() {
        };
    }
    if (no_callback == undefined || no_callback == null) {
        no_callback = function() {
        };
    }
    var index = parent.layer.confirm(msg, function() {
        yes_callback();
        parent.layer.close(index);
    }, function(index) {
        no_callback();
    });
    parent.layer.title(alertTitle, index);
};
/**
 *
 *  弹出加载弹出层
 *  msg  :  消息内容
 *  callback  : 回调方法 (可选) 返回true false
 */
var alertLoad = function(msg, callback) {
    /**
     *       用例：
     *        eq1：alertLoad("加载中...");
     *        eq2：alertLoad("加载中...", function () {
     *               return true;
     *             });
     */
    if (callback == undefined || callback == null) {
        parent.layer.load(msg);
    } else {
        var index = parent.layer.load(msg);
        if (callback()) {
            setTimeout(function() {
                parent.layer.close(index);
            }, alertTime * 1000);
        }
    }
};
//icon 对应数字
//parent.layer.alert(msg, -1); 没符号 
//parent.layer.alert(msg, 0);  感叹号
//parent.layer.alert(msg, 1);  正确
//parent.layer.alert(msg, 2);  错误
//parent.layer.alert(msg, 3);  禁止
//parent.layer.alert(msg, 4);  问号
//parent.layer.alert(msg, 5);  减号
//parent.layer.alert(msg, 6);  赞
//parent.layer.alert(msg, 7);  锁 
//parent.layer.alert(msg, 8);  哭脸
//parent.layer.alert(msg, 9);  笑脸
//parent.layer.alert(msg, 10); 正确
//parent.layer.alert(msg, 11); 闹钟
//parent.layer.alert(msg, 12); 消息
//parent.layer.alert(msg, 13); 米田共
//parent.layer.alert(msg, 14); 邮箱发送箭头
//parent.layer.alert(msg, 15); 鼠标下箭头
//parent.layer.alert(msg, 16); 加载