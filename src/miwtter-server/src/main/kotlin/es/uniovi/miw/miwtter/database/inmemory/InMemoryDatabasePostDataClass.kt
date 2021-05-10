package es.uniovi.miw.miwtter.database.inmemory

data class InMemoryDatabasePostDataClass(val id: String, val content: String, val ownerUsername: String, val ownerName: String, var numberOfLikes: Int = 0)
