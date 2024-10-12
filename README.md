# Neflix clone as MovieAppPage

Selenium Test Automation Project
This project contains a suite of automated test cases developed using Selenium and TestNG for a web application. The tests are organized into multiple classes based on the different pages of the application. Each class includes tests for both the UI elements and functional aspects of the respective pages.

Project Structure
1. LoginPageTest
Purpose: To validate the UI elements and functionalities of the Login page.
Test Cases:
Test the Login Page UI:
Check if the website logo is displayed.
Verify the heading text is "Login".
Validate the Username label text is "USERNAME".
Validate the Password label text is "PASSWORD".
Ensure the "Login" button is present.
Test the Login Page Functionalities:
Test login functionality with empty input fields.
Test login functionality with an empty USERNAME.
Test login functionality with an empty PASSWORD.
Test login functionality with invalid credentials (correct username, wrong password).
Test login functionality with valid credentials (Username: "rahul", Password: "rahul@2021").
2. HomePageTest
Purpose: To validate the UI elements and functionalities of the Home page.
Test Cases:
Test the Home Page:
Verify heading texts of each section.
Check if the play button is displayed.
Ensure movies are displayed in the corresponding movie sections.
Test the Contact Us section.
3. HeaderSectionTest
Purpose: To validate the UI elements and functionalities of the Header section.
Test Cases:
Test the Header Section UI:
Verify if the website logo is displayed.
Validate the Navbar elements.
Test the Header Section Functionalities:
Test navigation to the Home and Popular pages through header elements.
Test navigation to the account page through header elements.
4. PopularPageTest
Purpose: To validate the UI elements of the Popular page.
Test Cases:
Test the Popular Page UI:
Verify that movies are displayed.
5. SearchPageTest
Purpose: To validate the search functionality of the application.
Test Cases:
Test the Search Functionality:
Search with movie names and verify the count of movies displayed.
Test failure case in search functionality with invalid movie names, triggering error image and text.
6. MovieDetailsPageTest
Purpose: To validate the Movie Details page UI elements.
Test Cases:
Test the Movie Details Page:
Click on any movie from the Home Page and validate all UI elements.
Click on any movie from the Popular Page and validate all UI elements.
7. AccountPageTest
Purpose: To validate the Account page functionalities.
Test Cases:
Test the Account Page:
Validate all UI elements present on the Account page.
Test the Logout functionality.
Running Tests
The test cases are designed to be executed in the specified order and utilize parallel testing wherever possible for efficiency.


## About the Project

This project features a comprehensive suite of automated test cases using Selenium and TestNG to validate a web application's UI and functionalities. It includes tests for various pages such as the Login, Home, Header, Popular, Search, Movie Details, and Account pages, covering both UI elements and functional scenarios. The tests are structured for efficient execution with parallel testing capabilities.

## Technologies Used

List the main technologies or frameworks used in the project. For example:
- Java
- Selenium
- TestNG
- Cucumber

