package com.Backend.sos.service

import com.Backend.sos.model.RxRecomended
import com.Backend.sos.repository.RxRecomendedRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class RxRecomendedService {
    @Autowired
    lateinit var rxRecomendedRepository: RxRecomendedRepository

    fun listAll(): List<RxRecomended> {
        return rxRecomendedRepository.findAll()
    }

    fun getById(id: Long): Optional<RxRecomended> {
        return rxRecomendedRepository.findById(id)
    }

    fun save(rxRecomended: RxRecomended): RxRecomended {
        return rxRecomendedRepository.save(rxRecomended)
    }

    fun update(rxRecomended: RxRecomended): RxRecomended {
        return rxRecomendedRepository.save(rxRecomended)
    }

    fun delete(id: Long) {
        rxRecomendedRepository.deleteById(id)
    }
}