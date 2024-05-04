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
        let check = hoTen != oldName || khoa != oldKhoa || nganh != oldNganh || soDienThoai != oldSdt || email != oldEmail;

        function validate() {
            // Biểu thức chính quy để kiểm tra ký tự đặc biệt và số
            let specialChars = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?0-9]+/;
            // Biểu thức chính quy để kiểm tra định dạng email
            let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            // Biểu thức chính quy để kiểm tra định dạng số điện thoại
            let phoneNumberRegex = /^0\\d{9}$/;
            error = "";
            flag = true;
            if (hoTen === "") {
                error += "Tên thành viên không được để trống\n";
                flag = false;
            }
            if (khoa === "") {
                error += "Khoa không được để trống\n";
                flag = false;
            }
            if (nganh === "") {
                error += "Ngành không được để trống\n";
                flag = false;
            }
            if (email === "") {
                error += "Email không được để trống\n";
                flag = false;
            } else if (!emailRegex.test(email)) {
                error += "Email không đúng định dạng\n";
                flag = false;
            }
            if (soDienThoai === "") {
                error += "Số điện thoại không được để trống\n";
                flag = false;
            } else if (phoneNumberRegex.test(soDienThoai)) {
                error += "Số điện thoại không hợp lệ\n";
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


        if (check) {
            if (validate() == true) {

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
                        Swal.fire({
                            title: "Thông báo!",
                            text: "Cập nhật hồ sơ thành công",
                            icon: "success",
                            confirmButtonText: "Ok"
                        });
                    },
                    error: function (xhr, status, error) {
                        // Xử lý lỗi
                        console.log("Lỗi" + accountId + error);
                    }
                });
            }
        } else {
            Swal.fire({
                title: "Lưu ý!",
                text: "Phải thay đổi ít nhất 1 thông tin!",
                icon: "warning",
                confirmButtonText: "Ok"
            });
        }
    });

    // Thay đổi mật khẩu
    $("#update-password").click(function (e) {
        e.preventDefault(); // Ngăn chặn hành động mặc định của form

        // Lấy giá trị từ các trường nhập
//        let maTV = $("#dm-profile-edit-matv").val();
        let currentPassword = $("#dm-profile-edit-currentPassword").val();
        let newPassword = $("#dm-profile-edit-newPassword").val();
        let confirmPassword = $("#dm-profile-edit-confirmPassword").val();

        let check = currentPassword != "" && newPassword != "" && confirmPassword != "";
        let checkConfirmPass = confirmPassword === newPassword;

        function checkCurrentPassword() {
            $.ajax({
                url: "/checkCurrentPassword",
                type: "POST",
                data: {
                    maTV: accountId,
                    currentPassword: currentPassword,
                },
                success: function (result) {
                    if (result) {
                        // Mật khẩu hiện tại hợp lệ
                        console.log("Current password is correct");
                    } else {
                        // Mật khẩu hiện tại không hợp lệ
                        console.log("Current password is incorrect");
                    }
                }
            });
        }
        let test = checkCurrentPassword();
//        console.log(test);

        if (check) {
            // Gửi dữ liệu lên máy chủ qua Ajax
            if (checkConfirmPass) {
                $.ajax({
                    url: `/profile/updatePassword/${accountId}`, // Thay thế "url-xu-ly-du-lieu.php" bằng URL xử lý dữ liệu trên máy chủ của bạn
                    type: "POST",
                    data: {
                        currentPassword: currentPassword,
                        newPassword: newPassword,
                        confirmPassword: confirmPassword,
                    },
                    success: function (response) {
                        // Xử lý phản hồi thành công từ máy chủ
                        if (response === "true") {
                            Swal.fire({
                                title: "Thông báo!",
                                text: "Cập nhật mật khẩu thành công",
                                icon: "success",
                                confirmButtonText: "Ok"
                            });
                        } else {
                            Swal.fire({
                                title: "Thông báo!",
                                text: "Mật khẩu hiện tại sai!",
                                icon: "error",
                                confirmButtonText: "Ok"
                            });
                        }


                    },
                    error: function (xhr, status, error) {
                        // Xử lý lỗi
                        console.log("Lỗi kìa mẹ mày");
                    }
                });
            } else {
                Swal.fire({
                    title: "Mật khẩu xác nhận chưa chính xác!",
                    text: "Vui lòng nhập lại trường xác nhận mật khẩu!",
                    icon: "error",
                    confirmButtonText: "Ok"
                });
            }
        } else {
            Swal.fire({
                title: "Lưu ý!",
                text: "Vui lòng nhập đầy đủ!",
                icon: "warning",
                confirmButtonText: "Ok"
            });
        }
    });



});