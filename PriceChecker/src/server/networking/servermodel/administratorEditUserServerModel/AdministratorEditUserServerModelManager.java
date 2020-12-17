package server.networking.servermodel.administratorEditUserServerModel;
import dataaccess.administratorDAO.AdministratorDAO;
import dataaccess.loginRegisterDAO.LoginRegisterDAO;
import shared.util.EventType;
import shared.util.User;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class AdministratorEditUserServerModelManager implements AdministratorEditUserServerModel
{

  private AdministratorDAO administratorDAO;
  private LoginRegisterDAO loginRegisterDAO;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);


  public AdministratorEditUserServerModelManager(AdministratorDAO administratorDAO, LoginRegisterDAO loginRegisterDAO)
  {
    this.administratorDAO = administratorDAO;
    this.loginRegisterDAO = loginRegisterDAO;
  }

  @Override public String validateUserEdit(String oldUsername, String oldEmail, String username, String email, String password, String dob)
  {
    String result = null;
    try {
      User user = loginRegisterDAO.findUser(username);
      String userEmail = loginRegisterDAO.findEmail(email);

      Boolean isSameUser = oldUsername.equals(username);
      Boolean isSameEmail = oldEmail.equals(email);



      if (!isSameUser && user != null) {

        return "User with this username already exist";

      } else if ( !isSameEmail && userEmail != null) {

        return "Email already used";
      } else {
        administratorDAO.editUser(oldUsername,oldEmail,username, email, password, dob);
        support.firePropertyChange(EventType.EDIT_USER.name(), null, administratorDAO.getAllUsers());
        return "User edited";
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return "Houston we have a problem.";
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}
