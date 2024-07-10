package com.Backend.sos.repository

import com.Backend.sos.model.Frame
import org.springframework.data.jpa.repository.JpaRepository

interface FrameRepository: JpaRepository <Frame, Long> {

    fun findById(id: Long?): Frame?

}