package client.views.addNewProductShopManager;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckListView;
import org.controlsfx.control.SearchableComboBox;
import shared.util.Product;

import java.util.ArrayList;

public class AddNewProductShopManagerController implements ViewController {

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

    @FXML
    private SearchableComboBox<String> productBox;

    @FXML
    private Button editProdButton;

    @FXML
    private TextField priceTextField;

    @FXML
    private Button modifyProductButton;

    @FXML
    private Label loggedInAs;


    private ViewHandler viewHandler;
    private AddNewProductShopManagerViewModel addNewProductShopManagerViewModel;
    private String username;

    /**
     * Method used; for initializing the controller, loading the initial
     * data (categories and tags) and binding the category box and check list view
     * to the observable lists from the view model.
     *
     * @author Piotr
     */

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        addNewProductShopManagerViewModel = viewModelFactory.getAddNewProductShopManagerViewModel();
        categoryBox.setItems(addNewProductShopManagerViewModel.getAllProductCategories());
        checkListViewTags.setItems(addNewProductShopManagerViewModel.getAllTags());

        username = addNewProductShopManagerViewModel.getLoggedInUser();
        loggedInAs.setText(username);

        addNewProductShopManagerViewModel.loadDataForProductsNames();

        ArrayList<String> productNames = new ArrayList<>();
        ArrayList<String> productNamesForSpecificManager = new ArrayList<>();
        ObservableList<String> productNamesStrings = FXCollections.observableArrayList();

