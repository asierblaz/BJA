$(function () {

    $(document).ready(function () {
        var existe = $("#titulo").data("info");


        if (existe == 1) {
            $('#Puntuacion').prop("disabled", true);
            $("#Mejoras").prop("disabled", true);
            $("#Horas").prop("disabled", true);
            $("#enviar").prop("disabled", true);
        }
    });


    

});