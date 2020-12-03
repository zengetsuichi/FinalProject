package server.networking.servermodel.shopManagerServerModel;

import shared.util.Product;
import shared.util.PropertyChangeSubject;
import java.util.ArrayList;

/**
 * Interface is used for separating the server from the model as
 * well as providing methods to the model manager.
 *
 * The interface extends property change subject to listen and fire events
 * to the listeners of this model.
 *
 * @author Gosia, Karlo
 */

public interface ShopManagerServerModel extends PropertyChangeSubject
{
  ArrayList<Product> getAllProductsForSpecificManager(String username);
}
