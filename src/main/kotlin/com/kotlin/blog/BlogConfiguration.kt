package com.kotlin.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository, articleRepository: ArticleRepository) = ApplicationRunner {

        val johnDoe = userRepository.save(User("johndoe", "John", "Doe"))
        val janeDoe = userRepository.save(User("janedoe", "Jane", "Doe"))

        articleRepository.save(Article("First Post", "first-post", "This is my first post", johnDoe))
        articleRepository.save(Article("Second Post", "second-post", "This is my second post", johnDoe))
        articleRepository.save(Article("Third Post", "third-post", "This is my third post", janeDoe))
    }
}
