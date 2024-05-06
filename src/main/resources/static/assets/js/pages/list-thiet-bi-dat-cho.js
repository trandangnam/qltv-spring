// Pagination
const paginationNav = document.querySelector(".pagination-container");
const curPage = Number.parseInt(paginationNav.dataset.pageNo);
const totalPages = Number.parseInt(paginationNav.dataset.totalPages);
const userId = Number.parseInt(paginationNav.dataset.userId);
const query = paginationNav.dataset.query;
const urlArgs = { query };
const pagination = new Pagination(
  `/profile/datcho/${userId}/search`,
  urlArgs,
  curPage,
  totalPages
);
