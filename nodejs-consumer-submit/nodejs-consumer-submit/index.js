const express = require('express');
const axios = require('axios');

const app = express();

app.get('/api/check-student/:id', async (req, res) => {
    const studentId = req.params.id;

    try {
        const response = await axios.get(
            `http://localhost:8080/api/students/${studentId}`
        );

        res.json({
            message: "Sinh viên hợp lệ!",
            data: response.data
        });

    } catch (error) {

        if (error.response && error.response.status === 404) {
            res.status(404).json({
                message: "Không tìm thấy sinh viên"
            });

        } else {
            res.status(500).json({
                message: "Lỗi kết nối tới Server"
            });
        }
    }
});

app.listen(8082, () => {
    console.log('Node.js Consumer đang chạy ở cổng 8082');
});