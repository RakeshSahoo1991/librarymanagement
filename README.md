#librarymanagement

Tech Stack :
1. Spring boot
2. Maven
3. Junit
4. Mockito
4. Java (1.8)

please follow below steps to run application :

Step1 : If jar is not available in target folder then run this command to build the jar "mvn clean install"
Step2 : Run this command to build the docker image "docker build -t com.hexad/librarymanagement ."
Step3 : Run this command to run the docker image docker "docker run -p 8080:8080 com.hexad/librarymanagement"

There are 2 different controller LibraryController and UserController.

LibraryController has all the end point related to library management.

curl -d(POST) 'Request Body : Book instance' http://localhost:8080/library/addbook : To add a new book to the library.
curl -v(GET)  http://localhost:8080/library/viewallbooks : To view all the book from library.
curl -d(POST) 'Request Body : Not required' http://localhost:8080/library/borrowbook/{userId}/{bookId} : To borrow book from library.
curl -d(POST) 'Request Body : List<Integer>' http://localhost:8080/library/returnbook/{userId} : To return books to library.

UserController: I have added this to keep the user management separate from Library management .
curl -d 'Request Body : User instance' http://localhost:8080/user/adduser : To add a new trail if needed.

Replace http://localhost:8080 with your server base Url.

**NOTES**
Note1 : I have used static Map as database for this application , Which will be replaced with some database.
Note2 : I have used static block in repository class to initialize data to perform Integration testing.
Note3 : I have written integration testing at controller level and unit test cases at service level.
Note4 : I have used 2 data layers and intermediate mapper to keep DTOs and Data access objects separated.