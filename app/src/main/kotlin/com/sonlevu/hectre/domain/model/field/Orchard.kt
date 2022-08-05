package com.sonlevu.hectre.domain.model.field

data class Orchard(val name: String = "Unknown Orchard",
                   val id: String = "V1349J",
                   val areaAcre: Float = 0.001f,
                   val blocks: ArrayList<Block> = ArrayList())
