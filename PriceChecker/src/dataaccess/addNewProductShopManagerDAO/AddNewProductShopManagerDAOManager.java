package dataaccess.addNewProductShopManagerDAO;

import dataaccess.DatabaseConnection;
import shared.util.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddNewProductShopManagerDAOManager implements AddNewProductShopManagerDAO {

    /**
     * Class implementing the data access interface. Used for requesting data from
     * the database.
     *
     * Providing methods for; getting all product categories, getting all product
     * tags, adding new products to the database, adding existing product to the shop manager price table,adding new categories and adding
     * new tags for the products to the database.
     *
     * @author Piotr
     */

    private DatabaseConnection databaseConnection;

    public AddNewProductShopManagerDAOManager() throws SQLException
    {
        databaseConnection = DatabaseConnection.getInstance();
    }


    @Override
    public String addNewProduct(String clientUsername, String productName, String productDescription, String category, ArrayList<String> parseTag, int price) throws SQLException {

            try (Connection connection = databaseConnection.getConnection()) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM product where productName = ?");
                statement.setString(1, productName);
                ResultSet resultSet = statement.executeQuery();
                Product product = null;
                while (resultSet.next()) {
                    int productId = resultSet.getInt("productid");
                    String name = resultSet.getString("productname");
                    String description = resultSet.getString("productdescription");
                    String categoryName = resultSet.getString("categoryname");
                    product = new Product(productId, name, description, categoryName);
                }

                if (!(product == null)) {
                    String response = checkIfInPriceTable(product.getProductId(), price, clientUsername);
                    if(response.equals("Product added")){
                        return response;
                    }
                    else{
                        return response;
                    }
                } else {
                    int id = addToTheProductTable(productName, productDescription, category);
                    addToTheProductTagTable(id, parseTag);
                    int userId = getUserIdByUsername(clientUsername);
                    addThePriceToTheProduct(userId, id, price);
                    return "Product added.";
                }
            }
        }



    private int getUserIdByUsername(String clientUsername) throws SQLException{
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT userid from users where username = ?");
            statement.setString(1,clientUsername);
            ResultSet resultSet = statement.executeQuery();
            int userId = 0;
            while (resultSet.next()){
                userId = resultSet.getInt("userid");
            }
            return userId;
        }
    }



    private String checkIfInPriceTable(int productId, int price, String clientUsername) throws SQLException {
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT productid, price, price.userid\n" +
                    "FROM price\n" +
                    "INNER JOIN users\n" +
                    "ON price.userid = users.userid where username = ? AND productid = ?");
            statement.setString(1, clientUsername);
            statement.setInt(2, productId);;
            ResultSet resultSet = statement.executeQuery();
            int priceInTheTable = 0;
            int userId = 0;
            int productId1 = 0;
            while (resultSet.next()) {
                productId1 += resultSet.getInt("productid");
                priceInTheTable += resultSet.getInt("price");
                userId += resultSet.getInt("userid");
            }
            if(priceInTheTable == 0){
                addThePriceToTheProduct(userId, productId, price);
                return "Product added";
            }
            else if(price == priceInTheTable){
                return "Specified product already exists.";
            }
            else{
                updatePriceInTheTable(userId, price);
                return "Product added.";
            }
        }
    }



    private void updatePriceInTheTable(int userId, int price) throws SQLException {
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE price set price = ? where userid = ? ");
            statement.setInt(1, price);
            statement.setInt(2, userId);
            statement.executeUpdate();
        }
    }

    private void addThePriceToTheProduct(int userId, int productId, int price) throws SQLException {
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT into price(productid, price, userid) values" +
                    " (?,?,?) ");
            statement.setInt(1, productId);
            statement.setInt(2, price);
            statement.setInt(3, userId);
            statement.executeUpdate();
        }
    }


    @Override
    public ArrayList<String> getAllTags() throws SQLException {
            try (Connection connection = databaseConnection.getConnection()) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM tag");
                ResultSet resultSet = statement.executeQuery();
                ArrayList<String> tags = new ArrayList<>();
                while (resultSet.next()) {
                    String tagName = resultSet.getString("tagname");
                    tags.add(tagName);
                }
                return tags;
            }
        }


    @Override
    public ArrayList<String> getAllProductCategories() throws SQLException {

            try (Connection connection = databaseConnection.getConnection()) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM category");
                ResultSet resultSet = statement.executeQuery();
                ArrayList<String> category = new ArrayList<>();
                while (resultSet.next()) {
                    String categoryname = resultSet.getString("categoryname");
                    category.add(categoryname);
                }
                return category;
            }
        }

    @Override
    public ArrayList<Product> getAllProducts() throws SQLException {
        {
            try (Connection connection = databaseConnection.getConnection())
            {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM product" );


                ResultSet resultSet = statement.executeQuery();

                ArrayList<Product> products = new ArrayList<>();
                while (resultSet.next())
                {

                    int productId = resultSet.getInt("productid");
                    String name = resultSet.getString("productname");
                    String description = resultSet.getString("productdescription");
                    String categoryName = resultSet.getString("categoryname");
                    Product product = new Product(productId,name,description,categoryName);

                    products.add(product);

                }
                return products;

            }
        }
    }





    @Override
    public String addNewCategory(String newCategory) {
        return null;
    }

    @Override
    public String addNewTag(String newTag) throws SQLException {
        try (Connection connection = databaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tag where tagname = ?");
            statement.setString(1, newTag);
            ResultSet resultSet = statement.executeQuery();
            String tag = null;
            while(resultSet.next())
            {
                tag = resultSet.getString("tagname");
            }

            if(!(tag == null)){
                return "Specified tag already exists";
            }
            else{
                addNewTagNow(newTag);
                return "Tag added.";
            }
        }
    }

    @Override
    public ArrayList<Product> getAllProductsFor(String username) throws SQLException {
        {
            try (Connection connection = databaseConnection.getConnection())
            {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT product.productid, product.productname, product.productdescription, "
                                + "product.categoryname, price.price " + "from price inner join product on product.productid = "
                                + "price.productid inner join users on price.userid = users.userid where users.username = ?");
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                ArrayList<Product> products = new ArrayList<>();
                while (resultSet.next())
                {
                    int productId2 = resultSet.getInt("productid");
                    String name = resultSet.getString("productname");
                    String description = resultSet.getString("productdescription");
                    String categoryName = resultSet.getString("categoryname");
                    int price = resultSet.getInt("price");
                    Product product = new Product(productId2, name, description,
                            categoryName, price);
                    products.add(product);
                }

                return products;
            }
        }
    }

    @Override
    public String editNewProduct(int userId,int price, int productid) throws SQLException {
        {
            try (Connection connection = databaseConnection.getConnection()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO  price(userId, price, productId) VALUES (?,?,?)");
                statement.setInt(1, userId);
                statement.setInt(2, price);
                statement.setInt(3, productid);
                statement.executeUpdate();
                return "Price added";
            }
        }

    }



    @Override public ArrayList<String> getAllTagsById(int productId)
            throws SQLException
    {
        try (Connection connection = databaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("select * from producttag where productid =?");
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> tags = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt("productid");
                String tagname = resultSet.getString("tagname");
                tags.add(tagname);
            }
            return tags;
        }
    }

    @Override
    public int getUserId(String username) throws SQLException {
        try (Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where username =?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            int index= 0;
            while (resultSet.next())
            {
                 index = resultSet.getInt(1);
                System.out.println(index);
            }

            return index;
        }
    }


    private void addToTheProductTagTable(int id, ArrayList<String> parseTag) throws SQLException
            {
                try (Connection connection = databaseConnection.getConnection()){
                    for (int i = 0; i < parseTag.size(); i++)
                    {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO producttag(productid, tagname) VALUES(?,?)");
                        statement.setInt(1, id);
                        statement.setString(2, parseTag.get(i));
                        statement.executeUpdate();
                    }
                }
            }

    private int addToTheProductTable(String productName, String productDescription, String category)
            throws SQLException
    {
        try (Connection connection = databaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT into product(productname, productdescription, categoryname)  values (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, productName);
            statement.setString(2, productDescription);
            statement.setString(3, category);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next()){
                return keys.getInt("productid");
            }
        }
        return 0;
    }

    private void addNewTagNow(String newTag) throws SQLException
    {
        try (Connection connection = databaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tag (tagname) VALUES(?)");
            statement.setString(1, newTag);
            statement.executeUpdate();
        }
    }
}



