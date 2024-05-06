document.addEventListener("DOMContentLoaded", function (event) {
  const btnSearch = document.getElementById("btn-search");
  const inputSearch = document.getElementById("input-search");
  const arrbtnTraThietBi = document.querySelectorAll(".btn-TraThietBi");

  btnSearch.addEventListener("click", function () {
    const searchValue = inputSearch.value;
    fetch(`/thongtinsudung/tra/search?query=${searchValue}`)
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
                      <td class="text-center">
                        <a th:href="@{/thongtinsudung/{maTT}/tra(maTT=${ttsd.maTT})}"
                            class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Trả">
                            <i class="fa fa-fw fa-rotate-left"></i>
                        </a>
                     </td>
                  </tr>`;
        });
        tbody.innerHTML = htmlContent; // Cập nhật nội dung HTML một lần
      })
      .catch((error) => {
        console.error(error);
      });
  });

  // Xử lý trả thiết bị
  arrbtnTraThietBi.forEach((btn) => {
    btn.addEventListener("click", function (event) {
      event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a

      Swal.fire({
        title: "Bạn có chắc chắn muốn trả thiết bị này không?",
        text: "Hành động này không thể hoàn tác!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Trả",
      }).then((result) => {
        if (result.isConfirmed) {
            const deleteLink = this.getAttribute("href"); // Lấy đường dẫn xóa từ thuộc tính href của thẻ a
            window.location.href = deleteLink; 
        }
      });
    });
  });
});
