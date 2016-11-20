$(document).ready(function() {

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

            },
            error: function(errorThrown){
               console.log(errorThrown)
               alert("Error while adding new Examination.")
            }
        })
    });
    $('#headers').load('/html/components/header.html');
});

function getExamData(){
    var startdate = $('#startdate').val();
    var enddate = $('#enddate').val();
    var classif_id = $('#classifSelect').val();

    return {'startdate':startdate,'enddate':enddate,'classif_id':classif_id}

}