class Pagination {
  constructor(pathName, urlArgs, curPage, totalPages) {
    this.container = document.querySelector(".pagination-container");
    this.pg = this.container.querySelector(".list-page");
    this.btnPrevPg = this.container.querySelector("a.prev-page");
    this.btnNextPg = this.container.querySelector("a.next-page");
    this.btnFirstPg = this.container.querySelector("a.first-page");
    this.btnLastPg = this.container.querySelector("a.last-page");

    this.pathName = pathName;
    this.urlArgs = urlArgs;

    this.valuePage = {
      truncate: true,
      curPage,
      numLinksTwoSide: 1,
      totalPages,
    };

    this.setHrefToButtons();
    this.pagination();
  }

  urlArgsToString(pageNo) {
    const urlArgs = {
      ...this.urlArgs,
      pageNo,
    };
    return Object.entries(urlArgs)
      .map((arr) => arr.join("="))
      .join("&");
  }

  setHrefToButtons() {
    this.btnFirstPg.setAttribute(
      "href",
      `${this.pathName}?${this.urlArgsToString(1)}`
    );
    this.btnLastPg.setAttribute(
      "href",
      `${this.pathName}?${this.urlArgsToString(this.valuePage.totalPages)}`
    );
    this.btnPrevPg.setAttribute(
      "href",
      `${this.pathName}?${this.urlArgsToString(
        this.valuePage.curPage === 1 ? 1 : this.valuePage.curPage - 1
      )}`
    );
    this.btnNextPg.setAttribute(
      "href",
      `${this.pathName}?${this.urlArgsToString(
        this.valuePage.curPage === this.valuePage.totalPages
          ? this.valuePage.curPage
          : this.valuePage.curPage + 1
      )}`
    );
  }

  renderPage(index, active = "") {
    let style = "";
    if (index === 1 || index === this.valuePage.totalPages) {
      style = `style="border-radius:0;"`;
    }
    return `<li class="page-item ${active}">
          <a class="page-link" href="${this.pathName}?${this.urlArgsToString(
      index
    )}" ${style ? style : ""} data-page="${index}">${index}</a>
      </li>`;
  }

  handleButtonLeft() {
    if (this.valuePage.curPage === 1 || this.valuePage.totalPages <= 1) {
      this.btnPrevPg.classList.add("disabled");
      this.btnFirstPg.classList.add("disabled");
    } else {
      this.btnPrevPg.classList.remove("disabled");
      this.btnFirstPg.classList.remove("disabled");
    }
  }

  handleButtonRight() {
    if (
      this.valuePage.curPage === this.valuePage.totalPages ||
      this.valuePage.totalPages <= 1
    ) {
      this.btnNextPg.classList.add("disabled");
      this.btnLastPg.classList.add("disabled");
    } else {
      this.btnNextPg.classList.remove("disabled");
      this.btnLastPg.classList.remove("disabled");
    }
  }

  pagination() {
    const {
      totalPages,
      curPage,
      truncate,
      numLinksTwoSide: delta,
    } = this.valuePage;

    const range = delta + 4; // use for handle visible number of links left side

    let render = "";
    let renderTwoSide = "";
    let dot = `<li class="page-item"><a class="page-link" href="javascript:void(0)">...</a></li>`;
    let countTruncate = 0; // use for ellipsis - truncate left side or right side

    // use for truncate two side
    const numberTruncateLeft = curPage - delta;
    const numberTruncateRight = curPage + delta;

    let active = "";
    for (let pos = 1; pos <= totalPages; pos++) {
      active = pos === curPage ? "active" : "";

      // truncate
      if (totalPages >= 2 * range - 1 && truncate) {
        if (
          numberTruncateLeft > 3 &&
          numberTruncateRight < totalPages - 3 + 1
        ) {
          // truncate 2 side
          if (pos >= numberTruncateLeft && pos <= numberTruncateRight) {
            renderTwoSide += this.renderPage(pos, active);
          }
        } else {
          // truncate left side or right side
          if (
            (curPage < range && pos <= range) ||
            (curPage > totalPages - range && pos >= totalPages - range + 1) ||
            pos === totalPages ||
            pos === 1
          ) {
            render += this.renderPage(pos, active);
          } else {
            countTruncate++;
            if (countTruncate === 1) render += dot;
          }
        }
      } else {
        // not truncate
        render += this.renderPage(pos, active);
      }
    }

    if (renderTwoSide) {
      renderTwoSide =
        this.renderPage(1) +
        dot +
        renderTwoSide +
        dot +
        this.renderPage(totalPages);
      this.pg.innerHTML = renderTwoSide;
    } else {
      this.pg.innerHTML = render;
    }

    this.handleButtonLeft();
    this.handleButtonRight();
  }
}
