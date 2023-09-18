package com.enablehr.challenge.java.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
@ExtendWith(SpringExtension.class)
public class TodoControllerTest {
/*

  @Autowired
  private MockMvc mvc;

  @Autowired
  private TodoController subject;

  @BeforeEach
  public void setUp() {

    // Reset the subject state between tests

    subject.init();
  }

  @Test
  public void testClearCompleted() throws Exception {

    subject.getTodos()
           .stream()
           .filter(todo -> ((Integer) todo.get(TodoController.ID)) % 2 == 0)
           .forEach(todo -> todo.put(TodoController.COMPLETED, Boolean.TRUE));

    mvc.perform(post("/todos/clear").accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].id").value(1))
       .andExpect(jsonPath("$[0].text").value("Use React"))
       .andExpect(jsonPath("$[0].completed").value(false));

    assertThat(subject.getTodos()).hasSize(1);
  }

  @Test
  public void testCompleteAll() throws Exception {

    mvc.perform(post("/todos/complete").accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].id").value(0))
       .andExpect(jsonPath("$[0].text").value("Use Redux"))
       .andExpect(jsonPath("$[0].completed").value(true))
       .andExpect(jsonPath("$[1].id").value(1))
       .andExpect(jsonPath("$[1].text").value("Use React"))
       .andExpect(jsonPath("$[1].completed").value(true))
       .andExpect(jsonPath("$[2].id").value(2))
       .andExpect(jsonPath("$[2].text").value("Pass the Test"))
       .andExpect(jsonPath("$[2].completed").value(true));
  }

  @Test
  public void testCompleteAllWhenAllAreComplete() throws Exception {

    subject.getTodos()
           .forEach(todo -> todo.put(TodoController.COMPLETED, Boolean.TRUE));

    mvc.perform(post("/todos/complete").accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].id").value(0))
       .andExpect(jsonPath("$[0].text").value("Use Redux"))
       .andExpect(jsonPath("$[0].completed").value(false))
       .andExpect(jsonPath("$[1].id").value(1))
       .andExpect(jsonPath("$[1].text").value("Use React"))
       .andExpect(jsonPath("$[1].completed").value(false))
       .andExpect(jsonPath("$[2].id").value(2))
       .andExpect(jsonPath("$[2].text").value("Pass the Test"))
       .andExpect(jsonPath("$[2].completed").value(false));
  }

  @Test
  public void testCompleteAllWhenSomeAreComplete() throws Exception {

    subject.getTodos()
           .stream()
           .filter(todo -> (Integer) todo.get(TodoController.ID) % 2 == 0)
           .forEach(todo -> todo.put(TodoController.COMPLETED, Boolean.TRUE));

    mvc.perform(post("/todos/complete").accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].id").value(0))
       .andExpect(jsonPath("$[0].text").value("Use Redux"))
       .andExpect(jsonPath("$[0].completed").value(true))
       .andExpect(jsonPath("$[1].id").value(1))
       .andExpect(jsonPath("$[1].text").value("Use React"))
       .andExpect(jsonPath("$[1].completed").value(true))
       .andExpect(jsonPath("$[2].id").value(2))
       .andExpect(jsonPath("$[2].text").value("Pass the Test"))
       .andExpect(jsonPath("$[2].completed").value(true));
  }

  @Test
  public void testCreate() throws Exception {

    String json = "{\"text\":\"a new todo\"}";

    mvc.perform(post("/todo").content(json)
                             .contentType(APPLICATION_JSON)
                             .accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.id").value(3))
       .andExpect(jsonPath("$.text").value("a new todo"))
       .andExpect(jsonPath("$.completed").value(false));

    assertThat(subject.getTodos()).hasSize(4);
  }

  @Test
  public void testDelete() throws Exception {

    String json = "{\"id\":1}";

    mvc.perform(delete("/todo").content(json)
                               .contentType(APPLICATION_JSON)
                               .accept(APPLICATION_JSON))
       .andExpect(status().isOk());

    assertThat(subject.getTodos()).hasSize(2);
  }

  @Test
  public void testList() throws Exception {

    mvc.perform(get("/todos").accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].id").value(0))
       .andExpect(jsonPath("$[0].text").value("Use Redux"))
       .andExpect(jsonPath("$[0].completed").value(false))
       .andExpect(jsonPath("$[1].id").value(1))
       .andExpect(jsonPath("$[1].text").value("Use React"))
       .andExpect(jsonPath("$[1].completed").value(false))
       .andExpect(jsonPath("$[2].id").value(2))
       .andExpect(jsonPath("$[2].text").value("Pass the Test"))
       .andExpect(jsonPath("$[2].completed").value(false));
  }

  @Test
  public void testMarkAsCompleteWhenComplete() throws Exception {

    subject.getTodos()
           .stream()
           .findFirst()
           .ifPresent(todo -> todo.put(TodoController.COMPLETED, Boolean.TRUE));

    String json = "{\"id\":0}";

    mvc.perform(post("/todo/complete").content(json)
                                      .contentType(APPLICATION_JSON)
                                      .accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.id").value(0))
       .andExpect(jsonPath("$.text").value("Use Redux"))
       .andExpect(jsonPath("$.completed").value(false));
  }

  @Test
  public void testMarkAsCompleteWhenNotComplete() throws Exception {

    String json = "{\"id\":0}";

    mvc.perform(post("/todo/complete").content(json)
                                      .contentType(APPLICATION_JSON)
                                      .accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.id").value(0))
       .andExpect(jsonPath("$.text").value("Use Redux"))
       .andExpect(jsonPath("$.completed").value(true));
  }

  @Test
  public void testUpdate() throws Exception {

    String json = "{\"id\":0,\"text\":\"new text\"}";

    mvc.perform(put("/todo").content(json)
                            .contentType(APPLICATION_JSON)
                            .accept(APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.id").value(0))
       .andExpect(jsonPath("$.text").value("new text"))
       .andExpect(jsonPath("$.completed").value(false));
  }
*/

}
