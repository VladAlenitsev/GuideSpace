$(document).ready(function() {
    var question = $('#question');
    var submit = $('#addQuestion');
    submit.click(function() {
        document.getElementById("outputDiv").innerHTML = "Was added new question: " + question.val();
    });
    $('#headers').load('/html/components/header.html');

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
});




