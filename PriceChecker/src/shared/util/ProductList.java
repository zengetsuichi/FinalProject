package shared.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A class used for creating a product list object
 * and storing products in an array.
 *
 * @author Gosia, Hadi, Karlo
 */

public class ProductList implements Serializable
{
  private ArrayList<Product> products;

  public ProductList(){
    products = new ArrayList<>();
  }

  public void addProduct(Product product){
    products.add(product);
  }

  @Override public String toString()
  {
    return "ProductList{" + "products=" + products + '}';
  }

  public ArrayList<Product> getProducts(){
    Collections.sort(products, new Comparator<Product>() {
      @Override public int compare(Product z1, Product z2) {
        if (z1.getProductId() > z2.getProductId())
          return 1;
        if (z1.getProductId() < z2.getProductId())
          return -1;
        return 0;
      }
    });
    return products;
  }

  public boolean equals(ArrayList<Product> products1){
    return products.equals(products1);
  }
}
