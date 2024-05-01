package qltv.web.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import qltv.web.dto.ThanhVienDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import qltv.web.security.SecurityUtil;
import qltv.web.services.ThanhVienService;


@Controller
public class ThanhVienController {

    private ThanhVienService tvService;

    @Autowired
    public ThanhVienController(ThanhVienService tvService) {
        this.tvService = tvService;
    }

    @GetMapping("/thanhvien")
    public String listThanhVien(Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int maTV = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(maTV);
        model.addAttribute("user", user);
        List<ThanhVienDTO> listThanhVien = tvService.getAllThanhVien();
        model.addAttribute("listThanhVien", listThanhVien);
        return "thanh-vien-list";
    }

    @GetMapping("/thanhvien/new")
    public String createThanhVienForm(Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int maTV = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(maTV);
        model.addAttribute("user", user);
        ThanhVienDTO thanhVien = new ThanhVienDTO();
        model.addAttribute("thanhVien", thanhVien);
        return "thanh-vien-create";
    }

    @PostMapping("/thanhvien/new")
    public String saveThanhVien(@Valid @ModelAttribute("thanhVien") ThanhVienDTO thanhVien, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("thanhVien", thanhVien);
            return "thanh-vien-create";
        }

        tvService.saveThanhVien(thanhVien);
        return "redirect:/thanhvien";
    }

    @GetMapping("/thanhvien/{maTV}/edit")
    public String editThanhVienForm(@PathVariable("maTV") long maTV, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int userId = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(userId);
        model.addAttribute("user", user);
        ThanhVienDTO thanhVien = tvService.findMemberById(maTV);
        model.addAttribute("thanhVien", thanhVien);
        return "thanh-vien-edit";
    }

    @PostMapping("/thanhvien/{maTV}/edit")
    public String updateThanhVien(@PathVariable("maTV") long maTV,
            @Valid @ModelAttribute("thanhVien") ThanhVienDTO thanhVien,
            BindingResult result, Model model) {

        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int userId = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(userId);
        model.addAttribute("user", user);

        if (result.hasErrors()) {
            return "thanh-vien-edit";
        }

        tvService.updateThanhVien(thanhVien);
        return "redirect:/thanhvien";
    }

    @GetMapping("/thanhvien/{maTV}/delete")
    public String deleteThanhVien(@PathVariable("maTV") long maTV) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        tvService.deleteThanhVien(maTV);
        return "redirect:/thanhvien";
    }

    @GetMapping("/thanhvien/search")
    public String searchThanhVien(@RequestParam(value = "query") String query, Model model) {
        String username = SecurityUtil.getUserSession();
        if (username == null) {
            return "redirect:/login";
        }
        int userId = Integer.parseInt(username);
        ThanhVienDTO user = tvService.findMemberById(userId);
        model.addAttribute("user", user);
        List<ThanhVienDTO> listThanhVien = tvService.searchThanhVien(query);
        model.addAttribute("listThanhVien", listThanhVien);
        model.addAttribute("query", query);
        return "thanh-vien-list";
    }

    // hàm này của tiến nha đừng có xóa
    @GetMapping("/thanhvien/getbyid")
    @ResponseBody
    public ThanhVienDTO getThanhVienById(@RequestParam(value = "query") String query, Model model) {
//        String username = SecurityUtil.getUserSession();
//        if (username == null) {
//            return null;
//        }
        try {
            Long maTV = Long.parseLong(query);
            ThanhVienDTO user = tvService.findMemberById(maTV);
            return user;
        }catch(Exception e){
            return null;
        }

    }
    //---------------------------------------------
    @GetMapping("/thanhvien/getbynganh")
    @ResponseBody
    public String getThanhVienByNganh(@RequestParam(value = "query") String query, Model model) {
        try {
            String nganh = String.valueOf(query);
            List<ThanhVienDTO> listUser = tvService.findMemberByNganh(nganh);
            int max = 0;
            for(ThanhVienDTO tv : listUser){
                String maTV = tv.getMaTV()+"";
                String soCuoi = maTV.substring(maTV.length() - 4);
                int soCuoiInt = Integer.parseInt(soCuoi) + 1;
                if(soCuoiInt>max){
                    max = soCuoiInt;
                }
            }
            return String.format("%04d", max);
        }catch(Exception e){
            return null;
        }

    }
    // Endpoint để xuất file Excel từ danh sách thành viên
    @GetMapping("/thanhvien/export-excel")
    public ResponseEntity<byte[]> exportExcel(HttpServletResponse response) throws IOException {
        // Tạo một workbook Excel mới
        Workbook workbook = new XSSFWorkbook();
        XSSFSheet sheet =  (XSSFSheet) workbook.createSheet("Danh sách thành viên");

        // Tạo dòng header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Mã");
        headerRow.createCell(1).setCellValue("Họ tên");
        headerRow.createCell(2).setCellValue("Khoa");
        headerRow.createCell(3).setCellValue("Ngành");
        headerRow.createCell(4).setCellValue("SĐT");
        headerRow.createCell(5).setCellValue("Email");

        // Lấy danh sách thành viên từ service
        List<ThanhVienDTO> listThanhVien = tvService.getAllThanhVien();

        // Tạo dữ liệu cho bảng Excel từ danh sách thành viên
        int rowNum = 1;
        for (ThanhVienDTO thanhVien : listThanhVien) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(thanhVien.getMaTV());
            row.createCell(1).setCellValue(thanhVien.getHoTen());
            row.createCell(2).setCellValue(thanhVien.getKhoa());
            row.createCell(3).setCellValue(thanhVien.getNganh());
            row.createCell(4).setCellValue(thanhVien.getSdt());
            row.createCell(5).setCellValue(thanhVien.getEmail());
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
        headers.setContentDispositionFormData("attachment", "danh-sach-thanh-vien.xlsx");

        // Trả về response với dữ liệu file Excel và các header tương ứng
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
    }
    @PostMapping("/thanhvien/import-excel")
    public String importExcel(@RequestParam("file") MultipartFile file) {
        try {
            // Lấy danh sách mã thành viên hiện có từ cơ sở dữ liệu
            List<ThanhVienDTO> list = tvService.getAllThanhVien();
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
                int maTV = (int) row.getCell(0).getNumericCellValue();
                boolean flag=false;
                for (ThanhVienDTO tv : list){
                    if(maTV==tv.getMaTV()){
                        flag = true;
                    }
                }
                if(flag==false){
                    ThanhVienDTO thanhVien = new ThanhVienDTO();
                    thanhVien.setMaTV((int) row.getCell(0).getNumericCellValue());
                    thanhVien.setHoTen(row.getCell(1).getStringCellValue());
                    thanhVien.setKhoa(row.getCell(2).getStringCellValue());
                    thanhVien.setNganh(row.getCell(3).getStringCellValue());
                    thanhVien.setSdt(row.getCell(4).getStringCellValue());
                    thanhVien.setPassword(row.getCell(5).getNumericCellValue()+"");
                    thanhVien.setEmail(row.getCell(6).getStringCellValue());

                    // Lưu thành viên vào cơ sở dữ liệu
                    tvService.saveThanhVien(thanhVien);
                }
            }     
            // Đóng workbook và trả về thông báo thành công
            workbook.close();
            return "redirect:/thanhvien";
        } catch (IOException e) {
            // Xử lý lỗi khi đọc file Excel
            System.out.println("Lỗi đọc excel");
            e.printStackTrace();
            return "redirect:/thanhvien";
        }
    }

}
