$(document).ready(function() {

    $("#startdate").datetimepicker({
        format:'d-m-Y H:i',
    	minDate:'0',
    	dayOfWeekStart:1
    });
    $("#enddate").datetimepicker({
        format:'d-m-Y H:i',
    	minDate:'0',
    	dayOfWeekStart:1
    });

    $.ajax({url: "/getClassificators", success: function(result){
        for (i = 0; i < result.length; i++) {
            var opt = document.createElement('option');
            opt.value = result[i].id;
            opt.innerHTML = result[i].classif_name;
            $('#classifSelect').append(opt);
        }
    }});


    $('#examreg').click(function(){
        var cookie = JSON.parse($.cookie('CSRF'));
        var map = getExamData();
        console.log(map)
        $.ajax({
            data: JSON.stringify(map),
            headers: {'X-CSRF-TOKEN': cookie.csrf},
            contentType: 'application/json',
            timeout: 5000,
            type: 'POST',
            url: '/addExamination',
            success: function(data){
            document.getElementById("outputDiv").innerHTML = "New exam has been added";
            },
            error: function(errorThrown){
               console.log(errorThrown)
               document.getElementById("outputDiv").innerHTML = "Error while trying to create new examination.";
            }
        })
    });
    $('#headers').load('/html/components/header.html');
});

function getExamData(){
    return {'startdate': $('#startdate').val(),'enddate': $('#enddate').val(),'classif':$('#classifSelect').val()}
}

