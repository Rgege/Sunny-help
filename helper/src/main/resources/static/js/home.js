(document).ready(function(){
    isLogin();
});

/**
 * 判断用户是否登录  从而决定显示的具体内容
 */
function isLogin() {
    var ulBody=$('#top_ul');
    ulBody.empty();
    if(Session["username"] != null){
      var temp='<li style="position:absolute;left: 0px;top:0px"><a href="http://www.baidu.com" ><img src="../static/images/log.jpg" width="80px" height="30px"></a></li>' +
          '<li style="position:absolute;left: 1000px;top:0px"><input type="button" onclick="redToLogin()"  value="登录"></li>';
        ulBody.append(temp);
    }else {
        var temp='<li style="position:absolute;left: 0px;top:0px"><a href="http://www.baidu.com" ><img src="../static/images/log.jpg" width="80px" height="30px"></a></li>' +
            '<li style="position:absolute;left: 1000px;top:0px"><input type="button"  value="个人信息"></li>';
        ulBody.append(temp);
    }
}
/**
 * 跳转到登录页面
 */
function redToLogin() {
    window.location.href="https://www.baidu.com";
}