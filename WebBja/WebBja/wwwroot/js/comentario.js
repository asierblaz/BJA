$(function () {

     //cargar create en index
    $(document).ready(function () {
       var ruta = "/Comentario/Create";

        console.log(ruta);
        $.ajax({
            url:ruta,
            method: 'GET',
            success: function (html) {
                console.log(html);
                $('#formulariolario').html($(html).find('#formCreate'));

            }
        });
    });



    //añadr productos en productos
    $(document).on('click', '#eliminar', function (e) {
        e.preventDefault();

        var comentario = $(this).data("idcoment");
        if (confirm("¿Iruzkina ezabatu nahi duzu?")) {
            $.ajax({
                url: "/Comentario/Delete/" + comentario,
                method: 'POST',

                success: function (datos) {

                    window.location.href = "/";
                }
            });
        }
    });

    

});