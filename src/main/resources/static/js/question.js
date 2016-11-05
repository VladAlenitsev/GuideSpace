$(document).ready(function() {
    var submit = $('#addQuestion');
    submit.click(function() {
        var question = $('#question');
         var map = getData();
         var cookie = JSON.parse($.cookie('CSRF'));
         //var data = 'question=' + $('#question').val();
         console.log(map);
         $.ajax({
             data: JSON.stringify(map),
             headers: {'X-CSRF-TOKEN': cookie.csrf},
             timeout: 1000,
             contentType: 'application/json',
             type: 'POST',
             url: '/addQuestion'

         }).done(function(data, textStatus, jqXHR) {
		 alert("Question saved.");
         document.getElementById("outputDiv").innerHTML = "New question was added: " + question.val();
         document.getElementById("question").innerHTML = "";
         document.getElementById("answer1").innerHTML = "";
         document.getElementById("answer2").innerHTML = "";
         document.getElementById("answer3").innerHTML = "";
         document.getElementById("answer4").innerHTML = "";
         }).fail(function(jqXHR, textStatus, errorThrown) {
            alert("Error while adding question.");
         });
    });
    $('#headers').load('/html/components/header.html');

    //[Vlad comment @06.11.2016]
    // IDK wtf is this stuff
    // imo this all bullshit
    //          ||
    //          ||
    //          \/

    // siin teha eraldi kaks identiti õigete ja valede vastuste jaoks
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
    //          /\
    //          ||
    //          ||
});

function getData(){

    var question = $('#question').val();
    var answer1 = $('#answer1').val();
    var answer2 = $('#answer2').val();
    var answer3 = $('#answer3').val();
    var answer4 = $('#answer4').val();

    var map = {'question' : question, 'answer1' : answer1, 'answer2' : answer2, 'answer3' : answer3, 'answer4' : answer4}

    return map;
}





