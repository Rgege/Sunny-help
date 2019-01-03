var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket'); //构建一个SockJS对象
    stompClient = Stomp.over(socket); //用Stomp将SockJS进行协议封装
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        /**  订阅了/topic/greetings 发送的消息,这里雨在控制器的 convertAndSendToUser 定义的地址保持一致, 
         *  这里多用了一个/user,并且这个user 是必须的,使用user 才会发送消息到指定的用户。 
         *  */
        stompClient.subscribe('/queue/notifications', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    /*消息发送*/
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage() {
    stompClient.send("/app/singleChart", {}, JSON.stringify({'message': $("#mysend").val()}));
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
    $("#mybutton").click(function () {
        sendMessage();
    });
});


function connect(context)
{
    var msg=null;
    context.state.stomp = Stomp.over(new SockJS("/ws/endpointChat"));
    context.state.stomp.connect({}, function (frame) {
        context.state.stomp.subscribe("/user/queue/chat", function (message) {msg = JSON.parse(message.body)});
    });
    var oldMsg = window.localStorage.getItem(context.state.user.username + "#" + msg.from);
    if (oldMsg == null) {
        oldMsg = [];
        oldMsg.push(msg);
        window.localStorage.setItem(context.state.user.username + "#" + msg.from, JSON.stringify(oldMsg))
    } else {
        var oldMsgJson = JSON.parse(oldMsg);
        oldMsgJson.push(msg);
        window.localStorage.setItem(context.state.user.username + "#" + msg.from, JSON.stringify(oldMsgJson))
    }
    if (msg.from != context.state.currentFriend.username) {
        context.commit("addValue2DotMap", "isDot#" + context.state.user.username + "#" + msg.from);
    }
}//更新msgList var oldMsg2 = window.localStorage.getItem(context.state.user.username + "#" + context.state.currentFriend.username); if (oldMsg2 == null) { context.commit('updateMsgList', []); } else { context.commit('updateMsgList', JSON.parse(oldMsg2)); } }); }, failedMsg=> { }); }

