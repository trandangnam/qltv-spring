
document.addEventListener("DOMContentLoaded", function() {  
    ttsds.forEach(function(ttsd) {
        // Truy xuất thuộc tính maTT của từng đối tượng và log ra console
        console.log(ttsd.tgDatCho);
    });
     var resetButton = document.getElementById("reset-thoiGian");

    // Gán sự kiện cho nút "Đặt lại"
     resetButton.addEventListener("click", function() {
        // Lấy thẻ input thời gian theo id và cập nhật giá trị thành thời gian hiện tại
        var thoiGianInput = document.getElementById("val-thoiGian");
        var now = new Date();
        var formattedNow = now.toISOString().slice(0, 16); // Format: YYYY-MM-DDTHH:MM
        thoiGianInput.value = formattedNow;
     });
     // Function to set default value for the time input field
     function setDefaultTime() {
         var now = new Date();
         var formattedNow = now.toISOString().slice(0, 16); // Format: YYYY-MM-DDTHH:MM
         document.getElementById("val-thoiGian").value = formattedNow;
     }
     function xuLyThoiGianThayDoi() {
        var inputThoiGian = document.getElementById('val-thoiGian');
    
        inputThoiGian.addEventListener('input', function() {
            var thoiGianNhap = new Date(inputThoiGian.value);
            var thoiGianHienTai = new Date();
            
            // Tính toán thời gian tương lai
            var thoiGianToi = new Date(thoiGianHienTai);
            thoiGianToi.setFullYear(thoiGianHienTai.getFullYear() + 1);
    
            // Kiểm tra năm
            if (thoiGianNhap.getFullYear() < thoiGianHienTai.getFullYear()) {
                Swal.fire({
                    title: "Thời gian không hợp lệ",
                    text: "Thời gian không được nhỏ hơn hiện tại",
                    icon: "error",
                  })
                inputThoiGian.value = thoiGianHienTai.toISOString().slice(0,16);
                return;
            } else if (thoiGianNhap.getFullYear() === thoiGianHienTai.getFullYear()) {
                // Kiểm tra tháng
                if (thoiGianNhap.getMonth() < thoiGianHienTai.getMonth()) {
                    Swal.fire({
                        title: "Thời gian không hợp lệ",
                        text: "Thời gian không được nhỏ hơn hiện tại",
                        icon: "error",
                      })
                    inputThoiGian.value = thoiGianHienTai.toISOString().slice(0,16);
                    return;
                } else if (thoiGianNhap.getMonth() === thoiGianHienTai.getMonth()) {
                    // Kiểm tra ngày
                    if (thoiGianNhap.getDate() < thoiGianHienTai.getDate()) {
                        Swal.fire({
                            title: "Thời gian không hợp lệ",
                            text: "Thời gian không được nhỏ hơn hiện tại",
                            icon: "error",
                          })
                        inputThoiGian.value = thoiGianHienTai.toISOString().slice(0,16);
                        return;
                    } else if (thoiGianNhap.getDate() === thoiGianHienTai.getDate()) {
                        // Kiểm tra giờ
                        if (thoiGianNhap.getHours() < thoiGianHienTai.getHours()) {
                            Swal.fire({
                                title: "Thời gian không hợp lệ",
                                text: "Thời gian không được nhỏ hơn hiện tại",
                                icon: "error",
                              })
                            inputThoiGian.value = thoiGianHienTai.toISOString().slice(0,16);
                            return;
                        } else if (thoiGianNhap.getHours() === thoiGianHienTai.getHours()) {
                            // Kiểm tra phút
                            if (thoiGianNhap.getMinutes() < thoiGianHienTai.getMinutes()) {
                                Swal.fire({
                                    title: "Thời gian không hợp lệ",
                                    text: "Thời gian không được nhỏ hơn hiện tại",
                                    icon: "error",
                                  })
                                inputThoiGian.value = thoiGianHienTai.toISOString().slice(0,16);
                                return;
                            }
                        }
                    }
                }
            } else if (thoiGianNhap > thoiGianToi) {
                Swal.fire({
                    title: "Thời gian không hợp lệ",
                    text: "Thời gian không được vượt quá 1 năm",
                    icon: "error",
                  })
                inputThoiGian.value = thoiGianToi.toISOString().slice(0,16);
                return;
            }
    
            // Kiểm tra danh sách ttsds
            if (typeof ttsds !== 'undefined' && ttsds.length > 0) {
                ttsds.forEach(function(ttsd) {
                    var tgDatCho = new Date(ttsd.tgDatCho);
                    if (thoiGianNhap.getFullYear() === tgDatCho.getFullYear() && thoiGianNhap.getMonth() === tgDatCho.getMonth() && thoiGianNhap.getDate() === tgDatCho.getDate()) {
                        Swal.fire({
                            title: "Thời gian không hợp lệ",
                            text: "Thời gian đã được đặt bởi người khác",
                            icon: "error",
                          })
                        inputThoiGian.value = thoiGianHienTai.toISOString().slice(0,16);
                        return;
                    }           
                });
            }
        });
    }
    
     // Call the function when the DOM content is fully loaded
     setDefaultTime();
     xuLyThoiGianThayDoi();
 });
 