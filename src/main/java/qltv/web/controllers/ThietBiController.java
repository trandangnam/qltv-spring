package qltv.web.controllers;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import qltv.web.dto.ThietBiDTO;
import qltv.web.services.ThietBiService;

@RestController
@RequestMapping("/thietbi")
public class ThietBiController {

    private final ThietBiService thietBiService;

    @Autowired
    public ThietBiController(ThietBiService thietBiService) {
        this.thietBiService = thietBiService;
    }

    @GetMapping
    public String listThietBi(Model model) {
        List<ThietBiDTO> listThietBi = thietBiService.getAllThietBi();
        model.addAttribute("listThietBi", listThietBi);
        return "thiet-bi-list";
    }

    @GetMapping("/new")
    public String createThietBiForm(Model model) {
        ThietBiDTO thietBi = new ThietBiDTO();
        model.addAttribute("thietBi", thietBi);
        return "thiet-bi-create";
    }

    @PostMapping("/new")
    public String saveThietBi(@Valid @ModelAttribute("thietBi") ThietBiDTO thietBi, BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("thietBi", thietBi);
            return "thiet-bi-create";
        }
        thietBiService.saveThietBi(thietBi);
        return "redirect:/thietbi";
    }

    @GetMapping("/{maTB}/edit")
    public String editThietBiForm(@PathVariable("maTB") int maTB, Model model) {
        ThietBiDTO thietBi = thietBiService.findThietBiById(maTB);
        model.addAttribute("thietBi", thietBi);
        return "thiet-bi-edit";
    }

    @PostMapping("/{maTB}/edit")
    public String updateThietBi(@PathVariable("maTB") int maTB,
                                @Valid @ModelAttribute("thietBi") ThietBiDTO thietBi,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "thiet-bi-edit";
        }
        thietBiService.updateThietBi(thietBi);
        return "redirect:/thietbi";
    }

    @GetMapping("/{maTB}/delete")
    public String deleteThietBi(@PathVariable("maTB") int maTB) {
        thietBiService.deleteThietBi(maTB);
        return "redirect:/thietbi";
    }

    @GetMapping("/search")
    public String searchThietBi(@RequestParam(value = "query") String query, Model model) {
        List<ThietBiDTO> listThietBi = thietBiService.searchThietBi(query);
        model.addAttribute("listThietBi", listThietBi);
        model.addAttribute("query", query);
        return "thiet-bi-list";
    }

    @GetMapping("/export-excel")
    public ResponseEntity<byte[]> exportExcel(HttpServletResponse response) throws IOException {
        // Tạo một workbook Excel mới
        Workbook workbook = new XSSFWorkbook();
        XSSFSheet sheet =  (XSSFSheet) workbook.createSheet("Danh sách thiết bị");

        // Tạo dòng header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Mã");
        headerRow.createCell(1).setCellValue("Tên thiết bị");
        headerRow.createCell(2).setCellValue("Mô tả");

        // Lấy danh sách thiết bị từ service
        List<ThietBiDTO> listThietBi = thietBiService.getAllThietBi();

        // Tạo dữ liệu cho bảng Excel từ danh sách thiết bị
        int rowNum = 1;
        for (ThietBiDTO thietBi : listThietBi) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(thietBi.getMaTB());
            row.createCell(1).setCellValue(thietBi.getTenTB());
            row.createCell(2).setCellValue(thietBi.getMoTaTB());
        }

        // Ghi workbook vào một ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // Chuẩn bị dữ liệu để trả về cho người dùng
        byte[] excelBytes = outputStream.toByteArray();

        // Thiết lập các thông tin header cho response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "danh-sach-thiet-bi.xlsx");

        // Trả về response với dữ liệu file Excel và các header tương ứng
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
    }

    @PostMapping("/import-excel")
    public String importExcel(@RequestParam("file") MultipartFile file) {
        try {
            // Đọc file Excel
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Bỏ qua tiêu đề nếu có
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                int maTB = (int) row.getCell(0).getNumericCellValue();
                ThietBiDTO thietBi = thietBiService.findThietBiById(maTB);
                if(thietBi == null){
                    thietBi = new ThietBiDTO();
                    thietBi.setMaTB((int) row.getCell(0).getNumericCellValue());
                    thietBi.setTenTB(row.getCell(1).getStringCellValue());
                    thietBi.setMoTaTB(row.getCell(2).getStringCellValue());

                    // Lưu thiết bị vào cơ sở dữ liệu
                    thietBiService.saveThietBi(thietBi);
                }
            }
            // Đóng workbook và trả về thông báo thành công
            workbook.close();
            return "redirect:/thietbi";
        } catch (IOException e) {
            // Xử lý lỗi khi đọc file Excel
            e.printStackTrace();
            return "redirect:/thietbi";
        }
    }
    
    // ---------------------------------hàm này của tiến nha đừng có xóa---------------------------------
    @GetMapping("/thietbi/getbyid")
    @ResponseBody
    public ThietBiDTO getThietBiById(@RequestParam(value = "query") String query, Model model) {
        try {
            Long maTB = Long.parseLong(query);
            ThietBiDTO thietBi = thietBiService.findThietBiById(maTB);
            return thietBi;
        }catch(Exception e){
            return null;
        }
    }
    //--------------------------------------------------------------
}
