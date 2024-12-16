'use client'

import { useState } from 'react'
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card"
import { Package, Truck, Home, CheckCircle, Search } from 'lucide-react'

export default function PaginaEnvio() {
  const [numeroGuia, setNumeroGuia] = useState('')
  const [datosEnvio, setDatosEnvio] = useState(null)
  const [listaPaquetes, setListaPaquetes] = useState([])
  const [historialEnvios, setHistorialEnvios] = useState([])
  const [mensajeError, setMensajeError] = useState('')
  const [cargando, setCargando] = useState(false)

  const buscarEnvioPorGuia = async () => {
    if (!numeroGuia.trim()) {
      setMensajeError('Por favor, ingrese un número de guía válido.')
      setDatosEnvio(null)
      setListaPaquetes([])
      setHistorialEnvios([])
      return
    }

    setMensajeError('')
    setCargando(true)

    try {
      const respuestaEnvio = await fetch(`http://localhost:8084/time-fast/api/envio/obtener-envios-por-noguia/${numeroGuia}`)
      if (!respuestaEnvio.ok) throw new Error('No se pudo obtener la información del envío.')
      const datos = await respuestaEnvio.json()

      if (datos.length > 0) {
        const envio = datos[0]
        setDatosEnvio(envio)

        // Obtener Paquetes
        const respuestaPaquetes = await fetch(`http://localhost:8084/time-fast/api/paquetes/obtener-paquetes-envio/${envio.idEnvio}`)
        setListaPaquetes(respuestaPaquetes.ok ? await respuestaPaquetes.json() : [])

        // Obtener Historial de Envío
        const respuestaHistorial = await fetch(`http://localhost:8084/time-fast/api/historial-envio/obtener-historial/${numeroGuia}`)
        setHistorialEnvios(respuestaHistorial.ok ? await respuestaHistorial.json() : [])
      } else {
        setMensajeError('No se encontraron resultados para el número de guía ingresado.')
        setDatosEnvio(null)
        setListaPaquetes([])
        setHistorialEnvios([])
      }
    } catch (error) {
      setMensajeError(error.message)
    } finally {
      setCargando(false)
    }
  }

  const obtenerIconoPorEstado = (estado) => {
    switch (estado) {
      case 'Pedido recibido': return <Package className="w-6 h-6" />
      case 'En tránsito': return <Truck className="w-6 h-6" />
      case 'Centro de distribución': return <Home className="w-6 h-6" />
      case 'Entregado': return <CheckCircle className="w-6 h-6" />
      default: return null
    }
  }

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">Consulta de Envíos</h1>
      <div className="flex gap-2 mb-4">
        <Input
          type="text"
          placeholder="Ingrese número de guía"
          value={numeroGuia}
          onChange={(e) => setNumeroGuia(e.target.value)}
        />
        <Button onClick={buscarEnvioPorGuia} disabled={cargando}>
          {cargando ? 'Buscando...' : <><Search className="w-4 h-4 mr-2" /> Buscar</>}
        </Button>
      </div>

      {mensajeError && <p className="text-red-500">{mensajeError}</p>}

      {datosEnvio && (
        <Card>
          <CardHeader><CardTitle>Detalles del Envío</CardTitle></CardHeader>
          <CardContent>
            <div className="grid grid-cols-2 gap-4 mb-4">
              <div>
                <p><strong>Cliente:</strong> {datosEnvio.cliente}</p>
                <p><strong>Número de Guía:</strong> {datosEnvio.noGuia}</p>
                <p><strong>Estado:</strong> {datosEnvio.estadoDeEnvio}</p>
                <p><strong>Origen:</strong> {datosEnvio.origen}</p>
                <p><strong>Destino:</strong> {datosEnvio.destino}</p>
              </div>
              <div>
                <p><strong>Costo de Envío:</strong> ${datosEnvio.costoDeEnvio}</p>
                <p><strong>Colaborador:</strong> {datosEnvio.colaborador}</p>
              </div>
            </div>

            {/* Estado del Envío */}
            <h3 className="font-bold text-xl mt-4">Estado del Envío:</h3>
            <div className="flex items-center gap-4 mb-4">
              <div className="rounded-full p-3 bg-green-500 text-white">
                {obtenerIconoPorEstado(datosEnvio.estadoDeEnvio)}
              </div>
              <p>{datosEnvio.estadoDeEnvio}</p>
            </div>

            {/* Detalles del Paquete */}
            <h3 className="font-bold text-xl mt-4">Detalles del Paquete:</h3>
            {listaPaquetes.map((paquete, index) => (
              <div key={index} className="mb-4">
                <p><strong>ID Paquete:</strong> {paquete.idPaquete}</p>
                <p><strong>Descripción:</strong> {paquete.descripcion}</p>
                <p><strong>Peso:</strong> {paquete.peso} kg</p>
                <p><strong>Dimensiones:</strong> {paquete.alto}X{paquete.ancho}X{paquete.profundidad}</p>
              </div>
            ))}

            {/* Historial de Envios */}
            <h3 className="font-bold text-xl mt-4">Historial de Envíos:</h3>
            {historialEnvios.map((historial, index) => (
              <div key={index} className="flex items-center gap-4 mb-4">
                <div className="rounded-full p-3 bg-gray-300 text-black">
                  <Package className="w-6 h-6" />
                </div>
                <div>
                  <p><strong>Fecha del Cambio:</strong> {historial.tiempoDeCambio}</p>
                  <p><strong>Motivo:</strong> {historial.motivo}</p>
                  <p><strong>ID Paquete:</strong> {historial.idPaquete}</p>
                </div>
              </div>
            ))}
          </CardContent>
        </Card>
      )}
    </div>
  )
}
