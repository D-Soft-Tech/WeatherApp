package com.example.ey.mapper

interface NetworkResultMapper<Entity, Domain> {
    fun fromReturnedToList(fromRemote: Entity): Domain?
}
