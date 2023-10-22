package com.example.bookshelf.domain.repository

import com.example.bookshelf.domain.model.Author
import com.example.bookshelf.domain.model.vo.AuthorId

interface AuthorRepository {
    fun findById(id: AuthorId): Author?
    fun findAll(): List<Author>
    fun save(firstName: String, lastName: String): Author
    fun deleteAll()
}