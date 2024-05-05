document.addEventListener("DOMContentLoaded", function (event) {
  const btnSearch = document.getElementById("btn-search");
  const inputSearch = document.getElementById("input-search");
  btnSearch.addEventListener("click", function () {
    const searchValue = inputSearch.value;
    fetch(`/thongtinsudung/search?query=${searchValue}`)
      .then((response) => response.json())
      .then((data) => {
        const tbody = document.getElementById("tbl-ttsd");
        let htmlContent = ""; // Tạo một biến để chứa nội dung HTML mới
        data.forEach((ttsd) => {
          htmlContent += `
          <tr>
              <td class="fw-semibold fs-sm">${ttsd.maTT}</td>
              <td class="fs-sm">${ttsd.thanhVien.maTV}</td>
              <td class="fs-sm">${ttsd.thanhVien.hoTen}</td>
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
});
