const inputElements = document.querySelectorAll("input");

inputElements.forEach((el) => {
  el.addEventListener(
    "input",
    function (e) {
      el.classList.remove("is-invalid");
    },
    { once: true }
  );
});
