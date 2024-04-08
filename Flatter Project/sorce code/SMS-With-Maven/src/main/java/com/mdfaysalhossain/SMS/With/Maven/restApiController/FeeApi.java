package com.mdfaysalhossain.SMS.With.Maven.restApiController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.mdfaysalhossain.SMS.With.Maven.model.*;
import com.mdfaysalhossain.SMS.With.Maven.repository.IFeeCatagoryRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.IStudentAddRepo;
import com.mdfaysalhossain.SMS.With.Maven.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/fee")
@CrossOrigin("*")
public class FeeApi {

    @Autowired
    FeeService feeService;

    @Autowired
    IFeeCatagoryRepo iFeeCatagoryRepo;

    @Autowired
    IStudentAddRepo iStudentAddRepo;


    @GetMapping("")
    public List<FeeCatagoryModel> getallFee() {
        return feeService.getAllfee();
    }


    @GetMapping("/feest/{sid}")
    public List<FeeCatagoryModel> getFeeByStudentId(@PathVariable Long sid) {
        return feeService.findByStudentId(sid);
    }

    @PostMapping("")
    public ResponseEntity<FeeCatagoryModel> saveFee(@RequestBody FeeCatagoryModel feeCatagoryModel) {
        StudentAddModel studentAddModel = feeCatagoryModel.getStudentAddModel();
        if (studentAddModel == null) {
            // Handle the case when studentAddModel is null, e.g., return an error response
            return ResponseEntity.badRequest().build();
        }

        long sid = feeCatagoryModel.getSsid();
        StudentAddModel id = iStudentAddRepo.findBySid(sid);
        if (studentAddModel == null) {
            // Handle the case when the subject is not found, e.g., return an error response
            return ResponseEntity.notFound().build();
        }

        feeCatagoryModel.setSsid(sid);
        FeeCatagoryModel saveFee = feeService.saveFee(feeCatagoryModel);
        return ResponseEntity.ok(saveFee);
    }



//    @PostMapping("/save")
//    public ResponseEntity<FeeCatagoryModel> saveFee(@RequestBody FeeCatagoryModel feeCatagoryModel) {
//        StudentAddModel studentAddModel = feeCatagoryModel.getStudentAddModel();
//        if (studentAddModel == null) {
//            // Handle the case when studentAddModel is null, e.g., return an error response
//            return ResponseEntity.badRequest().build();
//        }
//
//        long sid = feeCatagoryModel.getSsid();
//        StudentAddModel studentAddModelFromDb = iStudentAddRepo.findBySid(sid);
//        if (studentAddModelFromDb == null) {
//
//            return ResponseEntity.notFound().build();
//        }
//
//        feeCatagoryModel.setStudentAddModel(studentAddModelFromDb);
//        FeeCatagoryModel savedFee = feeService.saveFee(feeCatagoryModel);
//        return ResponseEntity.ok(savedFee);
//    }












//    @PostMapping("")
//    public ResponseEntity<FeeCatagoryModel> saveFee(@RequestBody FeeCatagoryModel feeCatagoryModel) {
//        StudentAddModel studentAddModel = feeCatagoryModel.getStudentAddModel();
//        if (studentAddModel == null) {
//            // Handle the case when studentAddModel is null, e.g., return an error response
//            return ResponseEntity.badRequest().build();
//        }
//
//        long sid = feeCatagoryModel.getSsid();
//        StudentAddModel existingStudent = iStudentAddRepo.findBySid(sid);
//        if (existingStudent == null) {
//            // Handle the case when the student is not found, e.g., return an error response
//            return ResponseEntity.notFound().build();
//        }
//
//        // Set the student object retrieved from the database
//        feeCatagoryModel.setStudentAddModel(existingStudent);
//
//        // Save the fee category model
//        FeeCatagoryModel saveFee = feeService.saveFee(feeCatagoryModel);
//        return ResponseEntity.ok(saveFee);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFee(@PathVariable("id") long id) {
        boolean hasData = iFeeCatagoryRepo.existsById(id);
        if (hasData) {
            iFeeCatagoryRepo.deleteById(id);
            return new ResponseEntity<>("Data Are Delete successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data are not found", HttpStatus.BAD_REQUEST);
    }









