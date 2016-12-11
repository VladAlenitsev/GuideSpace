$(document).ready(function() {
    $.ajax({url: "/getClassificators", success: function(result){
        for (i = 0; i < result.length; i++) {
            var opt = document.createElement('option');
            opt.value = result[i].id;
            opt.innerHTML = result[i].classif_name;
            var opt2 = opt.cloneNode(true);
            $('#classificatorSelectionSearch').append(opt);
            $('#classificatorSelection').append(opt2);
        }
    }});
    $('#submitsearch').click(function() {
        $('#questionSelection').empty();
        var searchMap = getSearchData();
        console.log(searchMap);
        var cookie = JSON.parse($.cookie('CSRF'));
        $.ajax({
            data: JSON.stringify(searchMap),
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
                //[id, q, clas, an1, a1tf, a2, a2tf, a3, a3tf, a4, a4tf ]
                $('#idCarrier').val(result[0]);
                $('#question').val(result[1]);
                $("#classificatorSelection").val(result[2]);
                $('#answer1').val(result[3]);
                if(result[4]=='true') $('#atf1').prop("checked", true);
                if(result[4]=='false') $('#atf1').prop("checked", false);
                $('#answer2').val(result[5]);
                if(result[6]=='true') $('#atf2').prop("checked", true);
                if(result[6]=='false') $('#atf2').prop("checked", false);
                $('#answer3').val(result[7]);
                if(result[8]=='true') $('#atf3').prop("checked", true);
                if(result[8]=='false') $('#atf3').prop("checked", false);
                $('#answer4').val(result[9]);
                if(result[10]=='true') $('#atf4').prop("checked", true);
                if(result[10]=='false') $('#atf4').prop("checked", false);
            },
            error: function(errorThrown){
                $.notify(errorThrown, "error");
            }
        });
    });
    $('#saveQuestion').click(function(){
        var map = getData();
        var cookie = JSON.parse($.cookie('CSRF'));
        $.ajax({
            data: JSON.stringify(map),
            headers: {'X-CSRF-TOKEN': cookie.csrf},
            contentType: 'application/json',
            timeout: 5000,
            type: 'POST',
            url: '/updateQuestion',
            success: function(){
                $.notify("Questions has been updated successfully", "success");
                clean();
//                $('#idCarrier').val("");
//                document.getElementById("question").value = "";
//                document.getElementById("answer1").value = "";
//                document.getElementById("answer2").value = "";
//                document.getElementById("answer3").value = "";
//                document.getElementById("answer4").value = "";
//                $("#atf1").prop("checked", false);
//                $("#atf2").prop("checked", false);
//                $("#atf3").prop("checked", false);
//                $("#atf4").prop("checked", false);
            },
            error: function(errorThrown){
                console.log(errorThrown);
                $.notify("Couldn't update the question.", "error");
            }
        });
    });
    $('#deleteQuestion').click(function(){
        var map = getData();
        var cookie = JSON.parse($.cookie('CSRF'));
        $.ajax({
            data: JSON.stringify(map),
            headers: {'X-CSRF-TOKEN': cookie.csrf},
            contentType: 'application/json',
            timeout: 5000,
            type: 'POST',
            url: '/deleteQuestion',
            success: function(){
                $("#questionSelection option[value="+$('#idCarrier').val()+"]").remove();
                $.notify("Questions has been deleted", "success");
                clean();
//                $('#idCarrier').val("");
//                document.getElementById("question").value = "";
//                document.getElementById("answer1").value = "";
//                document.getElementById("answer2").value = "";
//                document.getElementById("answer3").value = "";
//                document.getElementById("answer4").value = "";
//                $("#atf1").prop("checked", false);
//                $("#atf2").prop("checked", false);
//                $("#atf3").prop("checked", false);
//                $("#atf4").prop("checked", false);
            },
            error: function(errorThrown){
                console.log(errorThrown);
                $.notify("Couldn't delete the question.", "error");
            }
        });
    });
});

function getData(){
    var answer1 = $('#answer1').val();
    var answer2 = $('#answer2').val();
    var answer3 = $('#answer3').val();
    var answer4 = $('#answer4').val();
    var correctAnswers = [];
    var wrongAnswers = [];
    document.getElementById('atf1').checked ? correctAnswers.push(answer1):wrongAnswers.push(answer1);
    document.getElementById('atf2').checked ? correctAnswers.push(answer2):wrongAnswers.push(answer2);
    document.getElementById('atf3').checked ? correctAnswers.push(answer3):wrongAnswers.push(answer3);
    document.getElementById('atf4').checked ? correctAnswers.push(answer4):wrongAnswers.push(answer4);
    return {'id':[$('#idCarrier').val()] ,'question' :[$('#question').val()], 'correctAnswers' : correctAnswers,
        'wrongAnswers' : wrongAnswers, 'classif' : [$('#classificatorSelection').val()]};
};
function getSearchData(){
    return {'searchText': $('#searchtext').val() ,'classif': $('#classificatorSelectionSearch').val()};
}

function clean(){
    $('#idCarrier').val("");
    document.getElementById("question").value = "";
    document.getElementById("answer1").value = "";
    document.getElementById("answer2").value = "";
    document.getElementById("answer3").value = "";
    document.getElementById("answer4").value = "";
    $("#atf1").prop("checked", false);
    $("#atf2").prop("checked", false);
    $("#atf3").prop("checked", false);
    $("#atf4").prop("checked", false);
}

