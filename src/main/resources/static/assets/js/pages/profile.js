/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

//Dashmix.helpersOnLoad(["js-flatpickr", "jq-datepicker"]);
$(document).ready(function () {
    const accountId = $(".account_ID").attr("data-id");
    var oldName = $("#dm-profile-edit-name").val();
    let oldKhoa = $("#dm-profile-edit-khoa").val();
    let oldNganh = $("#dm-profile-edit-nganh").val();
    let oldSdt = $("#dm-profile-edit-sdt").val();
    let oldEmail = $("#dm-profile-edit-email").val();

    // Cập nhật profile
    $("#update-profile").click(function (e) {
        e.preventDefault(); // Ngăn chặn hành động mặc định của form

        // Lấy giá trị từ các trường nhập
//        let maTV = $("#dm-profile-edit-matv").val();
        let hoTen = $("#dm-profile-edit-name").val();
        let khoa = $("#dm-profile-edit-khoa").val();
        let nganh = $("#dm-profile-edit-nganh").val();
        let soDienThoai = $("#dm-profile-edit-sdt").val();
        let email = $("#dm-profile-edit-email").val();
        console.log(email);
        let check = hoTen != oldName || khoa != oldKhoa || nganh != oldNganh || soDienThoai != oldSdt || email != oldEmail;

        if (check) {
            // Gửi dữ liệu lên máy chủ qua Ajax
            $.ajax({
                url: `/profile/${accountId}`, // Thay thế "url-xu-ly-du-lieu.php" bằng URL xử lý dữ liệu trên máy chủ của bạn
                type: "POST",
                data: {
                    hoTen: hoTen,
                    khoa: khoa,
                    nganh: nganh,
                    soDienThoai: soDienThoai,
                    email: email,
                },
                success: function (response) {
                    // Xử lý phản hồi thành công từ máy chủ
                    console.log("Ok!");
                    console.log(response);
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi
                    console.log("Lỗi" + accountId + error);
                }
            });
        } else {
            Swal.fire({
                title: "Lưu ý!",
                text: "Phải thay đổi ít nhất 1 thông tin!",
                icon: "warning",
                confirmButtonText: "Ok"
            });
        }
    });

});