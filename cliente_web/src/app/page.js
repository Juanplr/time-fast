'use client'

import { useState } from 'react'
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card"
import { Package, Truck, Home, CheckCircle, Search } from 'lucide-react'

export default function Page() {
  const [guia, setGuia] = useState('')
  const [envio, setEnvio] = useState(null)
  const [error, setError] = useState('') 

  const buscarEnvio = () => {
    if (!guia.trim()) {
      setError('Por favor, ingrese un número de guía válido.') 
      return
    }

    setError('') 

   
    setTimeout(() => {
      setEnvio({
        numero: guia,
        estado: 'En tránsito',
        origen: 'Ciudad de México',
        destino: 'Guadalajara',
        fechaEnvio: '2023-11-05',
        fechaEstimadaEntrega: '2023-11-10',
        paquetes: [
          { id: 1, peso: '2kg', dimensiones: '30x20x15cm' },
          { id: 2, peso: '1.5kg', dimensiones: '25x15x10cm' }
        ],
        historial: [
          { fecha: '2023-11-05', estado: 'Pedido recibido', completado: true },
          { fecha: '2023-11-06', estado: 'En tránsito', completado: true },
          { fecha: '2023-11-07', estado: 'En centro de distribución local', completado: true },
          { fecha: '', estado: 'Entregado', completado: false }
        ]
      })
    }, 1000)
  }

  const getIconForState = (state) => {
    switch (state) {
      case 'Pedido recibido':
        return <Package className="w-6 h-6" />
      case 'En tránsito':
        return <Truck className="w-6 h-6" />
      case 'En centro de distribución local':
        return <Home className="w-6 h-6" />
      case 'Entregado':
        return <CheckCircle className="w-6 h-6" />
      default:
        return null
    }
  }

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">Consulta de Envíos</h1>
      <div className="flex flex-col gap-2 mb-4">
        <div className="flex gap-2">
          <Input
            type="text"
            placeholder="Ingrese número de guía"
            value={guia}
            onChange={(e) => setGuia(e.target.value)}
          />
          <Button onClick={buscarEnvio}><Search className="w-4 h-4 mr-2" /> Buscar</Button>
        </div>
        {error && <p className="text-red-500 text-sm">{error}</p>} {/* Muestra el mensaje de error */}
      </div>

      {envio && (
        <Card>
          <CardHeader>
            <CardTitle>Detalles del Envío</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="grid grid-cols-2 gap-4 mb-4">
              <div>
                <p><strong>Número de Guía:</strong> {envio.numero}</p>
                <p><strong>Estado:</strong> {envio.estado}</p>
                <p><strong>Origen:</strong> {envio.origen}</p>
                <p><strong>Destino:</strong> {envio.destino}</p>
              </div>
              <div>
                <p><strong>Fecha de Envío:</strong> {envio.fechaEnvio}</p>
                <p><strong>Fecha Estimada de Entrega:</strong> {envio.fechaEstimadaEntrega}</p>
              </div>
            </div>

            <h3 className="font-semibold mt-4 mb-2">Estado del Envío:</h3>
            <div className="flex justify-between items-center mb-4">
              {envio.historial.map((evento, index) => (
                <div key={index} className="flex flex-col items-center">
                  <div className={`rounded-full p-2 ${evento.completado ? 'bg-green-500' : 'bg-gray-300'}`}>
                    {getIconForState(evento.estado)}
                  </div>
                  <p className="text-xs mt-1 text-center">{evento.estado}</p>
                </div>
              ))}
            </div>

            <h3 className="font-semibold mt-4 mb-2">Paquetes:</h3>
            <ul>
              {envio.paquetes.map(paquete => (
                <li key={paquete.id}>
                  Peso: {paquete.peso}, Dimensiones: {paquete.dimensiones}
                </li>
              ))}
            </ul>

            <h3 className="font-semibold mt-4 mb-2">Historial Detallado:</h3>
            <ul>
              {envio.historial.map((evento, index) => (
                <li key={index} className="flex items-center mb-2">
                  {getIconForState(evento.estado)}
                  <span className="ml-2">{evento.fecha}: {evento.estado}</span>
                </li>
              ))}
            </ul>
          </CardContent>
        </Card>
      )}
    </div>
  )
}
