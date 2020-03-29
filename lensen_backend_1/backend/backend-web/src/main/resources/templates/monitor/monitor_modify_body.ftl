<input type="hidden" id="hiddenMonitorId">
<div class="form-horizontal">
    <div class="form-group">
        <label class="col-md-2 control-label">监测点名称</label>
        <div class="col-md-5">
            <input type="text" class="form-control" id="modModalMonitorName" maxlength="50" value="${(monitorPO.name)!''}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">设备ID</label>
        <div class="col-md-5">
            <input type="text" class="form-control" id="modModalDeviceId" maxlength="50" value="${(monitorPO.deviceId)!''}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">区域</label>
        <div class="col-md-5">
            <select class="form-control" id="modModalRegion">
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
            <input type="text" class="form-control" id="modModalAddress" maxlength="50" value="${(monitorPO.address)!''}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">监测时间：</label>
        <div class="col-md-6 modalTime">
            <input class="form-control laydateTime" type="text" value="">
            <span>&nbsp;~&nbsp;&nbsp;</span>
            <input class="form-control laydateTime" type="text">
        </div>
        <button class="col-md-1 btn btn-info addMonitorTime" style="float: left">添加</button>
    </div>
</div>
