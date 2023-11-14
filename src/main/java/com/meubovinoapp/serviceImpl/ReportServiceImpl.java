package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.JWT.JwtFilter;
import com.meubovinoapp.POJO.Report;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.dao.ReportDAO;
import com.meubovinoapp.service.ReportService;
import com.meubovinoapp.utils.BovinoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.itextpdf.text.FontFactory.getFont;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    ReportDAO reportDAO;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        try {
            String fileName;
            if (validateRequestMap(requestMap)) {
                if (requestMap.containsKey("isGenerate") && !(Boolean) requestMap.get("isGenerate")) {
                    fileName = (String) requestMap.get("uuid");
                } else {
                    fileName = BovinoUtils.getUUID();
                    requestMap.put("uuid", fileName);
                    insertBill(requestMap);
                }

                String data = "Name: " + requestMap.get("name") + "\n" + "Quantidade de Bois " + requestMap.get("oxQuantity") +
                        "\n" + "Média de peso dos bois: " + requestMap.get("oxAverageWeight") + "\n";

                Document document = new Document();

                PdfWriter.getInstance(document, new FileOutputStream(BovinoConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));

                document.open();
                setRectangleInPdf(document);

                Paragraph chunk = new Paragraph("Bovino Management System", getFont("Header"));
                chunk.setAlignment(Element.ALIGN_CENTER);
                document.add(chunk);

                Paragraph paragraph = new Paragraph(data + "\n \n", getFont("Data"));
                document.add(paragraph);

                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                addTableHeader(table);

                JSONArray jsonArray = BovinoUtils.getJsonArrayFromString((String) requestMap.get("animalArray"));
                for (int i = 0; i < jsonArray.length(); i++) {
                    addRows(table, BovinoUtils.getMapFromJson(jsonArray.getString(i)));
                }

                document.add(table);

                Paragraph footer = new Paragraph( "Todos os direitos reservados a MeuBovinoApp", getFont("Data"));
                document.add(footer);
                document.close();
                return new ResponseEntity<>("{\"uuid\":\"" + fileName + "\"}", HttpStatus.OK);


            }
            return BovinoUtils.getResponseEntity("Required data not found.", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void addRows(PdfPTable table, Map<String, Object> data) {
        log.info("inside addRows");
        table.addCell((String) data.get("name"));
        table.addCell((String) data.get("race"));
        String actualWeight = String.valueOf(data.get("actualWeight"));
        log.info(actualWeight);
        table.addCell(actualWeight);
        String birth = String.valueOf(data.get("birth"));
        log.info(birth);
        table.addCell(birth);
    }

    private void addTableHeader(PdfPTable table) {

        log.info("Inside addTableHeader");
        Stream.of("Name", "Race", "Weight", "Birth")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.GREEN);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);

                });

    }

    private Font getFont(String type) {
        log.info("Inside getFont");
        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                dataFont.setStyle(Font.BOLD);
                return dataFont;
            default:
                return new Font();
        }
    }

    private void setRectangleInPdf(Document document) throws DocumentException {
        log.info("Inside setRectangleInPdf");
        Rectangle rect = new Rectangle(577, 825, 18, 15);
        rect.enableBorderSide(1);
        rect.enableBorderSide(2);
        rect.enableBorderSide(4);
        rect.enableBorderSide(8);
        rect.setBorderColor(BaseColor.BLACK);
        rect.setBorderWidth(1);
        document.add(rect);
    }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("paymentMethod") &&
                requestMap.containsKey("totalAmount")&&
                requestMap.containsKey("animalArray");
    }

    @Override
    public ResponseEntity<List<Report>> getReport() {

        List<Report> list = new ArrayList<>();
        if (jwtFilter.isAdmin()) {
            list = reportDAO.getAllReports();
        } else {
            list = reportDAO.getReportByUserName(jwtFilter.getCurrentUser());


        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        log.info("Inside getPdf : requestMap {}", requestMap);
        try {
            byte[] byteArray = new byte[0];
            if (!requestMap.containsKey("uuid") && validateRequestMap(requestMap)) {
                return new ResponseEntity<>(byteArray, HttpStatus.BAD_REQUEST);
            }
            String filePath = BovinoConstants.STORE_LOCATION + "\\" + (String) requestMap.get("uuid") + ".pdf";
            if (BovinoUtils.isFileExist(filePath)) {
                byteArray = getByteArray(filePath);
                return new ResponseEntity<>(byteArray, HttpStatus.OK);
            } else {
                requestMap.put("isGenerate", false);
                generateReport(requestMap);
                byteArray = getByteArray(filePath);
                return new ResponseEntity<>(byteArray, HttpStatus.OK);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> deleteReport(Integer id) {
        try{
            Optional optional = reportDAO.findById(Long.valueOf(id));
            if(!optional.isEmpty()){
                reportDAO.deleteById(Long.valueOf(id));
                return BovinoUtils.getResponseEntity("Report Deleted succesfully",HttpStatus.OK);
            }
            return BovinoUtils.getResponseEntity("Report id does not exist",HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private void insertBill(Map<String, Object> requestMap) {
        try {
            Report report = new Report();
            report.setUuid((String) requestMap.get("uuid"));
            report.setPaymentMethod((String) requestMap.get("paymentMethod"));
            report.setTotal(Integer.parseInt((String) requestMap.get("totalAmount")));
            report.setCreatedBy(jwtFilter.getCurrentUser());
            reportDAO.save(report);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private byte[] getByteArray(String filePath) throws Exception {
        File initialFile = new File(filePath);
        InputStream targetStream = new FileInputStream(initialFile);
        byte[] byteArray = IOUtils.toByteArray(targetStream);
        targetStream.close();
        return byteArray;
    }
}
