$(function () {


$('.tablas').DataTable({
    //responsive: true,
    "order": [[1, "desc"]],
    "language": {
        "url": "//cdn.datatables.net/plug-ins/1.11.4/i18n/eu.json",
        "buttons": {
            "print": "Imprimir",
        }

    }
});
});