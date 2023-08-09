
$(document).ready(function(){
    let url = "";

    $('button[id*="btn_"]').click(function() {
        url = "http://localhost:8080/" + $(this).attr('id').split("_")[1];
    });

    $('#ok_confirm').click(function() {
        document.location.href = url;
    })

    $(function() {
        $('[data-toggle="popover"]').popover();
    });

    $(".navbar-toggle").click(function(){
        $(".sidebar").toggleClass("sidebar-open");
    })

    function limpa_formulário_cep() {
        // Limpa valores do formulário de cep.
        $("#logradouro").val("");
        $("#bairro").val("");
        $("#cidade").val("");
        $("#uf").val("");
        $("#cep").removeClass("is-invalid");
        $("span").closest('.error-span').remove();
    }

    $("#cep").blur(function() {
        limpa_formulário_cep();
        //Nova variável "cep" somente com dígitos.
        var cep = $(this).val().replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {
            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;
            if (validacep.test(cep)) {
                //Consulta o webservice viacep.com.br/
                $.ajax({
                     method: "GET",
                     url: "https://viacep.com.br/ws/"+ cep +"/json/",
                     success: function(dados) {
                        if(!("erro" in dados)){
                            //Atualiza os campos com os valores da consulta.
                            $("#logradouro").val(dados.logradouro);
                            $("#bairro").val(dados.bairro);
                            $("#cidade").val(dados.localidade);
                            $("#uf").val(dados.uf);
                        } else{
                            $("#cep").addClass("is-invalid");
                            $("#error-cep").addClass("invalid-feedback").append("<span class='error-span'>Cep inválido!</span>");
                          }
                    }
                });
            } else{
                $("#cep").addClass("is-invalid");
                $("#error-cep").addClass("invalid-feedback").append("<span class='error-span'>Cep deve conter 8 dígitos.</span>");
              }
        }
    });
});