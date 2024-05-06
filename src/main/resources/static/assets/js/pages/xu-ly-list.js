document.addEventListener("DOMContentLoaded", function (event) {
  const btnSearch = document.getElementById("btn-search");
  const inputSearch = document.getElementById("input-search");
  const arrbtnXoaXuLy = document.querySelectorAll(".btn-xoaXuLy");

  btnSearch.addEventListener("click", function () {
    const searchValue = inputSearch.value;
    fetch(`/xuly/search?query=${searchValue}`)
      .then((response) => response.json())
      .then((data) => {
        const tbody = document.getElementById("tbl-xuly");
        let htmlContent = ""; // Tạo một biến để chứa nội dung HTML mới
        data.forEach((xl) => {
          htmlContent += `
                        <tr>
                            <td class="fw-semibold fs-sm">${xl.maXL}</td>
                            <td class="fs-sm">${xl.thanhVien.maTV}</td>
                            <td class="fs-sm">${xl.thanhVien.hoTen}</td>
                            <td class="fs-sm">${xl.hinhThucXL}</td>
                            <td class="fs-sm">${xl.ngayXL}</td>
                            <td class="fs-sm">${
                              xl.trangThaiXL == 1 ? "Đang xử lý" : "Đã xử lý"
                            }</td>
                            <td class="text-center">
                                <div class="btn-group">
                                    <a th:href="@{/xuly/{maXL}/edit(maXL=${
                                      xl.maXL
                                    })}" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Sửa">
                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                    </a>
                                    <a th:href="@{/xuly/{maXL}/delete(maXL=${
                                      xl.maXL
                                    })}" class="btn btn-sm btn-alt-secondary" data-bs-toggle="tooltip" title="Xoá">
                                        <i class="fa fa-fw fa-times"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    `;
        });
        tbody.innerHTML = htmlContent; // Cập nhật nội dung HTML một lần
      })
      .catch((error) => {
        console.error(error);
      });
  });

  // Xử lý xóa xử lý
  arrbtnXoaXuLy.forEach((btn) => {
    btn.addEventListener("click", function (event) {
      event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a

      Swal.fire({
        title: "Bạn có chắc chắn muốn xóa xử lý này không?",
        text: "Hành động này không thể hoàn tác!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Xóa",
        cancelButtonText: "Hủy",
      }).then((result) => {
        if (result.isConfirmed) {
          const deleteLink = this.getAttribute("href"); // Lấy đường dẫn xóa từ thuộc tính href của thẻ a
          window.location.href = deleteLink; // Chuyển hướng trang đến đường dẫn xóa
        }
      });
    });
  });
});
