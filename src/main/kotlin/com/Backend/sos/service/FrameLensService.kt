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

    fun list():List<Frame>?{
        return frameRepository.findAll()

    }

    fun listLens():List<Lens>?{        return lensRepository.findAll()
    }

    fun listById (id:Long?): Frame?{
        return frameRepository.findById(id)
    }

    fun save(model: Frame): Frame {
        frameRepository.save(model)

        return  frameRepository.save(model)
    }

    fun saveLens(model: Lens): Lens
    {
        lensRepository.save(model)

        return lensRepository.save(model)
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