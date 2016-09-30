jQuery(document).ready(function($) {
    showMapView();
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

    // Map Tab
    $('#mapView').on('click', function(event) {
        hideAll();
        showMapView();
    });

    //    Statistics view toggle
    $('#statisticsView').on('click', function(event) {
        if (isAuth() == "true") {
            hideAll();
            showStatistics();
        } else {
            displayLoginWindow();
        }
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

// MAP VIEW
function showMapView() {
    var sidebarWrapper = document.getElementById("sidebar-wrapper");
    if (sidebarWrapper == null) {
        $('#sidebar').load('/html/components/sidebar.html');
    } else {
        sidebarWrapper.style.display = "block";
    }
    document.getElementById("mapid").style.display = "block";
}

function hideMapView() {
    var sidebarWrapper = document.getElementById("sidebar-wrapper");
    if (sidebarWrapper != null) {
        sidebarWrapper.style.display = "none";
    }
    document.getElementById("mapid").style.display = "none";
}


// STATISTICS
function showStatistics() {
    var statisticsWrapper = document.getElementById("statisticsWrapper");
    var statisticsSidebar = document.getElementById("statistics-sidebar");
    if (statisticsWrapper == null || statisticsSidebar == null) {
        $('#statistics').load("/html/components/statistics.html");
    } else {
        statisticsSidebar.style.display = "block";
        statisticsWrapper.style.display = "block";
    }
}

function hideStatistics() {
    var statisticsWrapper = document.getElementById("statisticsWrapper");
    var statisticsSidebar = document.getElementById("statistics-sidebar");
    if (statisticsWrapper != null) {
        statisticsWrapper.style.display = "none";
    }
    if (statisticsSidebar != null) {
        statisticsSidebar.style.display = "none";
    }
}

function hideAll() {
    hideMapView();
    hideStatistics();
}

function isAuth() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "/isAuth", false);
    xmlHttp.send(null);
    return (xmlHttp.responseText);
}