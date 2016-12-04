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
$('#adminNavbar').on('click', function(event) {
    if (isAuth() == "true") {
        window.location = '/adminpanel';
    } else {
        displayLoginWindow();
    }
});
$('#examregNavbar').on('click', function(event) {
    if (isAuth() == "true") {
        window.location = '/examreg';
    } else {
        displayLoginWindow();
    }
});
$('#examNavbar').on('click', function(event) {
    if (isAuth() == "true") {
        window.location = '/exam';
    } else {
        displayLoginWindow();
    }
});
$('#questioneditNavbar').on('click', function(event) {
    if (isAuth() == "true") {
        window.location = '/questionedit';
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


if(isAuth() == "true"){
    displayLogout();
    if (isVerified() == "true") {
        displayExam();
        hideAdminPanel();
        hideQuestion();
        hideManageExamination();
        hideQuestionEdit();
    }
    else if (isQuestionAdder() == "true"){
        displayQuestion();
        displayQuestionEdit();
        hideAdminPanel();
        hideExam();
        hideManageExamination();
    }
    else if (isAdmin() == "true") {
        displayExam();
        displayQuestion();
        displayQuestionEdit();
        displayAdminPanel();
        displayManageExamination();

    }
    else if (isUnVerified() == "true"){
        hideAdminPanel();
        hideQuestion();
        hideExam();
        hideManageExamination();
        hideQuestionEdit();
    }
}

else {
    displayLogin();
    hideAdminPanel();
    hideQuestion();
    hideExam();
    hideManageExamination();
}


function isUnVerified() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/isUnVerified", false);
    xmlHttp.send(null);
    return (xmlHttp.responseText);
}

function isVerified() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/isVerified", false);
    xmlHttp.send(null);
    return (xmlHttp.responseText);
}

function isQuestionAdder() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/isQuestionAdder", false);
    xmlHttp.send(null);
    return (xmlHttp.responseText);
}

function hideQuestion() {
    $("#questionNavbar").css("display", "none");
}

function hideQuestionEdit() {
    $("#questioneditNavbar").css("display", "none");
}

function hideAdminPanel(){
    $("#adminNavbar").css("display","none")
}

function hideExam(){
    $("#examNavbar").css("display","none")
}

function hideManageExamination(){
    $("#examregNavbar").css("display","none")
}

function displayManageExamination(){
    $("#examregNavbar").css("display","block")
}

function displayQuestion() {
    $("#questionNavbar").css("display", "block");
}

function displayQuestionEdit() {
    $("#questioneditNavbar").css("display", "block");
}

function displayExam() {
     $("#examNavbar").css("display", "block");
 }
function displayAdminPanel() {
    $("#adminNavbar").css("display", "block");
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


function isAdmin() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/isAdmin", false);
    xmlHttp.send(null);
    return (xmlHttp.responseText);
}