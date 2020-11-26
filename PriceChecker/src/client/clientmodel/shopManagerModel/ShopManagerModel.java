package client.clientmodel.shopManagerModel;

import shared.util.PropertyChangeSubject;

public interface ShopManagerModel extends PropertyChangeSubject
{
  String getLoggedInUser();
}
