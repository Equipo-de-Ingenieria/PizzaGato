/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Product;
import static services.DetailService.getConnection;

/**
 *
 * @author Erick
 */
public class ProductService {

    private static final String GET_PRODUCT = "select pro.idProduct, pro.code, pro.size, pro.description, pro.price, pro.type, pro.imgpath "
            + "from `eif209_2001_p02`.products pro "
            + "where pro.idProduct = ?;";

    private static final String GET_LASTID = "select max(idProduct) from `eif209_2001_p02`.products";

    private static final String GET_PRODUCTS = "select pro.idProduct, pro.code, pro.size, pro.description, pro.price, pro.type, pro.imgpath "
            + "from `eif209_2001_p02`.products pro;";
    
     private static final String GET_PRODUCTS_BY_TYPE = "select pro.idProduct, pro.code, pro.size, pro.description, pro.price, pro.type, pro.imgpath "
            + "from `eif209_2001_p02`.products pro "
             + "where pro.type = ?;";
     
    private static final String CREATE_PRODUCT = "insert into `eif209_2001_p02`.products (code, size, description, price) "
            + "values (?, ?, ?, ?);";

    public static void checkUpdateCounts(int updateCounts) {

        if (updateCounts >= 0) {
            System.out.println("OK; updateCount=" + updateCounts);
        } else if (updateCounts == Statement.SUCCESS_NO_INFO) {
            System.out.println("OK; updateCount=Statement.SUCCESS_NO_INFO");
        } else if (updateCounts == Statement.EXECUTE_FAILED) {
            System.out.println("Failure; updateCount=Statement.EXECUTE_FAILED");
        }

    }

    public static Product getProductDB(int idProduct) {
        Product product = null;

        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_PRODUCT);) {
            stm.clearParameters();

            stm.setInt(1, idProduct);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setIdProduct(rs.getInt("idProduct"));
                    product.setCode(rs.getString("code"));
                    product.setSize(rs.getString("size"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setType(rs.getString("type"));
                    product.setImgPath("imgpath");
                }
            }
            stm.close();
            connection.close();
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return product;
    }

    public static int getLastID() {
        int idProduct = 0;

        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_LASTID);) {
            stm.clearParameters();

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    idProduct = rs.getInt(1);
                }
            }
            stm.close();
            connection.close();
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return idProduct;
    }

    public static Product getProduct(int idProduct) {
        Product product = getProductDB(idProduct);

        if (product != null) {
            product.setIngredients(RecipeService.getIngredients(idProduct));
        }

        return product;
    }

    public static ArrayList<Product> getProductsDB() {
        ArrayList<Product> products = null;
        Product product;

        try (Connection connection = getConnection();
                PreparedStatement pstm = connection.prepareStatement(GET_PRODUCTS);) {
            pstm.clearParameters();

            try (ResultSet rs = pstm.executeQuery()) {
                products = new ArrayList();
                while (rs.next()) {
                    product = new Product();
                    product.setIdProduct(rs.getInt(1));
                    product.setCode(rs.getString(2));
                    product.setSize(rs.getString(3));
                    product.setDescription(rs.getString(4));
                    product.setPrice(rs.getDouble(5));
                    product.setType(rs.getString(6));
                    product.setImgPath(rs.getString(7));

                    products.add(product);
                }

            }

            pstm.close();
            connection.close();

        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage() + " ()");
        }

        if (products == null) {
            System.err.println("\n\nLa factura esta nula\n\n");
        }

        return products;
    }
    
    public static ArrayList<Product> getProductsByTypeDB(String type) {
        ArrayList<Product> products = null;
        Product product;

        try (Connection connection = getConnection();
                PreparedStatement pstm = connection.prepareStatement(GET_PRODUCTS);) {
            pstm.clearParameters();
            pstm.setString(1, type);

            try (ResultSet rs = pstm.executeQuery()) {
                products = new ArrayList();
                while (rs.next()) {
                    product = new Product();
                    product.setIdProduct(rs.getInt(1));
                    product.setCode(rs.getString(2));
                    product.setSize(rs.getString(3));
                    product.setDescription(rs.getString(4));
                    product.setPrice(rs.getDouble(5));
                    product.setType(rs.getString(6));
                    product.setImgPath(rs.getString(7));

                    products.add(product);
                }

            }

            pstm.close();
            connection.close();

        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage() + " ()");
        }

        if (products == null) {
            System.err.println("\n\nLa factura esta nula\n\n");
        }

        return products;
    }

    public static ArrayList<Product> getProducts() {
        ArrayList<Product> products = getProductsDB();
        if (products != null && !products.isEmpty()) {
            products.forEach((product) -> {
                product.setIngredients(RecipeService.getIngredients(product.getIdProduct()));
            });
        }

        return products;

    }
    
       public static ArrayList<Product> getProductsByType(String type) {
        ArrayList<Product> products = getProductsByTypeDB(type);
        if (products != null && !products.isEmpty()) {
            products.forEach((product) -> {
                product.setIngredients(RecipeService.getIngredients(product.getIdProduct()));
            });
        }

        return products;

    }

    public static boolean createProduct(Product product) {
        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(CREATE_PRODUCT);) {
            stm.clearParameters();

            stm.setString(1, product.getCode());
            stm.setString(2, product.getSize());
            stm.setString(3, product.getDescription());
            stm.setDouble(4, product.getPrice());

            int updateCounts = stm.executeUpdate();
            checkUpdateCounts(updateCounts);

            if (updateCounts >= 0) {
                return true;
            }

            stm.close();
            connection.close();

        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage() + " createDetails()");

        }
        return false;

    }

    public static void main(String[] args) {
        System.out.println(getProducts().toString());
    }

}
