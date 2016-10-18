$(document).ready(function() {
    var question = $('#question');
    var submit = $('#addQuestion');
    submit.click(function() {
        document.getElementById("outputDiv").innerHTML = "Was added new question: " + question.val();

         var cookie = JSON.parse($.cookie('CSRF'));
         var data = 'question=' + question.val();
         $.ajax({
             data: data,
             headers: {'X-CSRF-TOKEN': cookie.csrf},
             timeout: 1000,
             type: 'POST',
             url: '/addQuestion'

         }).done(function(data, textStatus, jqXHR) {
         alert("done");
         }).fail(function(jqXHR, textStatus, errorThrown) {
            alert("Error while adding question.");
         });
    });
    $('#headers').load('/html/components/header.html');
});






