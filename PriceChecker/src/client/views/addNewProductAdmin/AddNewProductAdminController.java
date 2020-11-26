package client.views.addNewProductAdmin;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckListView;
import org.controlsfx.control.SearchableComboBox;
import java.util.ArrayList;
/**
 * Class implementing the view controller interface. Used for initializing
 * view components, retrieving data from them and providing functionality
 * for components.
 *
 * @author Gosia, Karlo
 */

public class AddNewProductAdminController implements ViewController
{
  @FXML
  private TextField productNameTextField;
  @FXML
  private TextArea productDescriptionTextField;
  @FXML
  private TextField newTagTextField;
  @FXML
  private Button addNewTagBtn;
  @FXML
  private Button addProductBtn;
  @FXML
  private SearchableComboBox<String> categoryBox;
  @FXML
  private TextField newCategoryTextField;
  @FXML
  private Button addNewCategoryBtn;
  @FXML
  private Button goBackBtn;
  @FXML
  private CheckListView<String> checkListViewTags;
  @FXML
  private Label errorLabel;

  private ViewHandler viewHandler;
  private AddNewProductAdminViewModel addNewProductAdminViewModel;

  /**
   * Method used; for initializing the controller, loading the initial
   * data (categories and tags) and binding the category box and check list view
   * to the observable lists from the view model.
   *
   * @author Gosia, Karlo
   */
  @Override public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    addNewProductAdminViewModel = viewModelFactory.getAddNewProductAdminViewModel();
    addNewProductAdminViewModel.loadData();
    categoryBox.setItems(addNewProductAdminViewModel.getAllProductCategories());
    checkListViewTags.setItems(addNewProductAdminViewModel.getAllTags());
  }

  /**
   * Method used for handling events from UI
   *
   * @author Gosia, Karlo
   */
  @Override public void handleClickMe(ActionEvent actionEvent)
  {
    if(actionEvent.getSource() == addProductBtn){
      addNewProduct();
    }
    else if(actionEvent.getSource() == addNewCategoryBtn){
      addNewCategory();
    }
    else if(actionEvent.getSource() == addNewTagBtn){
      addNewTag();
    }
    else if(actionEvent.getSource() == goBackBtn){
      viewHandler.openAdministratorView();
    }
  }

  private void addNewTag()
  {
    String newTag = newTagTextField.getText();
    if(!newTag.isEmpty() && newTag.length() <= 20)
    {
      String response = addNewProductAdminViewModel.addNewTag(newTag);
      errorLabel.setText(response);
      newTagTextField.clear();
    } else
      errorLabel.setText("Tag name is too long or tag field is empty.");
  }

  private void addNewCategory()
  {
    String newCategory = newCategoryTextField.getText();
    if(!newCategory.isEmpty() && newCategory.length() <= 40)
    {
      String response = addNewProductAdminViewModel.addNewCategory(newCategory);
      errorLabel.setText(response);
      newCategoryTextField.clear();
    } else
      errorLabel.setText("Category name is too long or category field is empty.");
  }

  private void addNewProduct()
  {
    String productName = productNameTextField.getText();
    String productDescription = productDescriptionTextField.getText();
    String category = categoryBox.selectionModelProperty().get().getSelectedItem();
    ObservableList<String> tags =  checkListViewTags.getCheckModel().getCheckedItems();

    if(!productName.isEmpty() && productName.length() <= 40){
      if(!productDescription.isEmpty() && productDescription.length() <= 200){
        if(!category.isEmpty()){
          if(!tags.isEmpty()){
            ArrayList<String> parseTag = new ArrayList<>(tags);
            String response = addNewProductAdminViewModel.addNewProduct(productName, productDescription, category, parseTag);

            if(response.equals("Product added."))
              viewHandler.openAdministratorView();
            else
              errorLabel.setText("Product already exists.");
          }
          else
            errorLabel.setText("Choose at least one product tag.");
        }
        else
          errorLabel.setText("Choose product category.");
      }
      else
        errorLabel.setText("Product description is empty or longer than 200 letters.");
    }
    else
      errorLabel.setText("Product name is empty or longer than 40 letters.");
  }
}

