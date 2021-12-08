$(document).ready(function () {
    var start = new Date();
    start.setFullYear(start.getFullYear() - 100);
    var end = new Date();
    end.setFullYear(end.getFullYear() - 1);
    $(".datepicker").datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: start.getFullYear() + ':' + end.getFullYear(),
        beforeShow: function () {
            setTimeout(function () {
                $('.ui-datepicker').css('z-index', 99999999999999);
            }, 0);
        }
    });
});