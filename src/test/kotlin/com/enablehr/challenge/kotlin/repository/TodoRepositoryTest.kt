package com.enablehr.challenge.kotlin.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJpaTest
@ExtendWith(SpringExtension::class)
internal class TodoRepositoryTest {

    @Autowired
    private lateinit var subject: TodoRepository

    @Test
    internal fun `test that repository is populated`() {

        assertThat(subject.findAll()).hasSize(3)
    }
}
