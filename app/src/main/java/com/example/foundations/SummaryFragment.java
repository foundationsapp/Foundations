package com.example.foundations;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Locale;

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
            }
            document.close();
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
                if(Build.VERSION.SDK_INT> Build.VERSION_CODES.M){
                        if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, 1000);
                    } else{
                            try {
                                createPDF();
                            } catch (DocumentException | IOException e) {
                                e.printStackTrace();
                            }
                        }                }
                else {
                    try {
                        createPDF();
                    } catch (DocumentException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        return view;

    }

    private void savepdf(){
        // Document document = new Document();
        String file = "Foundations Report " + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        String filePath = Environment.getExternalStorageDirectory()+"/"+file+".pdf";
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        try{

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            document.close();
            Toast.makeText(getActivity(), ""+ file + ".pdf" + "is saved to " + filePath , Toast.LENGTH_SHORT).show();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


    private static void addMetaData(Document document) {
        document.addTitle("Home Inspection By Foundations");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Foundations");
        document.addCreator("Foundations");
    }


    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Title of the document", catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph(
                "This document describes something which is very important ",
                smallBold));

        addEmptyLine(preface, 8);

        preface.add(new Paragraph(
                "This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
                redFont));

        document.add(preface);
        // Start a new page
        document.newPage();
    }

    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Basic Information", catFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Buyer Information", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("First Name"));
        subCatPart.add(new Paragraph("Last Name"));

        subPara = new Paragraph("Seller Information", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("First Name"));
        subCatPart.add(new Paragraph("Last Name"));

        // add a list
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Exterior", catFont);
        anchor.setName("Exterior");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Doors"));

        // now add all this to the document
        document.add(catPart);

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

    private static void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
        com.itextpdf.text.List list = new com.itextpdf.text.List(true, false, 10);
        list.add(new com.itextpdf.text.ListItem("First point"));
        list.add(new com.itextpdf.text.ListItem("Second point"));
        list.add(new com.itextpdf.text.ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
