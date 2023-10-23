package com.example.bookshelf.domain

import com.example.bookshelf.domain.repository.AuthorRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.flywaydb.core.Flyway
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.MySQLContainer

@SpringBootTest
class AuthorRepositoryTest(
    private val authorRepository: AuthorRepository
) : FunSpec({
    beforeSpec {
        Flyway.configure()
            .dataSource(container.jdbcUrl, container.username, container.password)
            .load()
            .migrate()
    }
    afterTest { authorRepository.deleteAll() }
    test("save and find") {
        val saved = authorRepository.save("太郎", "山田")
        val find = authorRepository.findById(saved.id)
        saved shouldBe find
    }
    test("find count one") {
        authorRepository.save("太郎", "山田")
        authorRepository.findAll().size shouldBe 1
    }
}) {
    companion object {
        val container = MySQLContainer("mysql:latest").apply {
            withDatabaseName("library")
            withUsername("root")
            withPassword("root")
            start()
        }
    }
}
