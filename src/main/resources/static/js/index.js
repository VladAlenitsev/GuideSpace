
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


    // Navbari laadimine
    $('#headers').load('/html/components/header.html');


//    $.ajax({url: "/addQuests", success: function(result){
//        console.log("loaded");
//        }
//    });


});








