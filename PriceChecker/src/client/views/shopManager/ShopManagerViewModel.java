package client.views.shopManager;

import client.clientmodel.shopManagerModel.ShopManagerModel;

public class ShopManagerViewModel
{
  private ShopManagerModel shopManagerModel;

  public ShopManagerViewModel(ShopManagerModel shopManagerModel)
  {
    this.shopManagerModel = shopManagerModel;

  }

  public String getLoggedInUser()
  {
    return shopManagerModel.getLoggedInUser();
  }
}
