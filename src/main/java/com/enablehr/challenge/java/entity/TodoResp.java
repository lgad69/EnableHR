package com.enablehr.challenge.java.entity;

public class TodoResp {

  private Integer id;
  private Boolean completed;

  private String text;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Boolean getCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public TodoResp(Integer id, Boolean completed, String text) {
    this.id = id;
    this.completed = completed;
    this.text = text;
  }

  public TodoResp(Todo body) {
    this.id = body.getId();
    this.completed = body.getCompleted();
    this.text = body.getText();
  }
}
