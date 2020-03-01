var SUCCESS = 200;
var ERROR = 404;
$(function () {
    showUserInfo();
    $('#logoutButton').click(logout);
    $('#submitButton').click(submitButton);
});

function submitButton() {
    var url = "/propsal/addPropsal";
    var corn = $("#classfiy").val()+"类"+$("#numberNum").val()+"号";
    var title = "春天花花居委会第"+$("#titleNum").val()+"届第"+$("#countNum").val()+"次会议第"+$("#numbeCount").val()+"号提案";
    var type=$('input:radio[name="radio01"]:checked').val();
    var reason = $("#reasonInput").val();
    var author = $("#authorName").val();
    var grade = $("#gradeNum").val();
    var party = $("#partyName").val();
    var blogUserId = $("#blogUserId").val();
    var mailNo = $("#mailNo").val();
    var phoneNum = $("#phoneNum").val();
    var job = $("#jobName").val();
    var eMail = $("#email").val();
    var address = $("#address").val();
    var theme= $("#themeName").val();
    var content =$('#edit')[0].childNodes[1].innerHTML;
    var contacto = $("#contacto").val();
    var company = $("#company").val();
    var telphone = $("#telphone").val();
    var unionAnthor = $("#unionAm").val();
    var data = {
        address:address,
        author:author,
        blogUserId:blogUserId,
        company:company,
        contacto:contacto,
        content:content,
        corn:corn,
        eMail:eMail,
        grade: grade,
        job:job,
        mailNo:mailNo,
        party:party,
        phoneNum:phoneNum,
        reason: reason,
        telphone:telphone,
        theme:theme,
        title:title,
        type:type,
        unionAnthor:unionAnthor
    };

    $.ajax({
        url : url,
        headers: {
            'token':cookie('token')
        },
        data : JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        type : "POST",
        dataType : "json",
        beforeSend:function(XMLHttpRequest){
            loadingFlag= layer.msg('正在提交中，请稍候……', { icon: 16, shade: 0.01,shadeClose:false,time:60000 });
        },
        success : function(result) {
            layer.close(loadingFlag);
            if (result.code == SUCCESS) {
                layer.msg("提交成功",{icon:1});
                setInterval(function(){
                    location.href = "book01.html";
                },2000);
            } else {
                if (result.code == ERROR)
                {
                    layer.msg(result.msg,{icon:0})
                }
            }
        },
        error : function(e) {
            layer.msg("当前服务器异常,请稍后重试",{icon:2});
        }
    })


}

function logout() {
    var data = {};
    $.ajax({
        url : "/role/logout",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "GET",
        dataType : "json",
        beforeSend:function(XMLHttpRequest){
            loadingFlag= layer.msg('正在退出中，请稍候……', { icon: 16, shade: 0.01,shadeClose:false,time:60000 });
        },
        success : function(result) {
            layer.close(loadingFlag);
            if (result.code == SUCCESS) {
                delCookie("token");
                delCookie("id");
                delCookie("roleType");
                layer.msg("已退出",{icon:1});
                setInterval(function(){
                    location.href = "login.html";
                },2000);
            } else {
                if (result.code == ERROR)
                {
                    layer.msg(result.msg,{icon:0})
                }
            }
        },
        error : function(e) {
            layer.msg("当前服务器异常,请稍后重试",{icon:2});
        }
    })
}


function showUserInfo() {
    var id = cookie("id");
    var data = {userId:id};
    $.ajax({
        url : "/role/detail",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "GET",
        dataType : "json",
        beforeSend:function(XMLHttpRequest){
            loadingFlag = layer.msg('正在加载中，请稍候……', { icon: 16, shade: 0.01,shadeClose:false,time:60000 });
        },
        success : function(result) {
            layer.close(loadingFlag);
            if (result.code == SUCCESS) {
                var info = result.data;
                $("#infoName").html(info.nickName);
                $("#blogUserId").val(cookie('id'));
                //$("#image").attr("src", "head_image/" + info.imageName);
            } else {
                if (result.code == ERROR) {
                    layer.msg(result.msg)
                }
            }
        },
        error : function(e) {
            if(cookie('token') == ""){
                layer.msg("登陆已过期!",{icon:2});
                setInterval(function(){
                    location.href = "login.html";
                },2000);
            }else{
                layer.msg("当前服务器异常,请稍后重试",{icon:2});
                setInterval(function(){
                    location.href = "login.html";
                },2000);
            }
        }
    })
}




function timetrans(date) {
    var date = new Date(date * 1000);
    var Y = date.getFullYear() + "-";
    var M = (date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date
            .getMonth() + 1)
        + "-";
    var D = (date.getDate() < 10 ? "0" + (date.getDate()) : date.getDate())
        + " ";
    var h = (date.getHours() < 10 ? "0" + date.getHours() : date.getHours())
        + ":";
    var m = (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
            .getMinutes())
        + ":";
    var s = (date.getSeconds() < 10 ? "0" + date.getSeconds() : date
        .getSeconds());
    return Y + M + D + h + m + s
};