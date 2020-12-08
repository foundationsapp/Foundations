package com.example.foundations;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT;

public class SummaryFragment extends Fragment {

    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static final Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static final Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    private static final String TAG = "SummaryFragment";
    private final FragmentSwitcher fragmentSwitcher;
    private final InspectionHandler inspectionHandler;
    private final List<ListItem> allListItems;
    private final List<Category> allCategories;
    private final List<SubCategory> allSubcategories;
    private final Report currentReport;

    public SummaryFragment(FragmentSwitcher fragmentSwitcher, InspectionHandler inspectionHandler){
        this.fragmentSwitcher = fragmentSwitcher;
        this.inspectionHandler = inspectionHandler;
        allListItems = inspectionHandler.getAllListItems();
        allCategories = inspectionHandler.getAllCategories();
        allSubcategories = inspectionHandler.getAllSubcategories();
        currentReport = fragmentSwitcher.getCurrentReport();
    }


    private void createPDF() throws IOException, DocumentException {

        String currentCategoryName = null;
        int currentCategoryId = -1;
        String currentSubcategoryName = null;
        int currentSubcategoryId = -1;
        int categorySectionCounter = 0;
        int subcategorySectionCounter = 0;
        int itemSectionCounter = 0;
        Chapter currentCategory = null;
        Section currentSubcategory = null;

        String file_name = "Foundations Report " + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis()) + ".pdf";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String file_path = storageDir.getAbsolutePath() + file_name;
        Document document = new Document();
        addMetaData(document);
        PdfWriter.getInstance(document, new FileOutputStream(file_path));
        document.open();

        if (allListItems != null && allListItems.size() > 0) {
            while (allListItems.size() > 0) {
                ListItem current = allListItems.remove(0);
                if (current.getCategoryId() != currentCategoryId) {
                    if (currentCategory != null) {
                        currentSubcategory.add(new Paragraph(" "));
                        document.add(currentCategory);
                        currentCategory = null;
                        currentSubcategory = null;
                    }
                    currentCategoryId = current.getCategoryId();
                    categorySectionCounter += 1;
                    subcategorySectionCounter = 0;
                    for (int i = 0; i < allCategories.size(); i++) {
                        if (currentCategoryId == allCategories.get(i).getCategoryId()) {
                            currentCategoryName = allCategories.get(i).getTitle();
                            break;
                        }
                    }
                    currentCategory = createNewCategory(currentCategoryName, categorySectionCounter);
                    System.out.println(categorySectionCounter + " " + currentCategoryName);
                }
                if (current.getSubCategoryId() != currentSubcategoryId) {
                    currentSubcategoryId = current.getSubCategoryId();
                    subcategorySectionCounter += 1;
                    itemSectionCounter = 0;
                    for (int i = 0; i < allSubcategories.size(); i++) {
                        if (currentSubcategoryId == allSubcategories.get(i).getSubCategoryId()) {
                            currentSubcategoryName = allSubcategories.get(i).getTitle();
                            break;
                        }
                    }
                    currentSubcategory = createNewSubcategory(currentCategory, currentSubcategoryName);
                    System.out.println("     " + categorySectionCounter + "." + subcategorySectionCounter + " " + currentSubcategoryName);
                }
                itemSectionCounter += 1;
                addItemsToSection(currentSubcategory, current);
                System.out.println("         " + categorySectionCounter + "." + subcategorySectionCounter + "." + itemSectionCounter);
                System.out.println("                    " + current.getNotes() + current.getPhotos());
                if (allListItems.size() == 0) {
                    currentSubcategory.add(new Paragraph(" "));
                    document.add(currentCategory);
                    currentCategory = null;
                    currentSubcategory = null;
                }
            }
            document.close();
            inspectionHandler.addPDF(file_path);
            Toast.makeText(getContext(), "PDF Report has been generated.", Toast.LENGTH_LONG).show();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary, container,false);