        for (int i = 0; i < addNewProductShopManagerViewModel.getAllProducts().size(); i++) {
            productNames.add(addNewProductShopManagerViewModel.getAllProducts().get(i).getProductName());
        }
        for (int i = 0; i < addNewProductShopManagerViewModel.getAllProductsInfoForSpecificManager().size(); i++) {
            productNamesForSpecificManager.add(addNewProductShopManagerViewModel.getAllProductsInfoForSpecificManager().get(i).getProductName());
        }
                Platform.runLater(() -> {
        if(productNames.size() == productNamesForSpecificManager.size()) {
            productNamesStrings.setAll("");
        }
        else {
            for (int i = 0; i < productNamesForSpecificManager.size(); i++) {
                if (productNames.contains(productNamesForSpecificManager.get(i))) {
                    productNames.remove(productNamesForSpecificManager.get(i));
                }
            }
            productNamesStrings.setAll(productNames);
        }
                productBox.setItems(productNamesStrings);
        });
    }


    /**
     * Method used for handling events from UI
     *
     * @author Piotr
     */
    @Override
    public void handleClickMe(ActionEvent actionEvent) {
        if (actionEvent.getSource() == goBackBtn) {
            viewHandler.openShopManagerView();
        } else if (actionEvent.getSource() == editProdButton) {
            editProd();
        } else if (actionEvent.getSource() == addProductBtn) {
            addNewProduct();
        } else if (actionEvent.getSource() == addNewTagBtn) {
            addNewTag();
        } else if (actionEvent.getSource() == addNewCategoryBtn) {
            addNewCategory();
        } else if (actionEvent.getSource() == modifyProductButton) {
            getThisProduct();

        }
    }


    public void handleClickMe1(ActionEvent actionEvent) {
        if (actionEvent.getSource() == goBackBtn) {
            viewHandler.openShopManagerView();
        } else if (actionEvent.getSource() == editProdButton) {
            editProd();
        } else if (actionEvent.getSource() == addProductBtn) {
            addNewProduct();
        } else if (actionEvent.getSource() == addNewTagBtn) {
            addNewTag();
        } else if (actionEvent.getSource() == addNewCategoryBtn) {
            addNewCategory();
        } else if (actionEvent.getSource() == modifyProductButton) {
            getThisProduct();

        }
    }

    /**
     * Method used for selecting the existing the product and setting fields
     *
     * @author Piotr
     */

    private void getThisProduct() {

        checkListViewTags.getCheckModel().clearChecks();

        String selected = productBox.selectionModelProperty().get().getSelectedItem();


        for (int i = 0; i < addNewProductShopManagerViewModel.getAllProducts().size(); i++) {
            if (addNewProductShopManagerViewModel.getAllProducts().get(i).getProductName().equals(selected)) {
                Product productSelected = addNewProductShopManagerViewModel.getAllProducts().get(i);
                String productName = productSelected.getProductName();
                productNameTextField.setText(String.valueOf(productName));
                String description = productSelected.getDescription();
                productDescriptionTextField.setText(description);
                int price = productSelected.getPrice();
                String category = productSelected.getCategory();
                priceTextField.setText("" + price);

                //Loop for getting category of selected product

                for (int j = 0; j < categoryBox.getItems().size(); j++) {
                    if (categoryBox.getItems().get(j).equals(category)) {
                        Platform.runLater(() -> categoryBox.getSelectionModel().select(productSelected.getCategory()));
                    }
                }

                //Loop for checking the tags for selected product
                ObservableList<String> tags = addNewProductShopManagerViewModel.getAllTagsById(productSelected.getProductId());

                for (int k = 0; k < addNewProductShopManagerViewModel.getAllTags().size(); k++) {
                    for (int j = 0; j < tags.size(); j++) {
                        if (tags.get(j).equals(addNewProductShopManagerViewModel.getAllTags().get(k))) {
                            checkListViewTags.getCheckModel().check(k);
                        }
                    }
                }

                productNameTextField.setDisable(true);
                productDescriptionTextField.setDisable(true);
                categoryBox.setDisable(true);
                checkListViewTags.setDisable(true);
                addProductBtn.setDisable(true);


                break;
            }
        }
    }

    private void addNewCategory() {
        String newCategory = newCategoryTextField.getText();
        if (!newCategory.isEmpty() && newCategory.length() <= 40) {
            String response =  addNewProductShopManagerViewModel.addNewCategory(newCategory);
            errorLabel.setText(response);
            newCategoryTextField.clear();
        } else
            errorLabel.setText("Category name is too long or category field is empty.");

    }

    private void addNewTag() {
        String newTag = newTagTextField.getText();
        if (!newTag.isEmpty() && newTag.length() <= 40) {
            newTagTextField.clear();
        } else
            errorLabel.setText("Tag name is too long or tag field is empty.");
    }


    /**
     * Method used for adding the new Product to ShopManager list
     *
     * @author Piotr
     */
    private void addNewProduct() {
        String productName = productNameTextField.getText();
        String productDescription = productDescriptionTextField.getText();
        String category = categoryBox.selectionModelProperty().get().getSelectedItem();
        ObservableList<String> tags = checkListViewTags.getCheckModel().getCheckedItems();
        int price = Integer.parseInt(priceTextField.getText());

        if (!productName.isEmpty() && productName.length() <= 40) {
            if (!productDescription.isEmpty() && productDescription.length() <= 200) {
                if (!category.isEmpty()) {
                    if (price>=0){
                    if (!tags.isEmpty()) {
                        if (!priceTextField.getText().isEmpty() && price > 0) {


                            ArrayList<String> parseTag = new ArrayList<>(tags);
                            String response = addNewProductShopManagerViewModel.addNewProduct(productName, productDescription, category, parseTag, price);

                            if (response.equals("Product added."))
                                viewHandler.openShopManagerView();
                            else {
                                errorLabel.setText(response);
                            }
                        } else {
                            errorLabel.setText("Product price is empty, 0 , or less than 0 ");
                        }
                    } else
                        errorLabel.setText("Choose at least one product tag.");
                    }
                    else
                    {
                        errorLabel.setText("Price cannot be negative or zero.");
                    }
                } else
                    errorLabel.setText("Choose product category.");
            } else
                errorLabel.setText("Product description is empty or longer than 200 letters.");
        } else
            errorLabel.setText("Product name is empty or longer than 40 letters.");
    }



    /**
     * Method used for adding the existing Product to ShopManager list with different price
     *
     * @author Piotr
     */
    private void editProd() {

        String selected = productBox.selectionModelProperty().get().getSelectedItem();


        for (int i = 0; i < addNewProductShopManagerViewModel.getAllProducts().size(); i++) {
            if (addNewProductShopManagerViewModel.getAllProducts().get(i).getProductName().equals(selected)) {
                Product productSelected = addNewProductShopManagerViewModel.getAllProducts().get(i);

                int userId = addNewProductShopManagerViewModel.getUserId(username);
                int productId = productSelected.getProductId();
                String productName = productNameTextField.getText();
                String productDescription = productDescriptionTextField.getText();
                String category = categoryBox.selectionModelProperty().get().getSelectedItem();
                String price = priceTextField.getText();
                ObservableList<String> tags = checkListViewTags.getCheckModel().getCheckedItems();

                if (!productName.isEmpty() && productName.length() <= 40) {
                    if (!productDescription.isEmpty() && productDescription.length() <= 200) {
                        if (!category.isEmpty()) {
                            if (!price.isEmpty() || Integer.parseInt(price)<=0) {
                                if (!tags.isEmpty()) {
                                    ArrayList<String> parseTag = new ArrayList<>(tags);
                                    String response = addNewProductShopManagerViewModel.editNewProduct(userId, Integer.parseInt(price), productId);

                                    if (response.equals("Price added"))
                                        viewHandler.openShopManagerView();
                                    else
                                        errorLabel.setText(response);
                                } else {
                                    errorLabel.setText("Choose at least one product tag.");
                                }
                            } else {
                                errorLabel.setText("Price cannot be negative or zero.");
                            }
                        } else {
                            errorLabel.setText("Choose product category.");
                        }
                    } else {
                        errorLabel.setText("Product description is empty or longer than 200 letters.");
                    }
                } else {
                    errorLabel.setText("Product name is empty or longer than 40 letters.");
                }
            }
        }
    }
}







