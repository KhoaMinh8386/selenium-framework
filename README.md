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