        EditText buyerFirstName = view.findViewById(R.id.pdf_buyer_first_name);
        buyerFirstName.setText(currentReport.getBuyerFirstName());
        EditText buyerLastName = view.findViewById(R.id.pdf_buyer_last_name);
        buyerLastName.setText(currentReport.getBuyerLastName());
        EditText sellerFirstName = view.findViewById(R.id.pdf_seller_first_name);
        sellerFirstName.setText(currentReport.getSellerFirstName());
        EditText sellerLastName = view.findViewById(R.id.pdf_seller_last_name);
        sellerLastName.setText(currentReport.getSellerLastName());
        EditText address = view.findViewById(R.id.pdf_site_address);
        address.setText(currentReport.getStreet());
        EditText state = view.findViewById(R.id.pdf_site_state);
        state.setText(currentReport.getState());
        EditText city = view.findViewById(R.id.pdf_site_city);
        city.setText(currentReport.getCity());
        EditText zipCode = view.findViewById(R.id.pdf_site_zip);
        zipCode.setText(currentReport.getZip());

        TextView buyerInformation = view.findViewById(R.id.pdf_buyer_information);
        TextView sellerInformation = view.findViewById(R.id.pdf_seller_information);
        TextView siteInformation = view.findViewById(R.id.pdf_site_information);

        Button save=(Button) view.findViewById(R.id.save_pdf);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, 1000);
            } else{
                    try {
                        createPDF();
                    } catch (IOException | DocumentException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addTitlePage(Document document) throws IOException, DocumentException {
        Image logo = Image.getInstance("drawable/logo.png");
        logo.scaleToFit(400, 200);
        logo.setAlignment(ALIGN_CENTER);
        logo.setSpacingAfter(5);
        logo.setSpacingBefore(5);
        document.add(logo);
        Paragraph title = new Paragraph(getString(R.string.title_page_title));
        title.setAlignment(ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);
        Paragraph preparedFor = new Paragraph(getString(R.string.prepedfor));
        preparedFor.setAlignment(ALIGN_CENTER);
        document.add(preparedFor);
        Paragraph buyer = new Paragraph(currentReport.getBuyerFirstName() + " " + currentReport.getBuyerLastName());
        buyer.setAlignment(ALIGN_CENTER);
        buyer.setSpacingAfter(5);
        document.add(buyer);
        Image propertyPhoto = Image.getInstance(currentReport.getPropertyPhoto());
        propertyPhoto.scaleToFit(400, 200);
        propertyPhoto.setAlignment(ALIGN_CENTER);
        propertyPhoto.setSpacingAfter(5);
        document.add(propertyPhoto);
        Paragraph propertyInspected = new Paragraph("PROPERTY INSPECTED:");
        propertyInspected.setAlignment(ALIGN_CENTER);
        document.add(propertyInspected);
        Paragraph address = new Paragraph(currentReport.getStreet() + "\n" + currentReport.getCity() + ", " + currentReport.getState() + " " + currentReport.getZip(),
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
        address.setAlignment(ALIGN_CENTER);
        document.add(address);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        Paragraph inspectionDate = new Paragraph("Date of Inspection: " + dtf.format(now));
        inspectionDate.setAlignment(ALIGN_CENTER);
        document.add(inspectionDate);
        document.newPage();

    }

    private static void addMetaData(Document document) {
        document.addTitle("Home Inspection By Foundations");
        document.addKeywords("Inspection, Report, PDF, Foundations, iText");
        document.addAuthor("Foundations");
        document.addCreator("Foundations");
    }

    private Chapter createNewCategory(String categoryName, int sectionNumber) {
        Anchor anchor = new Anchor(categoryName, catFont);
        anchor.setName("Category #" + sectionNumber);
        Chapter chapter = new Chapter(new Paragraph(anchor), sectionNumber);
        chapter.setNumberStyle(NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
        chapter.setTriggerNewPage(false);
        return chapter;
    }

    private Section createNewSubcategory(Chapter categoryChapter, String subCategoryName) {
        Paragraph subcategoryParagraph = new Paragraph(subCategoryName, subFont);
        Section section = categoryChapter.addSection(subcategoryParagraph);
        section.setIndentationLeft(10);
        section.setNumberStyle(NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
        return section;
    }

    private void addItemsToSection(Section currentSubcategory, ListItem item) throws IOException, BadElementException {
        Section section = currentSubcategory.addSection(new Paragraph(item.getNotes()));
        section.setIndentationLeft(20);
        if (item.getPhotos() != null) {
            Image img = Image.getInstance(item.getPhotos());
            img.setSpacingAfter(5);
            img.setSpacingBefore(5);
            img.scaleToFit(400, 200);
            section.add(img);
        }
        section.setNumberStyle(NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
    }
}
