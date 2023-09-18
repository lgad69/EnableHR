package com.enablehr.challenge.java.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class TodoRepositoryTest {

  @Autowired
  private TodoRepository subject;

  @Test
  public void testRepositoryIsPopulated() {

    assertThat(subject.findAll()).hasSize(3);
  }

}
