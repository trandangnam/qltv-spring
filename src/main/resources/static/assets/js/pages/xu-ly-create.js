const hinhThucXuLy = document.getElementById("hinhThucXuLy");
const tienPhat = document.getElementById("tienPhat");

hinhThucXuLy.addEventListener("change", function () {
  if (hinhThucXuLy.value.includes("oiThuong")) {
    tienPhat.disabled = false;
  } else {
    tienPhat.value = "";
    tienPhat.disabled = true;
  }
});
hinhThucXuLy.dispatchEvent(new Event("change"));

