package es.uniovi.miw.miwtter.database.domain

data class Post(val id: String, val content: String, val ownerUsername: String, val ownerName: String, var numberOfLikes: Int = 0)
