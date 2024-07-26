package com.Backend.sos.service

import com.Backend.sos.dto.FrameLensRequest
import com.Backend.sos.dto.ProductResponse
import com.Backend.sos.model.Frame
import com.Backend.sos.model.Lens
import com.Backend.sos.repository.FrameRepository
import com.Backend.sos.repository.LensRepository
import org.aspectj.apache.bcel.classfile.ExceptionTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.lang.reflect.Executable
import javax.lang.model.element.ModuleElement
import kotlin.time.Duration.Companion.milliseconds


@Service
class FrameLensService {
    @Autowired
    lateinit var frameRepository: FrameRepository

    @Autowired
    lateinit var lensRepository: LensRepository

    fun obtenerProductos(): List<ProductResponse> {
        val lenses = lensRepository.findAll().map { lens ->
            ProductResponse(
                id = lens.id,
                tipo = "Lens",
                descripcion = "${lens.lensType} - ${lens.lensMaterial}",
                precio = lens.lensPrice
            )
        }

        val frames = frameRepository.findAll().map { frame ->
            ProductResponse(
                id = frame.Id,
                tipo = "Frame",
                descripcion = "${frame.brand} - ${frame.model}",
                precio = frame.price
            )
        }
    return lenses + frames
}

    fun listById(id: Long?): Frame? {
        return frameRepository.findById(id)}




    fun saveFrameAndLens(request: FrameLensRequest): FrameLensRequest {
        request.frame.brand?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("Debe tener un marco")
        request.frame.referenceBrand!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("el marco de referencia no debe estar vacío")
        request.frame.size!!.takeIf { it > 0 }
            ?: throw Exception("No hay un tamaño especificado")
        request.frame.bridge!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("No hay un puente")
        request.frame.model!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("No se específico el modelo")
        request.frame.color!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("Color no especificado")
        request.frame.price?.takeIf { it > 0.0 }
            ?: throw Exception("El precio es obligatorio")
        if (request.frame.frameStock?.takeIf { it == 0 } != null) {
            throw Exception("El numero de producto está vacío")
        } else if (request.frame.frameStock?.takeIf { it > 1000 } != null) {
            throw Exception("EL número excede la cantidad permitida")
        }
        request.lens.lensType?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El tipo de lente está vacío")
        request.lens.lensMaterial!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El material esta vació o no?")
        request.lens.lensCoating!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("Recubrimiento si especificar")
        request.lens.lensColor!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("Color no especificado")
        request.lens.lensPrice!!.takeIf { it > 0.0 }
            ?: throw Exception("Precio no especificado ")
        if (request.lens.lensStock?.takeIf { it == 0 } != null) {
            throw Exception("El numero de producto está vacío")
        } else if (request.lens.lensStock?.takeIf { it > 1000 } != null) {
            throw Exception("EL número excede la cantidad permitida")
        }


        try {
            val savedFrame = frameRepository.save(request.frame)
            val savedLens = lensRepository.save(request.lens)
            return FrameLensRequest(savedLens, savedFrame)

        } catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex
            )
        }


    }

    fun deleteLens(id: Long?): Boolean? {
        try {
            val response = lensRepository.findById(id)
                ?: throw Exception("ID no existe")
            lensRepository.deleteById(id!!)
            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }


    fun update(model: FrameLensRequest): FrameLensRequest {
        try {
            frameRepository.findById(model.frame.Id)
                ?: throw Exception("ID no existe")
            lensRepository.findById(model.lens.id)
                ?: throw Exception("ID no existe")

            val updateFrame = frameRepository.save(model.frame)
            val updateLens = lensRepository.save(model.lens)

            return FrameLensRequest(updateLens, updateFrame)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateDaes(model : Frame): Frame{
        try {
            val response = frameRepository.findById(model.Id)
                ?:throw Exception("ID no existe")
            response.apply {
                brand = model.brand
                referenceBrand = model.referenceBrand
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
                lensCoating = model.lensCoating
                lensColor = model.lensColor
                lensPrice = model.lensPrice
                lensStock = model.lensStock
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

}