![EasyShop_E-commerce (2)](https://github.com/user-attachments/assets/f9bc5831-30d4-430a-878c-47d9b2e0e11e)

## Overview 
### The Easy Shop E-Commerce API is a backend system designed for managing product categories, user profiles, and other functionalities for an e-commerce platform. Built using Spring Boot, this API handles product management, user profiles, and more. The shopping cart and order functionality is planned for future development.

## Completed Phases

### Phase 1: Category Controller
* Implemented controllers for managing categories.
### Features:
* Fetch all categories.
* Filter products by category, price range, or color.
* Create, update, and delete categories (Admin only).

### Phase 2: Bug Fixing
* Addressed identified bugs in the initial setup.
* Ensured proper endpoint functionality.
* Improved stability and performance of controllers and DAOs.

### Phase 4: User Profile
* Implemented functionality for managing user profiles.
### Features:
* Fetch the profile of the currently logged-in user.
* Update profile information.
* Create new user profiles.
* Create the user profile inside Database.

## Technologies Used
* Java: Backend programming language.
* Spring Boot: Framework for building RESTful APIs.
* MySQL: Database for storing user, product, and category data.
* JWT: For secure authentication.
* Maven: Dependency and build management

## API Endpoints
### Category Endpoints
* GET http://localhost:8080/categories NO body
* GET http://localhost:8080/categories/1 NO body
* POST http://localhost:8080/categories Category
* PUT http://localhost:8080/categorids/1 Category
* DELETE http://localhost:8080/categorids/1 NO body

### Product Endpoints
* GET http://localhost:8080/products NO body
* GET http://localhost:8080/products/1 NO body
* POST http://localhost:8080/products Category
* PUT http://localhost:8080/products/1 Category
* DELETE http://localhost:8080/products/1 NO body

### User Profile Endpoints
* GET http://localhost:8080/profile NO body
* PUT http://localhost:8080/profile Profile body

## Screenshoots
<img width="1503" alt="EasyShop_1st" src="https://github.com/user-attachments/assets/88767a08-bbf0-4cd0-a6b6-6e1b0c9a806b" />

![image](https://github.com/user-attachments/assets/028bc8f2-ee33-4b7e-b255-324fede3652a)


![image](https://github.com/user-attachments/assets/7c636928-42cf-4db0-b1fc-496f7811128f)

## Favorite Coode Snippet

``` @PutMapping()
public void update(@RequestBody Profile profile, Principal principal) {
    User user = userDao.getByUserName(principal.getName());
    Profile existingProfile = profileDao.getByUserId(user.getId());

    if (existingProfile == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found.");
    }

    profileDao.update(user.getId(), profile);
} 





