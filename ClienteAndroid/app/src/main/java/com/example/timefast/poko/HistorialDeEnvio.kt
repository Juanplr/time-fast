package com.example.timefast.poko

data class HistorialDeEnvio(
    var idHistorialDeEnvio: Int?,
    var idEstadoDeEnvio: Int,
    var idColaborador: Int,
    var colaborador: String?,
    var noGuia: String,
    var motivo: String,
    var tiempoDeCambio: String
)
