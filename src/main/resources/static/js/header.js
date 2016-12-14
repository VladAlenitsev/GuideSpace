$('#questionNavbar').on('click', function (event) {
    if (isAuth() == "true") {
        window.location = '/question';
    } else {
        displayLoginWindow();
    }
});
$('#adminNavbar').on('click', function (event) {
    if (isAuth() == "true") {
        window.location = '/adminpanel';
    } else {
        displayLoginWindow();
    }
});

$('#examResults').on('click', function (event) {
    if (isAuth() == "true") {
        window.location = '/examresults';
    } else {
        displayLoginWindow();
    }
});
$('#examregNavbar').on('click', function (event) {
    if (isAuth() == "true") {
        window.location = '/examreg';
    } else {
        displayLoginWindow();
    }
});
$('#examNavbar').on('click', function (event) {
    if (isAuth() == "true") {
        $.ajax({
            url: "/examDone", success: function (result) {
                if (result == true) {
                    window.location = '/exam';
                }
                else {
                    $.notify("You have already done that exam!", "error");
                }
            }
        });
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
$('#loginNavbar').on('click', function (event) {
    displayLoginWindow();
});
$('#logoutNavbar').on('click', function (event) {
    event.preventDefault();
    var cookie = JSON.parse($.cookie('CSRF'));
    $.ajax({
        data: {},
        headers: {'X-CSRF-TOKEN': cookie.csrf},
        timeout: 1000,
        type: 'POST',
        url: '/logout'
    }).done(function (data, textStatus, jqXHR) {
        window.location = '/';
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log("logout unsuccessful");
    });
});
if (isAuth() == "true") {
    displayLogout();
    if (isVerified() == "true") {
        displayExam();
        hideAdminPanel();
        hideQuestion();
        hideManageExamination();
        hideQuestionEdit();
        displayResults();
    }
    else if (isQuestionAdder() == "true") {
        displayQuestion();
        displayQuestionEdit();
        hideAdminPanel();
        hideResults();
        hideExam();
        hideManageExamination();
    }
    else if (isAdmin() == "true") {
        displayResults();
        displayExam();
        displayQuestion();
        displayQuestionEdit();
        displayAdminPanel();
        displayManageExamination();
    }
    else if (isUnVerified() == "true") {
        hideAdminPanel();
        hideQuestion();
        hideExam();
        hideManageExamination();
        hideQuestionEdit();
        hideResults();
    }
}
else {
    displayLogin();
    hideAdminPanel();
    hideQuestion();
    hideExam();
    hideQuestionEdit();
    hideManageExamination();
    hideResults();
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
function hideAdminPanel() {
    $("#adminNavbar").css("display", "none")
}
function hideExam() {
    $("#examNavbar").css("display", "none")
}
function hideResults() {
    $("#examResults").css("display", "none")
}
function hideManageExamination() {
    $("#examregNavbar").css("display", "none")
}
function displayManageExamination() {
    $("#examregNavbar").css("display", "block")
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
function displayResults() {
    $("#examResults").css("display", "block");
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
function displayLoginWindow() {
    if (document.getElementById("myModal") == null) {
        $('#login').load('/html/components/login.html', function () {
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