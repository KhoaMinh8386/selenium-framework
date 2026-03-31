# 🚀 Selenium Automation Framework - Lab Assignments

Dự án này là bộ khung kiểm thử tự động (Automation Framework) sử dụng Java, Selenium 4, TestNG và Maven, được phát triển qua các cấp độ từ cơ bản đến nâng cao (CI/CD).

---

### 📊 Trạng thái Hệ thống & Báo cáo Allure
*Người xem có thể click trực tiếp vào các nút bên dưới để kiểm tra:*

[![Test Status](https://github.com/KhoaMinh8386/selenium-framework/actions/workflows/selenium-full.yml/badge.svg)](https://github.com/KhoaMinh8386/selenium-framework/actions/workflows/selenium-full.yml)
[![Allure Report Live](https://img.shields.io/badge/Allure%20Report-View%20Live-brightgreen?style=for-the-badge&logo=github)](https://KhoaMinh8386.github.io/selenium-framework/)

---

### 📂 Hướng dẫn các Nhánh (Branch Navigation)
Dự án được tổ chức theo các nhánh để giảng viên dễ dàng theo dõi tiến độ:

| Tên Nhánh | Nội dung & Mục tiêu | Trạng thái |
| :--- | :--- | :--- |
| **`main`** | **Bản ổn định nhất**: Chứa cấu trúc Framework hoàn chỉnh, tài liệu hướng dẫn và liên kết báo cáo. | ✅ Đã hoàn thành |
| **`lab9`** | **Nâng cao (CI/CD)**: Chứa cấu hình GitHub Actions, Allure Report và các bộ Test Suite tự động chạy trên Cloud. | 
| **`gh-pages`** | **Hosting**: Nhánh này do GitHub Actions tự động tạo ra, chỉ chứa các file tĩnh của báo cáo Allure (người dùng không cần sửa nhánh này). | 

---

### 🛠️ Các tính năng nổi bật trong Framework
1.  **Page Object Model (POM)**: Tổ chức mã nguồn sạch sẽ, tách biệt locator và logic test.
2.  **Allure Report chuyên nghiệp**:
    *   Sử dụng `@Feature`, `@Story`, `@Severity` để quản lý bài test.
    *   Ghi lại từng bước thực thi với `Allure.step()`.
    *   **Tự động chụp ảnh khi lỗi**: Đính kèm Screenshot vào báo cáo ngay khi test thất bại.
3.  **CI/CD Pipeline (GitHub Actions)**:
    *   Tự động chạy test trên cả **Chrome** và **Firefox** sau mỗi lần Push.
    *   Tự động tổng hợp kết quả và đẩy lên trang web báo cáo.

---

### 💻 Cách chạy tại máy cá nhân (Local)

Để chạy thử nghiệm tại máy cá nhân, bạn cần cài đặt **Java 17** và **Maven**.

1.  **Chạy toàn bộ bộ test**:
    ```bash
    mvn clean test
    ```
2.  **Mở báo cáo Allure (Yêu cầu Allure Command Line)**:
    ```bash
    mvn allure:serve
    ```

---




# Selenium Framework - Lab 9 Advanced Allure CI




[![Test Status](https://github.com/KhoaMinh8386/selenium-framework/actions/workflows/selenium-full.yml/badge.svg)](https://github.com/KhoaMinh8386/selenium-framework/actions/workflows/selenium-full.yml)
[![Allure Report](https://img.shields.io/badge/Allure%20Report-View%20Live-brightgreen)](https://KhoaMinh8386.github.io/selenium-framework/)

## Giới thiệu
Dự án Selenium Automation Framework nâng cao với:
*   **Allure Report**: Báo cáo chi tiết, chuyên nghiệp với đầy đủ Annotation `@Feature`, `@Story`, `@Severity`, `@Description`.
*   **Allure Step**: Ghi lại từng bước thực thi trong test method.
*   **Screenshot on Failure**: Tự động chụp ảnh màn hình và đính kèm vào Allure Report khi test thất bại thông qua custom TestNG Listener.
*   **CI/CD Pipeline**: 
    - Chạy tự động mỗi khi push code lên `main` hoặc `lab9`.
    - Chạy định kỳ vào 2AM các ngày trong tuần.
    - Matrix testing trên cả **Chrome** và **Firefox**.
*   **GitHub Pages**: Tự động tổng hợp kết quả và publish báo cáo lên GitHub Pages.

## Cấu trúc dự án
*   `.github/workflows/selenium-full.yml`: Pipeline cấu hình CI/CD.
*   `testng-smoke.xml`: File cấu hình chạy Smoke Test cho CI.
*   `src/test/java/utils/ScreenshotOnFailureListener.java`: Listener xử lý chụp ảnh lỗi.

## Cách chạy Local
1. Clone dự án.
2. Chạy test: `mvn clean test`
3. Xem báo cáo: `mvn allure:serve`
