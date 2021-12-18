
// deshabilitar botones
$(document).ready(() => {
    $("#btnIniciar").prop("disabled", true);
});

// formulario de registro
function menu_registrar() {
    $(".registro").show();
    $(".inicio").hide();
};

// formulario de inicio de sesión
function regresar_inicio() {
    $(".registro").hide();
    $(".inicio").show();
};

// validar campos de inicio de sesion
function validar_campos() {
    let formatoEmail = /^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    let correoIngresado = $("#inputCorreo").val();
    let contra = $("#inputContra").val().length;
    if(formatoEmail.test(correoIngresado) && contra >= 6) {
        $("#btnIniciar").prop("disabled", false);
    } else {
        $("#btnIniciar").prop("disabled", true);
    }
};

// cambios en campo de correo
$("#inputCorreo").on("input", () => {
    let formatoEmail = /^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    let correoIngresado = $("#inputCorreo").val();
    validar_campos();
    if(formatoEmail.test(correoIngresado)) {
        $(".validacion").html("<span class=\"correo-valido\">Correo valido.</span>");
    } else {
        $(".validacion").html("<span class=\"correo-no-valido\">Correo no valido.</span>");
    }
});

// cambios en campo de contraseña
$("#inputContra").on("input", () => {
    validar_campos();
});

// validar registro
function inicio_sesion() {
    let email = $("#inputCorreo").val();
    let password = $("#inputContra").val();
    $.ajax({
        url: "http://129.151.118.40:8081/api/user/"+email+"/"+password,
        type: "GET",
        dataType: "json",
        success: respuesta => {
            console.log(respuesta.name)
            if(respuesta.name == "NO DEFINIDO") {
                $("#userEx").html("El usuario con correo " + email + " no está registrado.");
            } else {
                $("#userEx").html("<small class=\"green\">Inicio de sesión con usuario: </small>" + respuesta.name);
            }
        }
    });
};