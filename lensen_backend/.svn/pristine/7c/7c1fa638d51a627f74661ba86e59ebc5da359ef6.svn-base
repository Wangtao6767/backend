<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>违法鸣笛后台管理系统-结果查询</title>
    <link href="${request.contextPath}/public/css/plus/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${request.contextPath}/public/css/plus/common.css" rel="stylesheet" type="text/css">
    <link href="${request.contextPath}/public/lightbox/css/lightbox.css" rel="stylesheet" type="text/css">
</head>
<body>
<#--鸣笛记录列表-->
<div class="common-view">
    <#--标题-->
    <p class="common-title">结果查询<span>&gt;</span>结果列表</p>
    <div class="comments">
        <div class="comments-search">
            <p class="common-little-title">结果列表</p>
            <div class="row">
                <div class="col-md-2 col-md-offset-3">
                    <div id="blowImg" class="carousel slide" data-ride="carousel">
                        <!-- 指示符 -->
                        <ul class="carousel-indicators">
                            <#if imgPOList??>
                                <#list imgPOList as img>
                                    <li data-target="#blowImg" data-slide-to="#{img_index}" <#if img_index == 0>class="active"</#if>></li>
                                </#list>
                            </#if>
                        </ul>

                        <!-- 轮播图片 -->
                        <div class="carousel-inner">
                            <#if imgPOList??>
                                <#list imgPOList as img>
                                    <div class="item <#if img_index == 0>active</#if>">
                                        <a href="${(img.imgUrl)!''}" class="lightbox-2" rel="flowers" title="">
                                            <img style="height: 200px;width: 100%;" src="${(img.imgUrl)!''}">
                                        </a>
                                    </div>
                                </#list>
                            <#else>
                                <img style="height: 200px;width: 100%;" src="">
                            </#if>
                        </div>

                        <#if imgPOList??>
                            <!-- 左右切换按钮 -->
                            <a class="left carousel-control" href="#blowImg" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#blowImg" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </#if>
                    </div>
                </div>
                <div class="col-md-2 col-md-offset-2">
                    <video style="height: 200px;" src="${(videoPO.videoUrl)!''}" id="blowVideo" controls="controls"></video>
                </div>
            </div>
            <div class="comments-search-item">
                <form class="form-inline">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">车牌</span>
                            <input type="text" value="${licensePlateNumber!''}" class="form-control" id="licensePlateNumber" maxlength="20">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">启始时间</span>
                            <input type="text" class="form-control" value="${startTime!''}" id="startTime"/>
                        </div>
                        <span>&nbsp;~&nbsp;&nbsp;</span><input type="text" class="form-control" value="${endTime!''}" id="endTime"/>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">区域</span>
                            <select class="form-control" id="region">
                                <option value="-1">全部</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">状态</span>
                            <select class="form-control" id="status">
                                <#if status??>
                                    <option value="-1">全部</option>
                                    <#if blowStatus??>
                                        <#list blowStatus as s>
                                            <option value="${(s.getCode())!''}" <#if status == s.getCode()>selected</#if>>
                                                ${s.getName()}
                                            </option>
                                        </#list>
                                    </#if>
                                <#else>
                                    <option value="-1">全部</option>
                                    <#if blowStatus??>
                                        <#list blowStatus as s>
                                            <option value="${(s.getCode())!''}">${s.getName()}</option>
                                        </#list>
                                    </#if>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-primary" id="query">查询</a>
                    </div>
                </form>
                <div class="data-reset">
                </div>
            </div>
        </div>
        <#--/鸣笛记录查询-->
        <#--鸣笛记录列表-->
        <div class="comments-list">
            <div style="height: 16px; font-size: 14px">共 <span style="color: red;">#{total!0}</span>
                条数据，每页展示 #{pageRows!0} 条，共 <span style="color: red">#{pages!0}</span> 页
            </div>
            <div class="tab-content">
                <div class="tab-pane fade in active">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th class="col-md-1">车牌号</th>
                            <th class="col-md-1">汽车类型</th>
                            <th class="col-md-1">违法类型</th>
                            <th class="col-md-1">行驶方向</th>
                            <th class="col-md-2">时间</th>
                            <th class="col-md-1">区域</th>
                            <th class="col-md-2">位置</th>
                            <th class="col-md-1">状态</th>
                            <th class="col-md-2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if data?? && data?size gt 0>
                            <#list data as va>
                                <tr>
                                    <td>
                                        <input style="height: auto;" name="radio" type="radio" onclick="Blow.getBlowImgAndVideo(#{(va.id)!0})" <#if va_index == 0>checked</#if>><span>${va.licensePlateNumber!''}</span>
                                    </td>
                                    <td>${va.carType!''}</td>
                                    <td>${va.illegalType!''}</td>
                                    <td>${va.travelDirection!''}</td>
                                    <td>${(va.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
                                    <td>${va.region!''}</td>
                                    <td>${va.address!''}</td>
                                    <td>
                                        <#if va.status?? && blowStatus??>
                                            <#list blowStatus as status>
                                                <#if va.status == status.getCode()>
                                                    ${status.getName()}
                                                </#if>
                                            </#list>
                                        </#if>
                                    </td>
                                    <td>
                                        <div>
                                            <#if va.status??>
                                                <#if va.status == 0>
                                                    <a onclick="Blow.toChangeStatus(#{va.id!0}, 1);">确认有效</a>
                                                    <a onclick="Blow.toChangeStatus(#{va.id!0}, 2);">无效作废</a>
                                                    <a onclick="Blow.toChangeStatus(#{va.id!0}, 0);">修正车牌</a>
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
        <#--/鸣笛记录列表-->
    </div>
</div>
<#--/鸣笛记录列表-->
<!-- 处理鸣笛记录确认模态框（Modal） -->
<div class="modal fade" id="modifyLpnModal" tabindex="-1" role="dialog" aria-labelledby="modLpnModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modifyLpnModalTitle">提醒：</h4>
            </div>
            <div class="modal-body" id="confirmContent">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-md-12 text-center">已经确认该记录有效，且已处理完成？</label>
                    </div>
                </div>
            </div>
            <div class="modal-footer" id="commit">
                <button type="button" class="btn btn-primary modal-btn-primary">确认</button>
                <button type="button" class="btn btn-default modal-btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
    <!-- /.modal-content -->
</div>
<!-- /.modal -->
<!-- 处理鸣笛记录确认模态框（Modal） -->
<script src="${request.contextPath}/public/js/script/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/public/js/script/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/public/laydate/laydate.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/public/lightbox/jquery.lightbox.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/public/js/script/common.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/scripts/blow/blow.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>