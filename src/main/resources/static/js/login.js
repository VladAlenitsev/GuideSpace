//  Login vormi esitamine
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
        window.location = '/loggedin';


    }).fail(function(jqXHR, textStatus, errorThrown) {
        $("#signupWarning").text("Invalid username or password");
    });
});

//  Registreerimine
$(document).on("submit", "#registerForm", function(){
    event.preventDefault();

    var cookie = JSON.parse($.cookie('CSRF'));
    var data = 'username=' + $('#usernameRegister').val() + '&password=' + $('#passwordRegister').val() + '&email=' + $('#emailRegister').val();
    $.ajax({
        data: data,
        headers: {'X-CSRF-TOKEN': cookie.csrf},
        timeout: 3000,
        type: 'POST',
        url: '/register'

    }).done(function(data, textStatus, jqXHR) {
        window.location = '/registered';
    }).fail(function(jqXHR, textStatus, errorThrown) {

        if(jqXHR.status = 406){
            var data = JSON.parse(jqXHR.responseText);
            var errorMessage = data.message;
            if(data.exception == "com.guidespace.service.DuplicateUsernameException"){
                $("#usernameWarning").text("Username already in use");
            }
            else if(data.exception == "com.guidespace.service.DuplicateEmailException"){
                $("#emailWarning").text("Email address already in use");
            }
        }
    });
});

 var password = document.getElementById("passwordRegister");
        var confirm_password = document.getElementById("passwordRegister2");
        function validatePassword(){
          if(password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
          } else {
            confirm_password.setCustomValidity('');
          }
        }
            password.onchange = validatePassword;
            confirm_password.onkeyup = validatePassword;