jQuery(document).ready(function($) {
    $('#headers').load('/html/components/header.html');
    if (isAuth().toString() === 'true'){
        if (isUnVerified().toString() === 'true'){
            displayCheckInfo();
        }
    } else{
        hideCheckInfo();
    }
//  CSRF tokeni küsimine sessiooni alguses
    $.ajax({
        type: 'GET',
        url: '/rest/getCSRF'
    }).done(function(data, textStatus, jqXHR) {
        var csrfToken = jqXHR.getResponseHeader('X-CSRF-TOKEN');
        if (csrfToken) {
            var cookie = JSON.parse($.cookie('CSRF'));
            cookie.csrf = csrfToken;
            $.cookie('CSRF', JSON.stringify(cookie));
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
        if (jqXHR.status === 401) { // HTTP Status 401: Unauthorized
            var cookie = JSON.stringify({
                method: 'GET',
                url: '/',
                csrf: jqXHR.getResponseHeader('X-CSRF-TOKEN')
            });
            $.cookie('CSRF', cookie);
        } else {
            console.log(errorThrown);
        }
    });
});

function hideCheckInfo() {
    $("#checkInfo").css("display", "none")
}
function displayCheckInfo() {
    $("#checkInfo").css("display", "block");
}
function isUnVerified() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/isUnVerified", false);
    xmlHttp.send(null);
    return (xmlHttp.responseText);
}

function isAuth() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/isAuth", false);
    xmlHttp.send(null);
    return (xmlHttp.responseText);
}





