package client.views;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
/**
 * @author Gosia, Piotr
 */
public interface ViewController
{
  void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory);
  void handleClickMe(ActionEvent actionEvent);
}
