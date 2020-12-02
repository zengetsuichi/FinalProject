package shared.util;

import java.io.Serializable;

/**
 * A class used for creating a shopPrice object
 * and shopName and price as one object.
 *
 * @author Gosia, Hadi, Karlo
 */

public class ShopPrice implements Serializable
{
  private String shopName;
  private int price;


  public ShopPrice(String shopName, int price)
  {
    this.shopName = shopName;
    this.price = price;
  }

  public String getShopName()
{
  return shopName;
}

  public void setShopName(String shopName)
  {
    this.shopName = shopName;
  }

  public int getPrice()
  {
    return price;
  }

  public void setPrice(int price)
  {
    this.price = price;
  }

  @Override public String toString()
  {
    return "ShopPrice{" + "shopName='" + shopName + '\'' + ", price=" + price
        + '}';
  }
}
