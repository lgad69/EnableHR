package com.enablehr.challenge.java.entity;

public class TodoResp {

  private Integer id;
  private Boolean completed;
  private String text;
  private Integer parentId;

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

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public TodoResp(Todo body) {
    this.id = body.getId();
    this.completed = body.getCompleted();
    this.text = body.getText();
    this.parentId = body.getParentId();
  }
}
