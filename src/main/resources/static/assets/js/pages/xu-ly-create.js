document.addEventListener("DOMContentLoaded", function (event) {
  const cbbHinhThucXuLy = document.getElementById("hinhThucXuLy");
  const txtTienPhat = document.getElementById("tienPhat");
  const txtMaTV = document.getElementById("maThanhVien");
  const txtTenTV = document.getElementById("tenThanhVien");
  const form = document.getElementById("form-themXuLy");
  const btnThemXuLy = document.getElementById("btn-themXuLy");

  // xử lý thay đổi combobox hình thức xử lý thì bật tắt input tiền phạt
  cbbHinhThucXuLy.addEventListener("change", function () {
    if (cbbHinhThucXuLy.value.includes("oiThuong")) {
      txtTienPhat.readOnly = false;
    } else {
      txtTienPhat.value = "";
      txtTienPhat.readOnly = true;
    }
    
  });
  cbbHinhThucXuLy.dispatchEvent(new Event("change"));

  // xử lý tự động hiện hiện tên thành viên khi chọn mã thành viên
  txtMaTV.addEventListener("focusout", async function () {
    try {
      const response = await fetch(`/thanhvien/getbyid?query=${txtMaTV.value}`);
      const data = await response.json();
      if (data == null || data.hoTen == undefined) {
        txtTenTV.value = "Không tìm thấy thành viên có mã này";
        return;
      }
      txtTenTV.value = data.hoTen;
    } catch (error) {}
  });

  // xử lý kiểm tra dữ liệu xử lý
  btnThemXuLy.addEventListener("click", function () {
    if (validate() === false) {
      return;
    }
    form.submit();
  });

  function validate() {
    let tienPhat = txtTienPhat.value;
    let maThanhVien = txtMaTV.value;
    let tenThanhVien = txtTenTV.value;
    let hinhThucXuLy = cbbHinhThucXuLy.value;

    error = "";
    flag = true;
    if (maThanhVien === "") {
      error += "Mã thành viên không được để trống\n";
      flag = false;
    }

    if (
      tenThanhVien === "Không tìm thấy thành viên có mã này" ||
      tenThanhVien === ""
    ) {
      error += "Mã thành viên không hợp lệ\n";
      flag = false;
    }
    if (tienPhat == "" && hinhThucXuLy.includes("oiThuong")) {
      error += "Tiền phạt không được để trống\n";
      flag = false;
    }
    if (tienPhat != "" && hinhThucXuLy.includes("oiThuong")) {
      if (isNaN(tienPhat)) {
        error += "Tiền phạt phải là số nguyên\n";
        flag = false;
      } else if (parseInt(tienPhat) < 10000 || parseInt(tienPhat) > 100000000) {
        error += "Tiền phạt phải từ 10.000 đến 100.000.000\n";
        flag = false;
      }
    }
    if (error != ""){
      Swal.fire({
        title: "Dữ liệu không hợp lệ",
        text: error,
        icon: "error",
      });
    }

    return flag;
  }
});
