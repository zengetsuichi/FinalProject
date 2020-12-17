package client;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class used for launching the Price Checker Application.
 *
 * @author Gosia, Piotr
 */

public class PriceCheckerApp extends Application
{
  @Override public void start(Stage stage)
  {
    ClientFactory clientFactory = new ClientFactory();
    ModelFactory modelFactory = new ModelFactory(clientFactory);
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    /**
     * Function for setting font type for every view.
     * @author Gosia, Karlo
     */
    Font.loadFont(PriceCheckerApp.class.getResource("Trirong-ExtraLight.ttf").toExternalForm(), 12);
    viewHandler.start();
  }
}
