var Monitor = {};
var webBase = getRootPath();

/**
 * 开启关闭按钮
 * @param id
 * @param status
 */
Monitor.changeStatus = function(id, status) {
    $.post(webBase + "/monitor/modMonitor", {
        id: id,
        status: status
    }, function(data) {
        var res = JSON.parse(data);
        if (res.isSuccess) {
            alert(res.msg);
            var pageNo = $("#pageNo").val();
            window.location.href = webBase + "/monitor/list?curPage=" + pageNo;
        } else {
            alert(res.msg);
        }
    })
};

/**
 * 编辑按钮获取监测点数据
 * @param id
 */
Monitor.toModifyMonitor = function(id) {
    $.post(webBase + "/monitor/getMonitor", {
        id: id
    }, function(data) {
        $('#modMonitorModal').find(".modal-body").html(data);
        laydateBind();
    })
};

$(function() {
    /**
     * 添加时间控件按钮
     */
    $("body").on("click", ".addMonitorTime", function() {
        $(this).parent().after('<div class="form-group">' +
            '                       <label class="col-md-2 control-label"></label>' +
            '                       <div class="col-md-6 modalTime">' +
            '                           <input class="form-control laydateTime" type="text">' +
            '                           <span>&nbsp;~&nbsp;&nbsp;</span>' +
            '                           <input class="form-control laydateTime" type="text">' +
            '                       </div>' +
            '                   </div>');
        laydateBind();
    });

    /**
     * 添加监测点提交按钮
     */
    $("#commitButton").click(function() {
        var name = $("#modalMonitorName").val();
        var deviceId = $("#modalDeviceId").val();
        var region = $("#modalRegion").val();
        var address = $("#modalAddress").val();
        var monitorTime = [];
        $("#addMonitorModal .laydateTime").each(function(index, value) {
            var time = {};
            if (index % 2 == 0) {
                // 监测开始时间
                time.startTime = $(value).val();
            } else {
                // 监测结束时间
                time.endTime = $(value).val();
            }
            monitorTime.push(time);
        });
        $.post(webBase + "/monitor/addMonitor", {
            name: name,
            deviceId: deviceId,
            region: region,
            address: address,
            monitorTime: JSON.stringify(monitorTime)
        }, function(data) {
            var res = JSON.parse(data);
            if (res.isSuccess) {
                alert(res.msg);
                window.location.href = webBase + "/monitor/list"
            } else {
                alert(res.msg);
            }
        })
    });

    /**
     * 编辑监测点提交按钮
     */
    $("#commitModButton").click(function() {
        var id = $("#hiddenMonitorId").val();
        var name = $("#modModalMonitorName").val();
        var deviceId = $("#modModalDeviceId").val();
        var region = $("#modModalRegion").val();
        var address = $("#modModalAddress").val();
        var monitorTime = [];
        $("#modMonitorModal .laydateTime").each(function(index, value) {
            var time = {};
            if (index % 2 == 0) {
                // 监测开始时间
                time.startTime = $(value).val();
            } else {
                // 监测结束时间
                time.endTime = $(value).val();
            }
            monitorTime.push(time);
        });
        $.post(webBase + "/monitor/modMonitor", {
            id: id,
            name: name,
            deviceId: deviceId,
            region: region,
            address: address,
            monitorTime: JSON.stringify(monitorTime)
        }, function(data) {
            var res = JSON.parse(data);
            if (res.isSuccess) {
                alert(res.msg);
                window.location.href = webBase + "/monitor/list"
            } else {
                alert(res.msg);
            }
        })
    });
});

function turnOverPage(pageNo) {
    window.location.href = webBase + "/monitor/list?curPage=" + pageNo;
}

function jumpTo(total) {
    var pageNo = $("#pageNo").val();
    if (null == pageNo || '' == pageNo) {
        alert("请输入页数");
    }
    if (isNaN(pageNo)) {
        alert("请输入数字");
    }
    turnOverPage((pageNo > total) ? total : pageNo);
}

function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName;
}
