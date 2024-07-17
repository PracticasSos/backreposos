package com.Backend.sos.service

import com.Backend.sos.dto.FrameLensRequest
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
import kotlin.time.Duration.Companion.milliseconds


@Service
class FrameLensService {
    @Autowired
    lateinit var frameRepository: FrameRepository

    @Autowired
    lateinit var lensRepository: LensRepository

    fun list():List<Frame>?{
        return frameRepository.findAll()

    }

    fun listLens():List<Lens>?{
        return lensRepository.findAll()
    }

    fun listById (id:Long?): Frame?{
        return frameRepository.findById(id)
    }


    fun saveFrameAndLens(request: FrameLensRequest): FrameLensRequest{
        request.frame.brand?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("Debe tener un marco")
        request.frame.referenceBrand!!. takeIf { it.trim().isNotEmpty()}
            ?: throw  Exception("el marco de referencia no debe estar vacío")
        request.frame.size!!.takeIf { it > 0}
            ?:throw Exception("No hay un tamaño especificado")
        request.frame.bridge!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("No hay un puente")
        request.frame.model!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("No se específico el modelo")
        request.frame.color!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("Color no especificado")
        request.frame.price?.takeIf { it > 0.0 }
            ?:throw  Exception("El precio es obligatorio")
        if (request.frame.frameStock?. takeIf { it == 0} != null){
            throw Exception("El numero de producto está vacío")
        }else if (request.frame.frameStock?. takeIf { it > 1000 } != null){
            throw Exception("EL número excede la cantidad permitida")
        }
        request.lens.lensType?.takeIf { it.trim().isNotEmpty()}
            ?: throw Exception("El tipo de lente está vacío")
        request.lens.lensMaterial!!.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El material esta vació o no?")
        request.lens.lensCoating!!.takeIf { it.trim().isNotEmpty() }
            ?:throw Exception("Recubrimiento si especificar")
        request.lens.lensColor!!.takeIf { it.trim().isNotEmpty() }
            ?:throw Exception("Color no especificado")
        request.lens.lensPrice!!.takeIf { it > 0.0}
            ?: throw Exception("Precio no especificado ")
        if (request.lens.lensStock?. takeIf { it == 0} != null){
            throw Exception("El numero de producto está vacío")
        }else if (request.lens.lensStock?. takeIf { it > 1000 } != null){
            throw Exception("EL número excede la cantidad permitida")
        }


        try {
            val savedFrame = frameRepository.save(request.frame)
            val savedLens = lensRepository.save(request.lens)
            return FrameLensRequest(savedLens, savedFrame)

        }catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message,ex
            )
        }


    }

    fun updateLens(model: Lens): Lens{
        try {
            lensRepository.findById(model.id)
                ?: throw Exception("ID no existe")

            return lensRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun deleteLens (id: Long?): Boolean?{
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


    fun update(model: Frame): Frame {
        try {
            frameRepository.findById(model.Id)
                ?: throw Exception("ID no existe")

            return frameRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
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