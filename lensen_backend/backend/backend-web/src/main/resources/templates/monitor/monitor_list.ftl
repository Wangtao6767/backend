<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>违法鸣笛后台管理系统-监测点列表</title>
    <link href="${request.contextPath}/public/css/plus/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${request.contextPath}/public/css/plus/common.css" rel="stylesheet" type="text/css">
</head>
<body>
<!--监测点列表-->
<div class="common-view">
    <!--标题-->
    <p class="common-title">监测点设置<span>&gt;</span>监测点列表</p>
    <div class="comments">
        <div class="comments-search">
            <p class="common-little-title">监测点列表</p>
            <div class="comments-search-item">
                <div class="data-reset">
                    <#--<@shiro.hasPermission name="addDealer">-->
                    <a href="javascript:void()" class="btn btn-info" id="addMonitor" data-toggle="modal" data-target="#addMonitorModal">
                        <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;新增监测点</a>
                    <#--</@shiro.hasPermission>-->
                </div>
            </div>
        </div>
        <!--/监测点查询-->
        <!--监测点列表-->
        <div class="comments-list">
            <div class="tab-content">
                <div class="tab-pane fade in active">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th class="col-md-1">序号</th>
                            <th class="col-md-2">监测点</th>
                            <th class="col-md-1">区域</th>
                            <th class="col-md-2">位置</th>
                            <th class="col-md-2">检测时间</th>
                            <th class="col-md-1">ID</th>
                            <th class="col-md-2">状态</th>
                            <th class="col-md-1">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if data?? && data?size gt 0>
                            <#list data as va>
                                <tr>
                                    <td>#{va.id!''}</td>
                                    <td>${va.name!''}</td>
                                    <td>${va.regionName!''}</td>
                                    <td>${va.address!''}</td>
                                    <td>
                                        <#if va.timePOs??>
                                            <#list va.timePOs as time>
                                                <p>${(time.startTime?string("HH:mm:ss"))!''}&nbsp;&nbsp;~&nbsp;
                                                    ${(time.endTime?string("HH:mm:ss"))!''}</p>
                                            </#list>
                                        </#if>
                                    </td>
                                    <td>${va.deviceId!''}</td>
                                    <td>
                                        <#if va.status?? && monitorStatus??>
                                            <#list monitorStatus as status>
                                                <#if va.status == status.getCode()>
                                                    ${status.getName()}
                                                </#if>
                                            </#list>
                                        </#if>
                                    </td>
                                    <td>
                                        <div>
                                            <#if va.status??>
                                                <#if va.status == 1>
                                                    <a onclick="Monitor.changeStatus(#{va.id!0}, 2);">关闭</a>
                                                <#elseif va.status == 2>
                                                    <a onclick="Monitor.changeStatus(#{va.id!0}, 1);">开启</a>
                                                    <a onclick="Monitor.toModifyMonitor(#{va.id!0});" data-toggle="modal" data-target="#modMonitorModal">编辑</a>
                                                </#if>
                                            </#if>
                                        </div>
                                    </td>
                                </tr>
                            </#list>
                        <#else>
                            <td colspan="8">没有符合的记录！</td>
                        </#if>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--分页器-->
            <#include "../public/pager.ftl">
            <!--/分页器-->
        </div>
        <!--/监测点列表-->
    </div>
</div>
<!--/监测点列表-->
<!-- 新增监测点模态框（Modal） -->
<div class="modal fade" id="addMonitorModal" tabindex="-1" role="dialog" aria-labelledby="addMonitorModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 50%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ResetModalLabel">添加站点</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-md-2 control-label">监测点名称</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="modalMonitorName" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">设备ID</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="modalDeviceId" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">区域</label>
                        <div class="col-md-5">
                            <select class="form-control" id="modalRegion">
                                <option value="0">请选择区域</option>
                                <#--<#if monitorStatus??>-->
                                <#--<#list monitorStatus as status>-->
                                <#--<option value="${(status.getCode())!''}">${status.getName()}</option>-->
                                <#--</#list>-->
                                <#--</#if>-->
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">详细地址</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="modalAddress" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">监测时间：</label>
                        <div class="col-md-6 modalTime">
                            <input class="form-control laydateTime" type="text">
                            <span>&nbsp;~&nbsp;&nbsp;</span>
                            <input class="form-control laydateTime" type="text">
                        </div>
                        <button class="col-md-1 btn btn-info addMonitorTime" style="float: left">添加</button>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary modal-btn-primary" id="commitButton">添加</button>
                <button type="button" class="btn btn-default modal-btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
    <!-- /.modal-content -->
</div>
<!-- /.modal -->
<!-- 新增监测点模态框（Modal） -->
<!-- 编辑监测点模态框（Modal） -->
<div class="modal fade" id="modMonitorModal" tabindex="-1" role="dialog" aria-labelledby="modMonitorModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 50%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ResetModalLabel">编辑站点</h4>
            </div>
            <div class="modal-body">
                <#include "monitor_modify_body.ftl"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary modal-btn-primary" id="commitModButton">确定</button>
                <button type="button" class="btn btn-default modal-btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
    <!-- /.modal-content -->
</div>
<!-- /.modal -->
<!-- 新增监测点模态框（Modal） -->
<script src="${request.contextPath}/public/js/script/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/public/js/script/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/public/laydate/laydate.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/public/js/script/common.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/scripts/monitor/monitor.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>