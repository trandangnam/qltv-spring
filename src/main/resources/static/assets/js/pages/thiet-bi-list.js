// Pagination
const paginationNav = document.querySelector(".pagination-container");
const curPage = Number.parseInt(paginationNav.dataset.pageNo);
const totalPages = Number.parseInt(paginationNav.dataset.totalPages);
const query = paginationNav.dataset.query;
const urlArgs = { query };
const pagination = new Pagination(
  "/thietbi/search",
  urlArgs,
  curPage,
  totalPages
);
