package com.morethanheroic.swords.recipe.repository.domain;

import com.morethanheroic.swords.recipe.repository.dao.RecipeDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecipeMapper {

    @Select("SELECT * FROM recipe WHERE user_id = #{user_id} AND recipe_id = #{recipe_id}")
    RecipeDatabaseEntity findRecipe(@Param("user_id") int userId, @Param("recipe_id") int recipeId);

    @Insert("INSERT INTO recipe SET user_id = #{user_id}, recipe_id = #{recipe_id}")
    void insertRecipe(@Param("user_id") int userId, @Param("recipe_id") int recipeId);

    @Select("SELECT * FROM recipe WHERE user_id = #{user_id}")
    List<RecipeDatabaseEntity> findRecipes(@Param("user_id") int userId);
}
