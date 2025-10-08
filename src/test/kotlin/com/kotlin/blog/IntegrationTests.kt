package com.kotlin.blog

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup(){
        println(">> Setup")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        val entity = restTemplate.getForEntity<String>("/")
        assert(entity.statusCode.is2xxSuccessful)
        assert(entity.body!!.contains("<title>Kotlin Blog</title>"))
        assert(entity.body!!.contains("<h1>Kotlin Blog</h1>"))
    }

    @Test
    fun `Assert article page title, content and status code`() {
        println(">> Assert article page title, content and status code")
        val title = "First Post"
        val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
        assert(entity.statusCode.is2xxSuccessful)
        assert(entity.body!!.contains("<title>$title</title>"))
        assert(entity.body!!.contains("This is my first post"))
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}