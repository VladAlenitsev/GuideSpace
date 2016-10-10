$(document).ready(function() {
    var question = $('#question');
    var submit = $('#addQuestion');
    submit.click(function() {
        document.getElementById("outputDiv").innerHTML = "Was added new question: " + question.val();
    });
});




