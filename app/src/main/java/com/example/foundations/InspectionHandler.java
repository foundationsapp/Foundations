package com.example.foundations;

import java.util.List;

public interface InspectionHandler {
    void showAddSubcategoryDialog(MainViewModel mainViewModel);
    void setAllCategories(List<Category> allCategories);
    void showListItemDialog(MainViewModel mainViewModel, List<SubCategory> allSubcategories, ListItem item);
    void setAllListItems(List<ListItem> allListItems);
    void setAllSubcategories(List<SubCategory> allSubcategories);
    List<Category> getAllCategories();
    List<SubCategory> getAllSubcategories();
    List<ListItem> getAllListItems();
    void showDeleteItemDialog(MainViewModel mainViewModel, ListItem item);
    void addPDF(String pdf);
}
