package client.views;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;

/**
 * The interface provides the methods to every controller that
 * implements it.
 *
 * @author Gosia, Piotr
 */

public interface ViewController
{
  void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory);
  void handleClickMe(ActionEvent actionEvent);
}