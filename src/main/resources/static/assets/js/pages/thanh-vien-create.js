document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");
    const maTV = document.getElementById("val-matv");
    const tenTV = document.getElementById("val-hoten");
    const khoa = document.getElementById("val-khoa");
    const nganh = document.getElementById("val-nganh");
    const sdt = document.getElementById("val-sdt");
    const email = document.getElementById("val-email");
    const password = document.getElementById("val-password");
    
    nganh.addEventListener("change", async function (event) {
        let mon = "00" ;
        switch (nganh.value) {
            case "Toán": khoa.value = "Toán UD";mon = "48"; break;
            case "Địa":khoa.value = "SP KHXH";mon="11";break;
            case "Sử":khoa.value = "SP KHXH";mon="10";break;
            case "Văn": khoa.value = "SP KHXH";mon="09";break;
            case "NNA":khoa.value = "Ngoại ngữ";mon = "38"; break;
            case "Anh":khoa.value = "Ngoại ngữ";mon = "13"; break;
            case "TLH":khoa.value = "QLGD";mon = "53";break;
            case "QTKD":khoa.value = "QTKD";mon="55";break;
            case "CNTT":khoa.value = "CNTT";mon="41";break;
            case "Lí":khoa.value = "SP KHTN";mon="02";break;
            default:
                console.log("Code sai");
                break;
        }
        try {
            const response = await fetch(`/thanhvien/getbynganh?query=${nganh.value}`);
            const data = await response.text();
            console.log(data);
            console.log(mon);
            const date = new Date();
            const year = date.getFullYear().toString().substring(2);
            maTV.value = "11"+year+mon+data;
        } catch (error) {
        }
    });
    
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        if (validate() === false) {
            return;
        }else{
            form.submit();
        }
    });
    
    function validate(){
        let txtmatv = maTV.value;
        let txttentv = tenTV.value;
        let txtkhoa = khoa.value;
        let txtnganh= nganh.value;
        let txtsdt = sdt.value;
        let txtemail = email.value;
        let txtpassword = password.value;
        
        // Biểu thức chính quy để kiểm tra ký tự đặc biệt và số
        let specialChars = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?0-9]+/;
        // Biểu thức chính quy để kiểm tra định dạng email
        let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        error = "";
        flag = true;
        if (txtmatv === "") {
            error += "Mã thành viên không được để trống\n";
            flag = false;
        }
        if (txttentv === "" ){
            error += "Tên thành viên không được để trống\n";
            flag = false;
        }else if( specialChars.test(txttentv)){
            error += "Tên thành viên không hợp lệ\n";
            flag = false;
        }
        if (txtkhoa === "") {
            error += "Khoa không được để trống\n";
            flag = false;
        }
        if (txtnganh === "") {
            error += "Ngành không được để trống\n";
            flag = false;
        }
        if (txtsdt === "") {
            error += "Số điện thoại không được để trống\n";
            flag = false;
        }
        if (txtemail === "") {
            error += "Email không được để trống\n";
            flag = false;
        }else if(!emailRegex.test(txtemail)){
            error += "Email không đúng định dạng\n";
            flag = false;
        }
        if(txtpassword.length < 8){
            error += "Mật khẩu phải có ít nhất 8 kí tự\n";
            flag = false;
        }
        if (error != "") {
            Swal.fire({
                title: "Dữ liệu không hợp lệ",
                text: error,
                icon: "error",
            });
        }
        return flag;
    }
    
//    form.addEventListener("submit", function(event) {
//        // Ngăn chặn việc gửi form đi
//        event.preventDefault();
//
//        // Lấy danh sách các input trong form
//        const inputs = form.querySelectorAll("input");
//
//        // Biến để kiểm tra xem có input nào rỗng không
//        let isEmpty = false;
//
//        // Lặp qua từng input để kiểm tra
//        inputs.forEach(function(input) {
//            // Nếu input có giá trị rỗng, đặt isEmpty thành true và hiển thị thông báo lỗi
//            if (input.value.trim() === "") {
//                isEmpty = true;
//                input.classList.add("is-invalid");
//            } else {
//                input.classList.remove("is-invalid");
//            }
//        });
//
//        // Nếu có input rỗng, hiển thị thông báo lỗi và không gửi form đi
//        if (isEmpty) {
//            alert("Vui lòng điền đầy đủ thông tin.");
//        } else {
//            // Nếu không có input rỗng, gửi form đi
//            form.submit();
//        }
//    });
});
