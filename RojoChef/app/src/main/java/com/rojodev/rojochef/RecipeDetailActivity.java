package com.rojodev.rojochef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by roannakeough on 8/3/17.
 */

public class RecipeDetailActivity extends MainActivity {
    private static final String TAG = "RecipeDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activateToolbar(true); //enable home button

        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra(RECIPE_TRANSFER);
        if (recipe == null) {
            Log.d(TAG, "onCreate: recipe is NULL!!!");
        }
        if(recipe != null) {
            TextView recipeTitle = (TextView) findViewById(R.id.recipe_title);
            recipeTitle.setText(recipe.getTitle());

            TextView recipeIngredients = (TextView) findViewById(R.id.recipe_ingredients);
            //iterate through array and get all instructions
            List<Ingredient> ingredientsArray = recipe.getIngredientList();
            StringBuilder ingredients = new StringBuilder();

            if(ingredientsArray.size() > 0) {
                for(int i = 0; i < ingredientsArray.size(); i++) {
                    //grab one ingredient at a time, build string with amount measurement name + newline
                    ingredients.append(String.valueOf(ingredientsArray.get(i).amount))
                            .append(" ")
                            .append(ingredientsArray.get(i).measurement)
                            .append(" ")
                            .append(ingredientsArray.get(i).name)
                            .append("\n");
                    Log.d(TAG, "onCreate: ingredients " + ingredients.toString());
                }
            }
            recipeIngredients.setText(ingredients.toString());

            TextView recipeInstructions = (TextView) findViewById(R.id.recipe_instructions);
            //iterate through array and get all instructions
            List<String> instructionsArray = recipe.getInstructionsList();
            StringBuilder instructions = new StringBuilder();
            //grab each instruction and separate with a newline
            if(instructionsArray.size() > 0) {
                for (String instruction : instructionsArray) {
                    if (instruction != null) {
                        instructions.append(instruction).append("\n\n");
                    }
                }
            }

            recipeInstructions.setText(instructions.toString());

            ImageView photoImage = (ImageView) findViewById(R.id.recipe_image);
            String photoUrl = null;
            try {
                photoUrl = recipe.getMedia().getPhotoUrl();
            } catch (Exception e) {
                //either there is no image, or can't get it
                Log.e(TAG, "onCreate: can't get full size image");
            }

            Picasso.with(this).load(photoUrl)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(photoImage);
        }
    }
}
