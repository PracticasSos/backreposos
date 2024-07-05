package com.Backend.sos.repository

import com.Backend.sos.model.RxRecomended
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RxRecomendedRepository : JpaRepository<RxRecomended, Long>