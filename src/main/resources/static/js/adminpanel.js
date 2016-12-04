$(document).ready(function() {
    $.ajax({url: "/getPersons", success: function(result){
        for (i = 0; i < result.length; i++) {
            var opt = document.createElement('option');
            opt.value = result[i].id;
            opt.innerHTML = result[i].username;
            $("#userSelection").append(opt);
        }
    }});

    $('#userSelection').change(function() {
        var username = $( "#userSelection option:selected" ).text();
        $.ajax({
            url: "/getPerson/"+username,
            success: function (result) {
                $('#username').text(result["username"]);
                $('#name').text(result["name"]);
                $('#surname').text(result["surname"]);
                $('#emailAddress').text(result["emailAddress"]);
                $('#userBirthDate').text(timeConverter(result["userBirthDate"]));
                $('#certWorkLangs').text(result["certWorkLangs"]);
                $('#active_cert_location').text(result["active_cert_location"]);
                $('#cert_exp_date').text(timeConverter(result["cert_exp_date"]));
                $('#user_role_id').text(result["user_role_id"]);
                $('#registered_exam_id').text(result["registered_exam_id"]);
                $('#exam_register_date').text(result["exam_register_date"]);
            }
        });
    });

    var submit = $('#changePermissions');
    submit.click(function() {
        var permissionSelection = $('#permissionSelection');
        var map = userSel();
        var cookie = JSON.parse($.cookie('CSRF'));
        $.ajax({
         data: JSON.stringify(map),
         headers: {'X-CSRF-TOKEN': cookie.csrf},
         contentType: 'application/json',
         timeout: 5000,
         type: 'POST',
         url: perSel(), /// /data.get...   -> giveAdminToSomeone
                                    // data.get ,.. -> giveVerficationToSomeone

         success: function(data){
            alert("done");
         },
         error: function(errorThrown){
            console.log(errorThrown);
            alert("Error while adding question.");
         }
        })
    });
});

function perSel(){
    return $('#permissionSelection').val();
}
function userSel(){
    return $('#userSelection').val().valueOf();
}

function timeConverter(UNIX_timestamp){
  var a = new Date(UNIX_timestamp / 1000);
  var year = a.getFullYear();
  var month = a.getMonth();
  var date = a.getDate();
  var time = date + ' ' + month + ' ' + year;
  return time;
}