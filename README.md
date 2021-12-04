# WIT challenge

Requirements to run the application:
- Docker compose to download the rabbitMQ image and set up the server
- Java 11

To set the application up and running, just execute the **execute.sh** file with
`sh execute.sh` command, it will start the rabbit on docker, rest and calculator modules.

## Making requests

I suggest using the [Postman](https://www.postman.com/downloads/) app to make requests to the application.

Here's a example of requests that you can make to the app:

`curl --location --request GET 'http://localhost:8080/calculate?numberX=9.5139&numberY=7.6882&operator=sum'`

The endpoint supports only four operations:
*sum*
*subtraction*
*division*
*multiplication*

To get the calculation of other operator, just change de `operator` param in the url to the desired one.

If you type any different operation not mentioned previously, it will return an error message of not supported operation.

The logs of the HTTP requests are stored in the `logs` folder. Each request has a `UNIQUE_ID` in the header response.

Hope you like it!
