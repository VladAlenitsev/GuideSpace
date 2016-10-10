
$(document).ready(function() {
        var question = $('#question');
        var submit = $('#addQuestion');
        submit.click(function() {
//            alert(question.val());
            document.getElementById("outputDiv").innerHTML = "Was added new question: " + question.val();
        });
    });

jQuery(document).ready(function($) {
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
            console.error('Houston, we have a problem...');
        }
    });

//    Navbaril login akna avamine
    $('#loginNavbar').on('click', function(event) {
        displayLoginWindow();
    });
//    Logout
    $('#logoutNavbar').on('click', function(event) {
        event.preventDefault();

        var cookie = JSON.parse($.cookie('CSRF'));
        $.ajax({
            data: {},
            headers: {
                'X-CSRF-TOKEN': cookie.csrf
            },
            timeout: 1000,
            type: 'POST',
            url: '/logout'

        }).done(function(data, textStatus, jqXHR) {
//           LOGITI VÄLJA
            window.location = '/';
        }).fail(function(jqXHR, textStatus, errorThrown) {
            console.log("logout unsuccessful");
//			VÄLJALOGIMINE EI ÕNNESTUNUD
        });
    });

    

    if (isAuth() == "true") {
        displayLogout();
    } else {
        displayLogin();
    }
});

// Login and logout buttons
function displayLogin() {
    $("#loginNavbar").css("display", "block");
    $("#logoutNavbar").css("display", "null");
}

function displayLogout() {
    $("#loginNavbar").css("display", "null");
    $("#logoutNavbar").css("display", "block");
}

// LOGIN SCREEN
function displayLoginWindow() {
    if (document.getElementById("myModal") == null) {
        $('#login').load('/html/components/login.html', function() {
            $("#myModal").modal("show");
        });
    } else {
        $("#myModal").modal("show");
    }
}







function isAuth() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/isAuth", false);
    xmlHttp.send(null);
    return (xmlHttp.responseText);
}