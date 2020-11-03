package net.bedev.testlumut.model

class modelLumut : ArrayList<modelLumut.modelLumutItem>(){
    data class modelLumutItem(
        val completed: Boolean,
        val id: Int,
        val title: String,
        val userId: Int
    )
}