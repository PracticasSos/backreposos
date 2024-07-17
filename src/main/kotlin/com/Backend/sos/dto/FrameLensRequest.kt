package com.Backend.sos.dto

import com.Backend.sos.model.Frame
import com.Backend.sos.model.Lens

data class FrameLensRequest(
    val lens: Lens,
    val frame: Frame  //esta clase sirve para poder tener ambos registros en una sola ruta, en este caso será inventario
)
