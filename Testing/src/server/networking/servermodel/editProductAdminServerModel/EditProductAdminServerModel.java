package server.networking.servermodel.editProductAdminServerModel;

import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface EditProductAdminServerModel extends PropertyChangeSubject
{
  String editProduct(String productName, String productDescription, String category, ArrayList<String> parseTag,
      int productId);
}
