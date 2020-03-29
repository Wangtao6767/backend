var Blow = {};
var webBase = getRootPath();

/**
 * 确认有效，作废，修正车牌点击按钮
 * @param id
 * @param status
 */
Blow.toChangeStatus = function(id, status) {
    if (status == 1) {
        $("#modifyLpnModalTitle").html("提醒：");
        $("#confirmContent").html(' <div class="form-horizontal">' +
            '                           <div class="form-group">' +
            '                               <label class="col-md-12 text-center">已经确认该记录有效，且已处理完成？</label>' +
            '                           </div>' +
            '                       </div>');
        $("#commit").html('<button type="button" class="btn btn-primary modal-btn-primary" onclick="Blow.changeStatus(' + id + ', ' + status + ')">确认有效</button>' +
            '<button type="button" class="btn btn-default modal-btn-default" data-dismiss="modal">取消</button>');
    } else if (status == 2) {
        $("#modifyLpnModalTitle").html("提醒：");
        $("#confirmContent").html(' <div class="form-horizontal">' +
            '                           <div class="form-group">' +
            '                               <label class="col-md-12 text-center">确认该记录无效，要进行删除操作？</label>' +
            '                           </div>' +
            '                       </div>');
        $("#commit").html('<button type="button" class="btn btn-primary modal-btn-primary text-center" onclick="Blow.changeStatus(' + id + ', ' + status + ')">确认有效</button>' +
            '<button type="button" class="btn btn-default modal-btn-default" data-dismiss="modal">取消</button>');
    } else if (status == 0) {
        $("#modifyLpnModalTitle").html("修正车牌");
        $("#confirmContent").html("");
        $("#commit").html('<button type="button" class="btn btn-primary modal-btn-primary text-center" onclick="Blow.changeLpn(' + id + ')">确认有效</button>' +
            '<button type="button" class="btn btn-default modal-btn-default" data-dismiss="modal">取消</button>');
    }

    $("#modifyLpnModal").modal("show");
};

/**
 * 提交确认有效，无效作废按钮
 * @param id
 * @param status
 */
Blow.changeStatus = function(id, status) {
    $.post(webBase + "/blow/modBlow", {
        id: id,
        status: status
    }, function(data) {
        var res = JSON.parse(data);
        if (res.isSuccess) {
            alert(res.msg);
            var pageNo = $("#pageNo").val();
            window.location.href = webBase + "/blow/list?curPage=" + pageNo;
        } else {
            alert(res.msg);
        }
    })
};

/**
 * 提交修改车牌按钮
 * @param id
 * @param lpn 车牌号
 */
Blow.changeLpn = function(id) {
    var lpn = $("#modLicensePlateNumber").val();
    if (lpn == null) {
        alert("车牌号不能为空");
        return;
    }
    $.post(webBase + "/blow/modBlow", {
        id: id,
        licensePlateNumber: lpn
    }, function(data) {
        var res = JSON.parse(data);
        if (res.isSuccess) {
            alert(res.msg);
            var pageNo = $("#pageNo").val();
            window.location.href = webBase + "/blow/list?curPage=" + pageNo;
        } else {
            alert(res.msg);
        }
    })
};

Blow.getBlowImgAndVideo = function(id) {
    $.post(webBase + "/blow/getBlowImg", {
        id: id
    }, function(data) {
        var res = JSON.parse(data);

        var $carouselIndicators = $(".carousel-indicators");
        var $carouselInner = $(".carousel-inner");
        var $carouselControl = $(".carousel-control");
        if (res.data == null) {
            $carouselIndicators.html("");
            $carouselInner.html('<div class="item active">' +
                '                       <img style="height: 200px;width: 100%;" src="">' +
                '                </div>');
            $carouselControl.hide();
        } else {
            var indicators = "";
            var inner = "";
            $.each(res.data, function(i, v) {
                if (i == 0) {
                    indicators = indicators + '<li data-target="#blowImg" data-slide-to="' + i + '" class="active"></li>';
                    inner = inner + '<div class="item active">' +
                        '               <a href="' + v.imgUrl + '" class="lightbox-2" rel="flowers" title="">' +
                        '                   <img style="height: 200px;width: 100%;" src="' + v.imgUrl + '">' +
                        '               </a>' +
                        '        </div>';
                } else {
                    indicators = indicators + '<li data-target="#blowImg" data-slide-to="' + i + '"></li>';
                    inner = inner + '<div class="item">' +
                        '               <a href="' + v.imgUrl + '" class="lightbox-2" rel="flowers" title="">' +
                        '                   <img style="height: 200px;width: 100%;" src="' + v.imgUrl + '">' +
                        '               </a>' +
                        '        </div>';
                }
            });
            $carouselIndicators.html(indicators);
            $carouselInner.html(inner);
            $carouselControl.show();
            // 图片放大
            $(".lightbox-2").lightbox({
                fitToScreen: true,
                imageClickClose: false,
                show_helper_text: false,
                show_linkback: false,
                navbarOnTop: true,
                disableNavbarLinks: true,
                fixedNavigation: false,
                strings: {}
            });
        }
    });

    $.post(webBase + "/blow/getBlowVideo", {
        id: id
    }, function(data) {
        var res = JSON.parse(data);
        if (res.data != null && res.data.videoUrl != null) {
            $("#blowVideo").attr('src', res.data.videoUrl);
        } else {
            $("#blowVideo").attr('src', '');
        }
    });
};

$(function() {

});

$(".lightbox-2").lightbox({
    fitToScreen: true,
    imageClickClose: false,
    show_helper_text: false,
    show_linkback: false,
    navbarOnTop: true,
    disableNavbarLinks: true,
    fixedNavigation: false,
    strings: {}
});

$("#query").click(function() {
    turnOverPage(1, 1);
});

function turnOverPage(pageNo) {
    var url = webBase + "/blow/list?curPage=" + pageNo;
    var licensePlateNumber = $("#licensePlateNumber").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var region = $("#region").val();
    var status = $("#status").val();
    if (licensePlateNumber != null && licensePlateNumber.length > 0) {
        url = url + "&licensePlateNumber=" + licensePlateNumber;
    }
    if (startTime != null && startTime.length > 0 && endTime != null && endTime.length > 0) {
        if (!compareTime(startTime, endTime)) {
            alert("开始时间不能大于结束时间");
            return;
        }
        url = url + "&startTime=" + startTime + "&endTime=" + endTime;
    }
    if (region != null && region.length > 0 && region != -1) {
        url = url + "&region=" + region;
    }
    if (status != null && status.length > 0 && status != -1) {
        url = url + "&status=" + status;
    }

    window.location.href = url;
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

