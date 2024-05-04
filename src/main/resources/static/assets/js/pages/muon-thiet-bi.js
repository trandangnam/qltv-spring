document.addEventListener("DOMContentLoaded", function (event) {
  const btnSearch = document.getElementById("btn-search");
  const inputSearch = document.getElementById("input-search");
  const txtMaTB = document.getElementById("input-MaThietBi");
  const txtMaTV = document.getElementById("input-MaThanhVien");
  const btnMuon = document.getElementById("btn-MuonThietBi");
  const txtMaTT = document.getElementById("input-MaTT");

  btnSearch.addEventListener("click", function () {
    const searchValue = inputSearch.value;
    fetch(`/thongtinsudung/muon/search?query=${searchValue}`)
      .then((response) => response.json())
      .then((data) => {
        const tbody = document.getElementById("tbl-ttsd");
        let htmlContent = ""; // Tạo một biến để chứa nội dung HTML mới
        data.forEach((ttsd) => {
          htmlContent += `
              <tr>
                  <td class="fw-semibold fs-sm">${ttsd.maTT}</td>
                  <td class="fs-sm">${ttsd.thietBi.maTB}</td>
                  <td class="fs-sm">${ttsd.thietBi.tenTB}</td>
                  <td class="fs-sm">${ttsd.tgMuon}</td>
                  <td class="fs-sm">${ttsd.tgTra}</td>
                  <td class="fs-sm">${ttsd.tgDatCho}</td>
                  <td class="fs-sm">
                      ${
                        ttsd.tgDatCho != null
                          ? "Đang đặt chỗ"
                          : ttsd.tgMuon != null && ttsd.tgTra == null
                          ? "Đang mượn"
                          : "Đã trả"
                      }
                  </td>
              </tr>`;
        });
        tbody.innerHTML = htmlContent; // Cập nhật nội dung HTML một lần
      })
      .catch((error) => {
        console.error(error);
      });
  });
  btnMuon.addEventListener("click", function () {
    if (validate() === false) {
      return;
    }
    Swal.fire({
      title: "Bạn có chắc chắn muốn mượn thiết bị này không?",
      text: "Hành động này không thể hoàn tác!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Mượn",
    }).then((result) => {
        if (result.isConfirmed) {
            const data = {
            maTT: txtMaTT.value,
            thanhVien: {
                maTV: txtMaTV.value,
            },
            thietBi: {
                maTB: txtMaTB.value,
            },
            tgVao : null,
            tgMuon: new Date(),
            tgTra: null,
            tgDatCho: null,
            };
            const url = "/thongtinsudung/muon";
            $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (data) {
                console.log(data);
                if (data == "success") {
                Swal.fire({
                    title: "Mượn thiết bị thành công",
                    icon: "success",
                }).then((result) => {
                    if (result.isConfirmed) {
                    window.location.href = "/thongtinsudung/muon";
                    }
                });
                } else {
                Swal.fire({
                    title: "Mượn thiết bị thất bại",
                    icon: "error",
                    text: data,
                });
                }
            },
            error: function (e) {
                Swal.fire({
                title: "Mượn thiết bị thất bại",
                icon: "error",
                });
            },
            });
        }
    });
  });
  function validate() {
    let maTB = txtMaTB.value;
    let maTV = txtMaTV.value;
    if (maTB == "" || maTV == "") {
      Swal.fire({
        title: "Dữ liệu không hợp lệ",
        text: "Mã thiết bị và mã thành viên không được để trống",
        icon: "error",
      });
      return false;
    }
    if (!validateThanhVien(maTV)) {
      return false;
    }
    if (!validateThietBi(maTB)) {
      return false;
    }
  }

  async function validateThanhVien(maTV) {
    try {
      const response = await fetch(`/thanhvien/getbyid?query=${maTV}`);
      const data = await response.json();
      if (data == null || data.hoTen == undefined) {
        Swal.fire({
          title: "Dữ liệu không hợp lệ",
          text: "Thành Viên không tồn tại",
          icon: "error",
        });
        return false;
      }
    } catch (error) {}
  }

  async function validateThietBi(maTB) {
    try {
      const response = await fetch(`/thietbi/getbyid?query=${maTB}`);
      const data = await response.json();
      if (data == null || data.tenTB == undefined) {
        Swal.fire({
          title: "Dữ liệu không hợp lệ",
          text: "Thiết bị không tồn tại",
          icon: "error",
        });
        return false;
      }
    } catch (error) {}
  }
});
