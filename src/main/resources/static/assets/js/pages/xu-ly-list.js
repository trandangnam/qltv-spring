document.addEventListener("DOMContentLoaded", function (event) {
  const arrbtnXoaXuLy = document.querySelectorAll(".btn-xoaXuLy");

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

// Pagination
const paginationNav = document.querySelector(".pagination-container");
const curPage = Number.parseInt(paginationNav.dataset.pageNo);
const totalPages = Number.parseInt(paginationNav.dataset.totalPages);
const query = paginationNav.dataset.query;
const urlArgs = { query };
const pagination = new Pagination("/xuly/search", urlArgs, curPage, totalPages);
