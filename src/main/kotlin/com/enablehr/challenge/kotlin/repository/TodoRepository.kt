package com.enablehr.challenge.kotlin.repository

import com.enablehr.challenge.kotlin.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo, Int>
