
$(document).ready(() => {
    $("#btnRegistrar").prop("disabled", true);
});

// validar campos de registro
function validar_registro() {
    let nombre = $("#inputNombre").val().length;
    let formatoEmail = /^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    let correoIngresado = $("#inputCorreoRegistro").val();
    let contra = $("#inputContraRegistro").val();
    let contraConfirm = $("#inputContraConfirm").val();
    let contraCaracteres = $("#inputContraRegistro").val().length;
    console.log("dfdf")
    if(nombre > 0 && formatoEmail.test(correoIngresado) && contraCaracteres >= 6 && contra == contraConfirm) {
        $("#btnRegistrar").prop("disabled", false);
    } else {
        $("#btnRegistrar").prop("disabled", true);
    }
};

// cambio en campo de nombre
$("#inputNombre").on("input", () => {
    validar_registro();
});

// cambio en campo de correo
$("#inputCorreoRegistro").on("input", () => {
    let formatoEmail = /^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    let correoIngresado = $("#inputCorreoRegistro").val();
    validar_registro();
    if(formatoEmail.test(correoIngresado)) {
        $(".validacion-correo-registro").html("<span class=\"correo-valido\">Correo valido.</span>");
    } else {
        $(".validacion-correo-registro").html("<span class=\"correo-no-valido\">Correo no valido.</span>");
    }
});

// cambios en campo de contraseña
$("#inputContraRegistro").on("input", () => {
    let contraCaracteres = $("#inputContraRegistro").val().length;
    validar_registro();
    if(contraCaracteres >= 6) {
        $(".contra-validacion").html("Contraseña valida.");
    } else {
        $(".contra-validacion").html("La contraseña debe tener minimo 6 caracteres.");
    }
});

// cambios en campo de validar contraseña
$("#inputContraConfirm").on("input", () => {
    validar_registro();
});

// registrar usuario 
function registrar() {
    var usuario = {
        id: function() {return (Math.floor(Math.random() * ((999999+1)-0)+0));},
        email: $("#inputCorreoRegistro").val(),
        password: $("#inputContraRegistro").val(),
        name: $("#inputNombre").val()
    };
    $.ajax({
        url: "http://129.151.118.40:8081/api/user/new",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(usuario),
        success: respuesta => {
            console.log(respuesta.id);
            $("#inputCorreoRegistro").val("");
            $("#inputContraRegistro").val("");
            $("#inputContraConfirm").val("");
            $("#inputNombre").val("");
        }
    });
};