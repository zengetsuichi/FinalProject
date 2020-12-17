package shared.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * A class used for creating a product object.
 *
 * @author Gosia, Hadi, Karlo
 */

public class Product implements Serializable
{
  private String productName;
  private int productId;
  private String description;
  private String category;
  private int price;
  private int quantity;

  public Product(int productId, String productName, String description,
      String category)
  {
    this.productName = productName;
    this.productId = productId;
    this.description = description;
    this.category = category;
  }

  public Product( int productId, String productName, String description,
      String category, int price)
  {
    this.productName = productName;
    this.productId = productId;
    this.description = description;
    this.category = category;
    this.price = price;
  }

  public Product(int quantity, int productId, String productName, String description,
      String category)
  {
    this.productName = productName;
    this.productId = productId;
    this.description = description;
    this.category = category;
    this.quantity = quantity;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }

  public String getProductName()
  {
    return productName;
  }

  public void setProductName(String productName)
  {
    this.productName = productName;
  }

  public int getProductId()
  {
    return productId;
  }

  public void setProductId(int productId)
  {
    this.productId = productId;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getCategory()
  {
    return category;
  }

  public void setCategory(String category)
  {
    this.category = category;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Product))
      return false;
    Product other = (Product) obj;
    return productName.equals(other.productName) && description.equals(other.description)
        && productId == other.productId && category.equals(other.category);
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
    return "Product{" + "productName='" + productName + '\'' + ", productId="
        + productId + ", description='" + description + '\'' + ", category='"
        + category + '\'' + ", price=" + price + '}';
  }

}
