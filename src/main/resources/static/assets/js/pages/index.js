// Pagination
const paginationNav = document.querySelector(".pagination-container");
const curPage = Number.parseInt(paginationNav.dataset.pageNo);
const totalPages = Number.parseInt(paginationNav.dataset.totalPages);
const query = paginationNav.dataset.query;
const urlArgs = { query };
const pagination = new Pagination("/search", urlArgs, curPage, totalPages);

const btnScrollTo = document.querySelector(".btn--scroll-to");
const section1 = document.getElementById("section--1");

btnScrollTo.addEventListener("click", function () {
  section1.scrollIntoView({ behavior: "smooth" });
});
