var token;
var gatewayApiTestUrl="http://localhost:8080/a/mbr/gatewayTest/gatewayApiTest";
var gatewayCircuitBreakerUrl="http://localhost:8080/a/mbr/gatewayTest/gatewayCircuitBreaker";
var gatewayChannelSwitchUrl="http://localhost:8080/a/mbr/gatewayTest/gatewayChannelSwitch";
var queryNodesUrl="http://localhost:8080/a/mbr/gatewayTest/getNodes";
var authorizationUrl="http://localhost:8080/a/mbr/gatewayTest/authorization"
//获取业态下全部节点
function getNodes(yetai) {

    var BGNodes = ["10.18.8.51:8001", "10.18.8.52:8001", "10.18.8.53:8001", "10.18.8.54:8001", "10.18.8.55:8001", "10.18.8.56:8001",
        "10.18.8.51:8002", "10.18.8.52:8002", "10.18.8.53:8002", "10.18.8.54:8002", "10.18.8.55:8002", "10.18.8.56:8002",
        "10.18.8.51:8003", "10.18.8.52:8003", "10.18.8.53:8003", "10.18.8.54:8003", "10.18.8.55:8003", "10.18.8.56:8003",
        "10.18.8.51:8004", "10.18.8.52:8004", "10.18.8.53:8004", "10.18.8.54:8004", "10.18.8.55:8004", "10.18.8.56:8004"];

    var LHNodes = ["10.0.30.43:8002", "10.0.30.44:8002", "10.0.30.45:8002", "10.0.30.46:8002", "10.0.30.47:8002", "10.0.30.48:8002",
        "10.0.30.79:8002", "10.0.30.80:8002", "10.0.30.81:8002", "10.0.30.41:8003", "10.0.30.42:8003", "10.0.30.43:8003",
        "10.0.30.44:8003", "10.0.30.45:8003", "10.0.30.46:8003", "10.0.30.47:8003", "10.0.30.48:8003", "10.0.30.79:8003",
        "10.0.30.80:8003", "10.0.30.81:8003", "10.0.30.41:8004", "10.0.30.42:8004", "10.0.30.43:8004", "10.0.30.44:8004",
        "10.0.30.45:8004", "10.0.30.46:8004", "10.0.30.47:8004", "10.0.30.48:8004", "10.0.30.79:8004", "10.0.30.80:8004",
        "10.0.30.81:8004", "10.0.30.41:8001", "10.0.30.42:8001", "10.0.30.43:8001", "10.0.30.44:8001", "10.0.30.45:8001",
        "10.0.30.46:8001", "10.0.30.47:8001", "10.0.30.48:8001", "10.0.30.79:8001", "10.0.30.80:8001", "10.0.30.81:8001",
        "10.0.30.41:8002", "10.0.30.42:8002"];


    var CLDNodes = ["10.201.36.121:7210", "10.201.36.131:7210", "10.201.36.132:7210", "10.201.36.122:7210", "10.201.36.123:7210",
        "10.201.36.124:7210", "10.201.36.125:7210", "10.201.36.126:7210", "10.201.36.127:7210", "10.201.36.128:7210",
        "10.201.36.129:7210", "10.201.36.130:7210"];

    if (yetai == "1107") {//百股
        return BGNodes;
    } else if (yetai == "1108") {//联华
        return LHNodes;
    } else if (yetai == "2020") {//云前置
        return CLDNodes;
    }
}

function doTest() {
    $("#resultShow").empty();
    var yetai=$("#yetai option:selected").val();
    var interfaceType=$("#testInterface option:selected").val();
    var Nodes = getNodes(yetai);
    var spanStart = $('<span class="label label-default"></span></br>');
    if(interfaceType==1){
        spanStart.innerHTML="会员识别测试结果:";
    }else if(interfaceType==2){
        spanStart.innerHTML="折扣计算测试结果:";
    }
    $("#resultShow").append(spanStart);

    for(var i = 0,len=Nodes.length; i < len; i++) {
        $.ajax(gatewayApiTestUrl, {
            type: "POST",
            xhrFields: {
                withCredentials: true,
                useDefaultXhrHeader: false
            },
            data: {
                "token": token,
                "yetai": yetai,
                "nodes" : Nodes[i],
                "interfaceType" : interfaceType
            },
            dataType: 'json',
            crossDomain: true,
            error : function(data, status, xhr) {
                var spanSysError = $('<span class="label label-danger">调用OSP接口错误</span></br>');
                $("#resultShow").append(spanSysError);
            },
            success: function(data, status, xhr) {
                var msg=data.obj;
                console.log(data.resCode+"=="+msg);
                if (data.resCode == "00100000") {
                    var spanSuccess=$('<span class="label label-success">'+msg+'</span></br>');
                    $("#resultShow").append(spanSuccess);
                } else if (data.resCode == "00100003"){
                    sweetAlert("没有权限","","error");
                } else  {
                    var spanError=$('<span class="label label-danger">'+msg+'</span></br>');
                    $("#resultShow").append(spanError);
                }
            }
        });
    }
    var spanEnd=$('<span class="label label-default">完毕！</span>');
    $("#resultShow").append(spanEnd);
}