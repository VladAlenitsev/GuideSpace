$(document).ready(function() {

    $.ajax({url: "/getPersons", success: function(result){
        for (i = 0; i < result.length; i++) {
            var opt = document.createElement('option');
            opt.value = result[i].id;
            opt.innerHTML = result[i].username;
            $('#userSelection').append(opt);
        }
    }});
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