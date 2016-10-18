/**
 * Created by Leonid29 on 17.10.2016.
 */
$('#questionNavbar').on('click', function(event) {
    if (isAuth() == "true") {
        window.location = '/question';
    } else {
        displayLoginWindow();
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