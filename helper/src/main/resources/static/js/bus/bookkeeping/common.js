$(document).ready(function () {
    checkLogin();
});
var token;

function checkLogin() {
    token = sessionStorage.getItem("token");
    if (token == null || token == undefined || token == "") {
        location.href = '/helper/tally/login';
    }
}

$(function () {
    $('nav#menu').mmenu({
        extensions: ['theme-black'],
        setSelected: true,
        counters: true,
        searchfield: {
            placeholder: '搜索账单'
        },
        iconbar: {
            add: true,
            size: 40,
            top: [
                '<a th:href="@{/tally/home}" href="home"><span class="fa fa-home"></span></a>'
            ],
            bottom: [
                '<a href="#/"><span class="fa fa-twitter"></span></a>',
                '<a href="#/"><span class="fa fa-facebook"></span></a>',
                '<a href="#/"><span class="fa fa-youtube"></span></a>'
            ]
        },
        sidebar: {
            collapsed: {
                use: '(min-width: 450px)',
                size: 40,
                hideNavbar: false
            },
            expanded: {
                use: '(min-width: 992px)',
                size: 35
            }
        },
        navbars: [
            {
                content: ['searchfield']
            }, {
                position: 'bottom',
                content: ['<a href="" target="_blank">login</a>']
            }
        ]
    }, {
        searchfield: {
            clear: true
        },
        navbars: {
            breadcrumbs: {
                removeFirst: true
            }
        }
    });

    $('a[href^="#/"]').click(function () {
        alert('Thank you for clicking, but that\'s a demo link');
        return false;
    })
});
// function setActiv(){
//     $('.navbar-nav').find('a').each(function () {
//         if (this.href == document.location.href || document.location.href.search(this.href) >= 0) {
//             $(this).parent().addClass('active'); // this.className = 'active';
//         }
//     });
// }


//base64转码函数
function base64(file, callback) {
    var coolFile = {};

    function readerOnload(e) {
        var base64 = btoa(e.target.result);
        coolFile.base64 = base64;
        callback(coolFile)
    };

    var reader = new FileReader();
    reader.onload = readerOnload;

    var file = file[0].files[0];

    if (file == null || file == undefined) {
        console.log("non file")
    }else {
        coolFile.filetype = file.type;
        coolFile.size = file.size;
        coolFile.filename = file.name;
        reader.readAsBinaryString(file);
    }
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

