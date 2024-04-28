const pathName = window.location.pathname.split("/")[1];
document.querySelector(`a[href='/${pathName}']`).classList.add("active");
