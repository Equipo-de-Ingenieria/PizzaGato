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
import static services.DetailService.getConnection;

/**
 *
 * @author Erick
 */
public class IngredientService {

    private static final String GET_INGREDIENTS = "select ing.idIngredient, ing.code, ing.name "
            + "from `eif209_2001_p02`.ingredients ing";

    public static ArrayList<models.Ingredient> getIngredientsDB() {
        ArrayList<models.Ingredient> ingredients = null;
        models.Ingredient ingredient = null;

        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_INGREDIENTS);) {
            stm.clearParameters();

            try (ResultSet rs = stm.executeQuery()) {
                ingredients = new ArrayList();
                while (rs.next()) {
                    ingredient = new models.Ingredient();
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
}
