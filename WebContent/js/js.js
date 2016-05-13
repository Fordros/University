
$(document).ready(function(){



    $("#accNumber").change(function()
    {
        var acc = document.getElementById("accNumber").value;
        if (acc.length != 17) {
            document.getElementById("send").disabled = true;
        }else {
            document.getElementById("send").disabled = false;
        }
    });

    $('#accNumber')/*на месте number любой селектор которым ты указываешся на нужный импут*/.bind("change keyup input click", function() {
        if (this.value.match(/[^0-9]/g)) {
            this.value = this.value.replace(/[^0-9]/g, '');
        }
    });

});

