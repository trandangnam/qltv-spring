//const inputElements = document.querySelectorAll("input");
//
//inputElements.forEach((el) => {
//  el.addEventListener(
//    "input",
//    function (e) {
//      el.classList.remove("is-invalid");
//    },
//    { once: true }
//  );
//});
document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");
    const maTV = document.getElementById("val-matv");
    const tenTV = document.getElementById("val-hoten");
    const khoa = document.getElementById("val-khoa");
    const nganh = document.getElementById("val-nganh");
    const sdt = document.getElementById("val-sdt");
    const email = document.getElementById("val-email");
    const password = document.getElementById("val-password");
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
});