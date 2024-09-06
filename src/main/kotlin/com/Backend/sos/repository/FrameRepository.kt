package com.Backend.sos.repository

import com.Backend.sos.model.Frame
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FrameRepository: JpaRepository <Frame, Long> {

    fun findById(id: Long?): Frame

    @Query(nativeQuery = true)
    fun sumTotal(@Param("frame_id") frameId: Long?): Double

}