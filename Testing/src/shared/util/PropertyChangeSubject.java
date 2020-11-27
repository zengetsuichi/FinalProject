package shared.util;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

/**
 * Interface used for implementing the Observer pattern
 * @author Gosia
 */
public interface PropertyChangeSubject
{
  void addListener(String eventName, PropertyChangeListener listener);
  void removeListener(String eventName, PropertyChangeListener listener);
}
