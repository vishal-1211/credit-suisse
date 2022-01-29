In the credit-suisse microservice committed in this project, one can find 2 endpoints.
The purpose of both the endpoints is explained as below : 

1. Post ( save ) call - Saves the data after calculation in HSQLDB
   Endpoint Example : http://localhost:8080/save
   Sample Request Data ( Response Body ) : C:\\Users\\vishaarora\\Desktop\\cslogfile.txt
   
2. Get ( retrieve ) call - Gets the data which is already present in HSQLDB
   Endpoint Example : http://localhost:8080/all
   Sample Request Data : Not Required   
   
The first endpoint, reads the absolute path of the file in which appropriate entries are added and performs the calculation on each of the timestamps ( for a certain id ), and saves the data along with flag in HSQLDB

The second endpoint, reads the existing data present in the HSQLDB and populates the same in response. Following images explain the same : 

POST CALL RESPONSE EXAMPLE :
![image](https://user-images.githubusercontent.com/65604476/151679135-af2e484a-a93e-4b1d-9d57-c25dfbbd2d2f.png)

GET CALL RESPONSE EXAMPLE :
![image](https://user-images.githubusercontent.com/65604476/151679154-8a419e08-c61d-47e8-984c-a5a816b0f50b.png)

Test cases, common exception handler and loggers with aop have been added accordingly
