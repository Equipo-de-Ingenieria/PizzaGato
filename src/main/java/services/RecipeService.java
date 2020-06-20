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
import java.util.ArrayList;
import models.Ingredient;
import models.Product;
import static services.DetailService.checkUpdateCounts;
import static services.DetailService.getConnection;

/**
 *
 * @author Erick
 */
public class RecipeService {

    private static final String GET_PRODUCT_INGREDIENTS = "select rec.idIngredient, ing.code, ing.name "
            + "from `eif209_2001_p02`.recipe rec, `eif209_2001_p02`.ingredients ing "
            + "where rec.idProduct = ? and rec.idIngredient = ing.idIngredient;";

    private static final String CREATE_RECIPE = "insert into Recipe (idProduct, idIngredient) "
            + "values(?, ?)";

    public static ArrayList<Ingredient> getIngredients(int idProduct) {
        ArrayList<Ingredient> ingredients = null;
        Ingredient ingredient;

        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_PRODUCT_INGREDIENTS);) {
            stm.clearParameters();
            stm.setInt(1, idProduct);

            try (ResultSet rs = stm.executeQuery()) {
                ingredients = new ArrayList();
                while (rs.next()) {
                    ingredient = new Ingredient();
                    ingredient.setIdIngredient(rs.getInt("idIngredient"));
                    ingredient.setCode(rs.getString("code"));
                    ingredient.setName(rs.getString("name"));

                    ingredients.add(ingredient);
                }

            }

            stm.close();
            connection.close();

        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage() + " ()");
        }

        if (ingredients == null) {
            System.err.println("\n\nLa factura esta nula\n\n");
        }

        return ingredients;
    }

    public static boolean createRecipe(int idProduct, ArrayList<Ingredient> ingredients) {
        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(CREATE_RECIPE);) {
            stm.clearParameters();

            for (Ingredient in : ingredients) {
                stm.setInt(1, idProduct);
                stm.setInt(2, in.getIdIngredient());
                stm.addBatch();
            }

            int[] updateCounts = stm.executeBatch();
            checkUpdateCounts(updateCounts);

            if (updateCounts.length >= 0) {
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
//        ArrayList<Ingredient> in = IngredientService.getIngredientsDB();
////        System.out.println(in.toString());
//        //Product product = new Product("SAD", "Grande", "PapaJohns", 11005.5);
//        ArrayList<Ingredient> newIn = new ArrayList();
//        newIn.add(in.get(1));
//        newIn.add(in.get(2));
//        newIn.add(in.get(3));
//
//        product.setIngredients(newIn);
//        ProductService.createProduct(product);
//        int idProduct = ProductService.getLastID();
//        createRecipe(idProduct, newIn);
        

    }
}
