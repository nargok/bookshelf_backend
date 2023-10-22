package com.example.bookshelf.infrastructure

import com.example.bookshelf.domain.model.Author
import com.example.bookshelf.domain.model.vo.AuthorId
import com.example.bookshelf.domain.repository.AuthorRepository
import com.example.bookshelf.infra.jooq.tables.references.AUTHOR
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository

@Repository
class AuthorRepositoryImpl(
    private val dslContext: DSLContext,
): AuthorRepository {
    override fun findById(id: AuthorId): Author? {
        return dslContext.select()
            .from(AUTHOR)
            .where(AUTHOR.ID.eq(id.value.toInt()))
            .fetchOne()?.let { toModel(it) }
    }

    override fun findAll(): List<Author> {
        TODO("Not yet implemented")
    }

    override fun save(firstName: String, lastName: String): Author {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    private fun toModel(record: Record): Author {
        return Author(
            AuthorId(requireNotNull(record.getValue(AUTHOR.ID)).toLong()),
            record.getValue(AUTHOR.FIRST_NAME)!!,
            record.getValue(AUTHOR.LAST_NAME)!!,
        )
    }
}