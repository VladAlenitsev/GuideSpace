$(document).on("submit", "#loginform", function(){
    event.preventDefault();

    var cookie = JSON.parse($.cookie('CSRF'));
    var data = 'username=' + $('#username').val() + '&password=' + $('#password').val();
    $.ajax({
        data: data,
        headers: {'X-CSRF-TOKEN': cookie.csrf},
        timeout: 1000,
        type: 'POST',
        url: '/login'

    }).done(function(data, textStatus, jqXHR) {
        window.location = '/';


    }).fail(function(jqXHR, textStatus, errorThrown) {
        $("#signupWarning").text("Invalid username or password");
    });
});