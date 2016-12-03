$(document).ready(function() {

    $.ajax({url: "/getClassificators", success: function(result){
        for (i = 0; i < result.length; i++) {
            var opt = document.createElement('option');
            opt.value = result[i].id;
            opt.innerHTML = result[i].classif_name;
            $('#classificatorSelection').append(opt);
        }
    }});

    var submit = $('#addQuestion');
    submit.click(function() {
        var question = $('#question');
         var map = getData();
         var cookie = JSON.parse($.cookie('CSRF'));
         $.ajax({
             data: JSON.stringify(map),
             headers: {'X-CSRF-TOKEN': cookie.csrf},
             contentType: 'application/json',
             timeout: 5000,
             type: 'POST',
             url: '/addQuestion',
             success: function(data){
                document.getElementById("outputDiv").innerHTML = "New question was added: " + question.val();
                document.getElementById("question").value = "";
                document.getElementById("answer1").value = "";
                document.getElementById("answer2").value = "";
                document.getElementById("answer3").value = "";
                document.getElementById("answer4").value = "";
                $("#atf1").prop("checked", false);
                $("#atf2").prop("checked", false);
                $("#atf3").prop("checked", false);
                $("#atf4").prop("checked", false);
             },
             error: function(errorThrown){
                alert("Error while adding question.");
             }
         })
    });
    $('#headers').load('/html/components/header.html');

    var max_fields      = 5; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID

    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
            e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                $(wrapper).append('<div><input type="text" name="oigevastus[]"/><a href="#" class="remove_field">Remove</a></div>'); //add input box
            }
        });

        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
            e.preventDefault(); $(this).parent('div').remove(); x--;
        })
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

    return {'question' :[$('#question').val()], 'correctAnswers' : correctAnswers,
        'wrongAnswers' : wrongAnswers, 'classif' : [$('#classificatorSelection').val()]};
}





