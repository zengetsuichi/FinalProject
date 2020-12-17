package shared.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class used for creating an user object.
 *
 * @author Gosia, Piotr
 */

public class User implements Serializable
{
  private int userId;
  private String username;
  private String email;
  private String password;
  private String dob;
  private String type;
  private boolean isSubscribed;

  /**
   * Constructor made for retrieving user data with subscription field
   * @author Karlo
   */
  public User(String username, String email, String password,
      String dob, String type, int userId, boolean isSubscribed)
  {
    this.username = username;
    this.email = email;
    this.password = password;
    this.dob = dob;
    this.type = type;
    this.userId = userId;
    this.isSubscribed = isSubscribed;
  }

  public User(String username, String email, String password,
      String dob, String type, int userId)
  {
    this.username = username;
    this.email = email;
    this.password = password;
    this.dob = dob;
    this.type = type;
    this.userId = userId;
  }

  public User(String username, String email, String password, String dob,
      String type)
  {
    this.username = username;
    this.email = email;
    this.password = password;
    this.dob = dob;
    this.type = type;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }


  public int getUserId()
  {
    return userId;
  }

  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  public String getDob()
  {
    return dob;
  }

  public void setDob(String dob)
  {
    this.dob = dob;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public boolean getIsSubscribed()
  {
    return isSubscribed;
  }

  public void setSubscribed(boolean subscribed)
  {
    isSubscribed = subscribed;
  }

  @Override public String toString()
  {
    return "User{" + "userId='" + userId + '\'' + ", username='" + username
        + '\'' + ", email='" + email + '\'' + ", password='" + password + '\''
        + ", dob='" + dob + '\'' + ", type='" + type + '\'' + '}';
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return userId == user.userId && isSubscribed == user.isSubscribed && Objects
        .equals(username, user.username) && Objects.equals(email, user.email)
        && Objects.equals(password, user.password) && Objects
        .equals(dob, user.dob) && Objects.equals(type, user.type);
  }

}
