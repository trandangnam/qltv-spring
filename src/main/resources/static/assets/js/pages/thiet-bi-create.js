// Function to generate device code based on device name
            function generateDeviceCode(deviceName) {
            // Mapping of device types to their prefixes
            const deviceTypePrefix = {
            'Micro': '1',
            'Máy chiếu': '2',
            'Máy ảnh': '3',
            'Cassette': '4',
            'Tivi': '5',
            'Quạt đứng': '6'
            };

            // Extract device type from the device name
            const deviceType = deviceName.split(' ')[0];

            // Get current year
            const currentYear = new Date().getFullYear().toString().slice(2);

            // Get current quantity (you may fetch this from your backend or maintain it in your frontend logic)
            const currentQuantity = 1;

            // Generate device code
            const deviceTypeCode = deviceTypePrefix[deviceType];
            const quantityCode = currentQuantity.toString().padStart(2, '0');

            return deviceTypeCode + currentYear + quantityCode;
            }

            // Event listener for input change event on device name input field
            document.getElementById('val-tentb').addEventListener('input', function () {
            const deviceName = this.value.trim();
            const deviceCode = generateDeviceCode(deviceName);
            document.getElementById('val-matb').value = deviceCode;
            });

            // Event listener for keypress event on device name input field
            document.getElementById('val-tentb').addEventListener('keypress', function (event) {
            if (event.key === 'Enter') {
            const deviceName = this.value.trim();
            const deviceCode = generateDeviceCode(deviceName);
            document.getElementById('val-matb').value = deviceCode;
            }
            });

            // Event listener for blur event on device name input field
            document.getElementById('val-tentb').addEventListener('blur', function () {
            const deviceName = this.value.trim();
            const deviceCode = generateDeviceCode(deviceName);
            document.getElementById('val-matb').value = deviceCode;
            });