package net.sjune.mongodb.persistence.domain;

import java.io.Serializable;

public class User implements Serializable {

  private static final long serialVersionUID = -7667147479819193393L;
  private String userName;
  private String password;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
