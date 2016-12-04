$(document).ready(function() {

    $('#classifadd').click(function(){

        $.ajax({url: "/addQuests", success: function(result){
            console.log("loaded");
           }
        });
    });

    $('#submitsearch').click(function() {
        var searchText = document.getElementById("searchtext").value;
        var cookie = JSON.parse($.cookie('CSRF'));
        $.ajax({
            data: searchText,
            headers: {'X-CSRF-TOKEN': cookie.csrf},
            contentType: 'application/json',
            timeout: 5000,
            type: 'POST',
            url: "/findAllQuestions",
            success: function(result) {
                 for (var key in result) {
                   if (result.hasOwnProperty(key)) {
                     var opt = document.createElement('option');
                     opt.value = result[key];
                     opt.innerHTML = key;
                     $('#questionSelection').append(opt);
                   }
                 }
            },
            error:function(errorThrown){
                console.log(errorThrown);
                $.notify("Error while searching for questions", "error");
            }
        });
    });

    $('#edit').click(function(){
        var cookie = JSON.parse($.cookie('CSRF'));
        $.ajax({
        data: $('#questionSelection').value,
        headers: {'X-CSRF-TOKEN': cookie.csrf},
        contentType: 'application/json',
        timeout: 5000,
        type: 'GET',
        url: "/findQuestionById",
        success: function(result) {
            console.log(result);
            $.notify("Changes saved.", "success");
        },
        error: function(errorThrown){
            console.log(errorThrown);
            $.notify("Couldn't save changes.", "error");
        }
        });
    });
});