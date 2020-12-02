package client.clientmodel.editProductAdministratorModel;
import shared.util.PropertyChangeSubject;
import java.util.ArrayList;
/**
 * Interface is used for separating the view model from the model as
 * well as providing methods to the model manager.
 *
 * The interface extends property change subject to listen and fire events
 * to the listeners of this model.
 *
 * @author Gosia, Karlo
 */
public interface EditProductAdministratorModel extends PropertyChangeSubject
{
  ArrayList<String> getAllProductCategories();
  ArrayList<String> getAllTags();
  String addNewCategory(String newCategory);
  String addNewTag(String newTag);
  String editProduct(String productName, String productDescription,
      String category, ArrayList<String> parseTag, int productId);
}
