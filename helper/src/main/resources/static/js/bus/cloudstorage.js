$(document).ready(function () {
    checkLogin();
});
var token;

function checkLogin() {
    token = sessionStorage.getItem("cs_token");
    if (token == null || token == undefined || token == "") {
        location.href = '/helper/cs/login';
    }
}
function sendGet(url,params,callBack,token) {
    $.ajax(url+"?" + params, {
        type: "GET",
        contentType: "application/json",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        error: function (data, status, xhr) {
            sweetAlert("服务忙，请重试", "", "error");
        },
        success: function (data, status, xhr) {
            callBack(data);
        }
    });
}
function sendPost(url,params,callBack,token) {
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(params) ,
        dataType: "json",
        contentType: "application/json",
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("token", token);
        },
        success: function (data) {
            callBack(data);
        },
        error: function (XMLHttpRequest) {
            sweetAlert("服务忙，请重试", "", "error");
        }
    });
}




function getFileIcon(fileFomart) {
    //常见文档格式有：TXT、DOC、XLS、PPT、DOCX、XLSX、PPTX 等
    // 常见图片格式有：JPG、PNG、PDF、TIFF、SWF等
    // 常见视频格式有：FLV、RMVB、MP4、MVB等
    // 常见声音格式有：WMA、MP3等
    var supportFomarts = ["JPG", "PNG", "GIF", "TIFF", "SWF",
        "RAR", "ZIP",
        "PDF", "DOC", "DOCX", "DOC", "XLSX", "XLS", "TXT", "PPTX", "PPT",
        "JAVA", "CLASS", "XML", "JS", "CSS",
        "HTML", "JSP",
        "MP4", "RMVB", "FLV", "MVB", "AVI",
        "MP3", "WMA"];
    var v1 = fileFomart.toUpperCase();
    if (v1 == "JPG" || v1 == "PNG" || v1 == "GIF" || v1 == "TIFF" || v1 == "SWF") {
        return "default";
    } else if (v1 == "RAR" || v1 == "ZIP") {
        return "ZIP.png";
    } else if (v1 == "DOCX" || v1 == "DOC") {
        return "word.png";
    } else if (v1 == "XLSX" || v1 == "XLS") {
        return "excel.png";
    } else if (v1 == "PPTX" || v1 == "PPT") {//
        return "ppt.png";
    } else if (v1 == "PDF") {
        return "pdf.png";
    } else if (v1 == "TXT") {
        return "txt.png";
    } else if (v1 == "JAVA" || v1 == "CLASS" || v1 == "XML" || v1 == "JS" || v1 == "CSS") {
        return "code.png";
    } else if (v1 == "HTML" || v1 == "JSP") {
        return "web.png";
    } else if (v1 == "MP4" || v1 == "RMVB" || v1 == "FLV" || v1 == "MVB" || v1 == "AVI") {
        return "video.png";
    } else if (v1 == "MP3" || v1 == "WMA") {
        return "mp3.png";
    } else if (v1 == "exe") {
        return "exe.png";
    }

}