All the scenarios mentioned in coding challenge are taken care of.
In case of negative number(s) input the application throws customer NegativeNumberNotAllowedExeption.

To test the main program go to the root directory and issue the following maven command
  mvn exec:java -D"exec.mainClass"="service.StringAccumulator"
  
The Junit test can be invoked using following maven command
  mvn test
