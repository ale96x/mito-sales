package com.mitocode.repo;

import com.mitocode.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepo extends IGenericRepo<Category,Integer> {
    //Queries derivados (Derived Queries)
    //select * from Category c where c.name = 'COMPUTERS';
    List<Category> findByName(String name);

    List<Category> findByNameLike(String name);

    //%XY% -> findByNameContains
    List<Category> findByNameContains(String name);

    //XYZ% -> findByNameStartsWith
    //%XYZ -> findByNameEndsWith
    List<Category> findByNameStartsWith(String name);

    List<Category> findByNameAndEnabled(String name, Boolean enabled);

    List<Category> findByNameOrEnabled(String name, Boolean enabled);

    List<Category> findByEnabledTrue();

    Category findOneByName(String name);

    List<Category> findTop3ByName(String name);

    List<Category> findByNameIs(String name);

    List<Category> findByNameIsNot(String name);

    List<Category> findByNameIsNull();

    List<Category> findByNameIsNotNull();

    List<Category> findByNameEquals(String name);

    List<Category> findByNameEqualsIgnoreCase(String name);

    List<Category> findByIdCategoryLessThan(Integer id); //<

    List<Category> findByIdCategoryLessThanEqual(Integer id); //<=

    List<Category> findByIdCategoryGreaterThan(Integer id); //>

    List<Category> findByIdCategoryGreaterThanEqual(Integer id); //>=

    List<Category> findByIdCategoryBetween(Integer id1, Integer id2);


    //JPQL Java Persistence Query Language
    @Query("FROM Category c WHERE c.name = ?1 AND c.description LIKE %?2%")
    List<Category> getByNameAndDescription1(String name, String description);

    @Query("FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    List<Category> getByNameAndDescription2(@Param("name") String x, @Param("description") String y);

    @Query("select new Category(c.name, c.description) from Category c") //No va a devolver todo porque falta crear un constructor con esos miembros
    List<Category> getByNameAndDescription3();

    ////////////////////////////////////////////////////////////////////////////////////////

    //SQL NativeQuery
    @Query(value="select * from category where name = :name",nativeQuery = true)
    List<Category> getByNameSQL(@Param("name") String name);

    @Modifying //para insert, update, delete
    @Query(value = "update from category set name=:name where id=:id",nativeQuery = true)
    Integer updateNameById(@Param("name") Integer id,@Param("id") String name);

}
