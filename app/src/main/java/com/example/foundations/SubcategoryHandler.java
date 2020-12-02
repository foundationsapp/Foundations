package com.example.foundations;

import java.util.List;

public interface SubcategoryHandler {
    void showAddSubcategoryDialog(MainViewModel mainViewModel);
    void setAllCategories(List<Category> allCategories);
    void showListItemDialog(MainViewModel mainViewModel, List<SubCategory> allSubcategories);
}
