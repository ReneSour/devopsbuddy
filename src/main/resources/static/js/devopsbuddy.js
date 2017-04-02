$(document).ready(main);

function main (){
    $('.btn-collapse').click(function (e){
        e.preventDefault();
        var $this=$(this);
        var $collapse = $this.closest('.collapse-group').find('.collapse');
        //hace un switch entre mostrar/no mostrar el texto
        $collapse.collapse('toggle');
    });
}