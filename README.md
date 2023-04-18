# FinancialHouseChallenge

This challenge is a Springboot applicaton which is constructed as a web application.
It basically provides an api to report transactions provided external api. You can use the collection link below to use endpoints simply.

https://api.postman.com/collections/5351010-177ee7ff-1adc-4f64-8516-d73237350a6b?access_key=PMAT-01GYA4C31XXNX2M2D7NTRSP3JZ

In this project Self signed certificate is used, and located in classpath. In production environment using this certificate is not safe.

All api endpoints are designed by using reactive programming. While consuming external api endpoints, WebClient library is used which is the recommend library by Springboot creaters.

And also you can see comparasion of RestTemplate and WebClient.

https://www.baeldung.com/spring-webclient-resttemplate



## PreRequsite

Dockerfile is located under reporting folder. You need to run docker deamon only.

You can get docker from this link https://docs.docker.com/get-docker/
 

# Run the Application

Under reporting folder,
To build image, you should run the command from terminal 

docker build -t reporting . 

To run the the container

docker run -p 8443:8443 reporting


# Improvement

Spring security can be added to safe backend project. 
When external api endpoints return not expected result, Our project returns internal server error.
It can be improved on exceptional cases and add more test cases.