    @GetMapping("/generateReceipt")
    public ResponseEntity<byte[]> generateDepositReceiptPdf() {
        try {

            FeeCatagoryModel lastFeeTransation = iFeeCatagoryRepo.findTopByOrderByPaymentDateDesc();

            if (lastFeeTransation != null) {
                byte[] pdfBytes = generatePdfBytes(lastFeeTransation);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "deposit_receipt.pdf");

                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }





















//    private byte[] generatePdfBytes(DepositTransaction withdrawTransaction) throws IOException, DocumentException {
//        Document document = new Document();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PdfWriter writer = PdfWriter.getInstance(document, baos);
//
//        WithdrawTransactionRestController.HeaderFooter event = new DepositTransactionRestController().HeaderFooter();
//        writer.setPageEvent(event);
//
//        document.open();
//
//        document.add(new Paragraph("                                                          Unique Bank Limited"));
//        document.add(new Paragraph("                                                             Withdraw Receipt"));
//        document.add(new Paragraph("               Account Name:                                                      " + withdrawTransaction.getFirstName()));
//        document.add(new Paragraph("               Account Number:                                                    " + withdrawTransaction.getAccountNumber()));
//        document.add(new Paragraph("               Withdraw Amount:                                                   " + withdrawTransaction.getwAmount()));
//        document.add(new Paragraph("               Account Type:                                                         " + withdrawTransaction.getAccountType()));
//        document.add(new Paragraph("               Date:                                                                       " + withdrawTransaction.getWithdrawTime()));
//
//        document.add(new Paragraph("                                                                                              Received By :.........................."));
//
//        document.close();
//
//        return baos.toByteArray();
//    }
//
//    class HeaderFooter extends PdfPageEventHelper {
//        public void onEndPage(PdfWriter writer, Document document) {
//            try {
////                Image img = Image.getInstance(getClass().getClassLoader().getResource("static/assets/images/logo.png"));
////
////                img.scaleToFit(100, 100);
////                img.setAbsolutePosition(40f, 760f);
////
////                PdfContentByte cb = writer.getDirectContent();
////                cb.addImage(img);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }



private byte[] generatePdfBytes(FeeCatagoryModel feeCatagoryModel) throws IOException, DocumentException {
    Document document = new Document();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter writer = PdfWriter.getInstance(document, baos);

    FeeApi.HeaderFooter event = new FeeApi.HeaderFooter();
    writer.setPageEvent(event);

    document.open();

    // Add a table with two columns to divide the page
    PdfPTable mainTable = new PdfPTable(2);
    mainTable.setWidthPercentage(100);
    mainTable.setWidths(new int[]{50, 50}); // Divide the page into two equal parts

    // School Copy Section
    // Add title for School copy
    Paragraph schoolCopyTitle = new Paragraph("SCHOOL MANAGEMENT SYSTEM");
    schoolCopyTitle.setAlignment(Element.ALIGN_CENTER);
    document.add(schoolCopyTitle);

    // Add date for School copy
    Paragraph schoolCopyDate = new Paragraph("Date: " + feeCatagoryModel.getPaymentDate());
    schoolCopyDate.setAlignment(Element.ALIGN_RIGHT);
    document.add(schoolCopyDate);

    // Add table for School copy
    PdfPTable schoolCopyTable = new PdfPTable(2);
    schoolCopyTable.setWidthPercentage(100);

    // Add content to the School copy table
    addTableHeader(schoolCopyTable);
    addRows(schoolCopyTable, feeCatagoryModel);

    // Add School copy table to the left side of the main table
    PdfPCell schoolCopyCell = new PdfPCell(schoolCopyTable);
    schoolCopyCell.setBorder(PdfPCell.NO_BORDER);
    mainTable.addCell(schoolCopyCell);

    // Student Copy Section
    // Add title for Student copy
    Paragraph studentCopyTitle = new Paragraph("SCHOOL MANAGEMENT SYSTEM");
    studentCopyTitle.setAlignment(Element.ALIGN_CENTER);

    // Add date for Student copy
    Paragraph studentCopyDate = new Paragraph("Date: " + feeCatagoryModel.getPaymentDate());
    studentCopyDate.setAlignment(Element.ALIGN_RIGHT);

    // Add table for Student copy
    PdfPTable studentCopyTable = new PdfPTable(2);
    studentCopyTable.setWidthPercentage(100);

    // Add content to the Student copy table
    addTableHeader(studentCopyTable);
    addRows(studentCopyTable, feeCatagoryModel);

    // Add Student copy table to the right side of the main table
    PdfPCell studentCopyCell = new PdfPCell(studentCopyTable);
    studentCopyCell.setBorder(PdfPCell.NO_BORDER);
    mainTable.addCell(studentCopyCell);

    // Add the main table to the document
    document.add(mainTable);

    document.close();

    return baos.toByteArray();
}
    private void addTableHeader(PdfPTable table) {
        table.addCell("Field");
        table.addCell("Value");
    }

    private void addRows(PdfPTable table, FeeCatagoryModel feeCatagoryModel) {
        table.addCell("Date");
        table.addCell("  "+feeCatagoryModel.getPaymentDate());

        table.addCell("Student Name");
        table.addCell("  "+feeCatagoryModel.getStudentAddModel().getStfirstname() + " " + feeCatagoryModel.getStudentAddModel().getStlastname());

        table.addCell("ROLL No");
        table.addCell("  "+feeCatagoryModel.getStudentAddModel().getStRoll());

        table.addCell("Class");
        table.addCell("  "+feeCatagoryModel.getStudentAddModel().getStClass());

        table.addCell("Batch Id");
        table.addCell("  "+feeCatagoryModel.getStudentAddModel().getBatchId());

        table.addCell("Payment Id");
        table.addCell("  "+String.valueOf(feeCatagoryModel.getFeeid()));

        table.addCell("Payment Category");
        table.addCell("  "+feeCatagoryModel.getFeeCatagory());

        table.addCell("Payment Month");
        table.addCell("  "+feeCatagoryModel.getFeeMonth());

        table.addCell("Total Amount");
        table.addCell("  "+feeCatagoryModel.getFeeAmount());

        table.addCell("Received By");
        table.addCell("..........................");
    }

//chat----------------------

//    private byte[] generatePdfBytes(FeeCatagoryModel feeCatagoryModel) throws IOException, DocumentException {
//        Document document = new Document();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PdfWriter writer = PdfWriter.getInstance(document, baos);
//
//        FeeApi.HeaderFooter event = new FeeApi.HeaderFooter();
//        writer.setPageEvent(event);
//
//        document.open();
//
//        // Create a table to hold the paragraphs
//        PdfPTable table = new PdfPTable(1);
//        table.setWidthPercentage(100);
//
//        // Create a PdfPCell for each paragraph and add it to the table
//        PdfPCell cell;
//
//        cell = new PdfPCell(new Paragraph("SCHOOL MANAGEMENT SYSTEM"));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("Student Payment Receipt"));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("--------------------------------------------------------------------------------"));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("Date             : " + feeCatagoryModel.getPaymentDate()));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("Student Name     : " + feeCatagoryModel.getStudentAddModel().getStfirstname() + " " + feeCatagoryModel.getStudentAddModel().getStlastname()));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("ROLL No          : " + feeCatagoryModel.getStudentAddModel().getStRoll()));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("Class            : " + feeCatagoryModel.getStudentAddModel().getStClass()));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("Batch Id         : " + feeCatagoryModel.getStudentAddModel().getBatchId()));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("Payment Id       : " + feeCatagoryModel.getFeeid()));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("Payment Category: " + feeCatagoryModel.getFeeCatagory()));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("Payment Month    : " + feeCatagoryModel.getFeeMonth()));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("Total Amount     : " + feeCatagoryModel.getFeeAmount()));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Paragraph("Received By      : .........................."));
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        table.addCell(cell);
//
//        // Add the table to the document
//        document.add(table);
//
//        document.close();
//
//        return baos.toByteArray();
//    }


    class HeaderFooter extends PdfPageEventHelper {
        public void onEndPage(PdfWriter writer, Document document) {
            try {
//                Image img = Image.getInstance(getClass().getClassLoader().getResource("static/assets/images/logo.png"));
//
//                img.scaleToFit(100, 100);
//                img.setAbsolutePosition(40f, 760f);
//
//                PdfContentByte cb = writer.getDirectContent();
//                cb.addImage(img);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

















}
