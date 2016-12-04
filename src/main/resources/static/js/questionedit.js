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
    $('#questionSelection').change(function() {
        var optionVal = document.getElementById("questionSelection").value;
        $.ajax({
            url: "/findQuestionWithAnswers/"+optionVal,
            success: function(result) {
                console.log(result);
                var hmKey = result.keys().keys()[0];
                var hmValue = result.keys()[hmKey];
                var hmValue2 = result.keys().values()[0];
                console.log(hmKey);
                console.log(hmValue);
                console.log(hmValue2);
                $('#question').value(hmKey);
                $('#name').text(result["name"]);
                $('#surname').text(result["surname"]);
                $('#emailAddress').text(result["emailAddress"]);
            },
            error: function(result){
                $.notify(result, "error");
            }
        });
    });
});