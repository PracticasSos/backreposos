package com.Backend.sos.service

import com.Backend.sos.model.Frame
import com.Backend.sos.model.Lens
import com.Backend.sos.repository.FrameRepository
import com.Backend.sos.repository.LensRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class FrameLensService {
    @Autowired
    lateinit var frameRepository: FrameRepository

    @Autowired
    lateinit var lensRepository: LensRepository

    fun listById(id: Long?): Frame? {
        return frameRepository.findById(id)
    }

    fun  listLens(): List <Lens>{
        return lensRepository.findAll()
    }

    fun listFrame(): List<Frame>{
        return frameRepository.findAll()
    }


    fun savaLens(model: Lens): Lens{
        model.lensType?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("Nombres no debe ser vacio")
        model.lensMaterial?.takeIf { it.trim(). isNotEmpty() } ?: throw Exception ("Debe especificar el material o asignarlo como vacío")
        model.lensDescription?.takeIf { it.trim(). isNotEmpty() } ?: throw Exception ("Debe tener una descripción")
        model.lensColor?.takeIf { it.trim(). isNotEmpty() } ?: throw Exception ("Se debe especificar el color")
        model.lensPrice?.takeIf { it > 0.0 } ?: throw Exception ("El precio no puede ser de un valor 0")

        return lensRepository.save(model)
    }



    fun saveFrame(request: Frame): Frame {
        try{
        request.brand?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("Debe tener un marco")
        request.reference!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("el marco de referencia no debe estar vacío")
        request.size!!.takeIf { it > 0 }
            ?: throw Exception("No hay un tamaño especificado")
        request.bridge!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("No hay un puente")
        request.model!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("No se específico el modelo")
        request.color!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("Color no especificado")
        request.rod!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("Color no especificado")
        request.price?.takeIf { it > 0.0 }
            ?: throw Exception("El precio es obligatorio")
        if (request.frameStock?.takeIf { it == 0 } != null) {
            throw Exception("El numero de producto está vacío")
        } else if (request.frameStock?.takeIf { it > 1000 } != null) {
            throw Exception("EL número excede la cantidad permitida")
        }
            return frameRepository.save(request)
        } catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex
            )
        }
    }

    fun updateDaes(model : Frame): Frame{
        try {
            val response = frameRepository.findById(model.id)
                ?:throw Exception("ID no existe")
            response.apply {
                brand = model.brand
                reference = model.reference
                size = model.size
                bridge = model.bridge
                color = model.color
                price = model.price
                frameStock = model.frameStock
            }
            return frameRepository.save(response)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updatesLens(model: Lens): Lens{
        try {
            val respuesta = lensRepository.findById(model.id)
                ?: throw Exception("ID no encontrada")
            respuesta.apply {
                lensType = model.lensType
                lensMaterial = model.lensMaterial
                lensDescription = model.lensDescription
                lensColor = model.lensColor
                lensPrice = model.lensPrice
            }
            return lensRepository.save(respuesta)
        }catch (ex: Exception){
             throw ResponseStatusException(HttpStatus.NOT_FOUND,ex. message)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = frameRepository.findById(id)
                ?: throw Exception("ID no existe")
            frameRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun deleteLens (id: Long?):Boolean?{
        try{
            val response = lensRepository.findById(id)
                ?: throw Exception("ID no existe")
            lensRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}