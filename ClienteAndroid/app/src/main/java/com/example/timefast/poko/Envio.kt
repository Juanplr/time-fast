package com.example.timefast.poko

data class Envio(

    var idEnvio: Int,
    var idCliente: Int,
    var cliente: String,
    var destino: String,
    var origen: String,
    var origenCalle: String,
    var origenNumero: String,
    var origenColonia: String,
    var origenCodigoPostal: String,
    var origenCiudad: String,
    var origenEstado: String,
    var noGuia: String,
    var costoDeEnvio: Float,
    var idEstadoDeEnvio: Int,
    var estadoDeEnvio: String,
    var idColaborador: Int,
    var colaborador: String
)
