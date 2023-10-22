package com.example.bookshelf.domain.model

import com.example.bookshelf.domain.model.vo.AuthorId

data class Author(
    val id: AuthorId,
    val firstName: String,
    val lastName: String,
)
