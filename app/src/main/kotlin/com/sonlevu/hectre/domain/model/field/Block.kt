package com.sonlevu.hectre.domain.model.field

data class Block(val name: String = "Unknown Block",
                 val id: Int = -1,
                 val orchardID: Int = -1,
                 val orchardName: String = "Unknown Orchard",
                 val rowList: ArrayList<Row>,
                 val areaAcre: Float = 0.001f)
