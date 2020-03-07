package com.ly.messagemanage.Domain;


import lombok.ToString;

@ToString
public class User {

  private long id;
  private String name;
  private String password;
  private String clientkey;
  private long status;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getClientkey() {
    return clientkey;
  }

  public void setClientkey(String clientkey) {
    this.clientkey = clientkey;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
