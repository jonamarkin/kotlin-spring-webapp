package com.kotlin.blog

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val articleRepository: ArticleRepository,
    val userRepository: UserRepository
) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val johnDoe = User("johndoe", "John", "Doe")
        entityManager.persist(johnDoe)

        val article = Article("First Post", "first-post", "This is my first post", johnDoe)
        entityManager.persist(article)
        entityManager.flush()

        val found = articleRepository.findByIdOrNull(article.id!!)
        assert(found == article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val janeDoe = User("janedoe", "Jane", "Doe")
        entityManager.persist(janeDoe)
        entityManager.flush()

        val found = userRepository.findByLogin(janeDoe.login)
        assert(found == janeDoe)
    }
}