
## Parental Control Service Test:

Contains 2 Maven modules: movie-metadata and parental-control.

## pre-requisites
    Requires following being installed on the system
        Java 8 or above
        Maven 3.x version 

## Build and test

mvn clean install

##  TODO 

1) From the exercise is not clear what to do in the case the movie is not found. so I decided to throw exception further up
2) Using streams is definitely an overkill On ParentalControlLevel for fromCode method, but for presentation I used streams
3) Can apply retry on TechnicalFailureException scenario, but various on SLA requirements.
4) It would be nice to include org.springframework @Service, @Autowired ..., for this test I explicitly kept fewer dependencies only
5) Module packaging left as default (jar) 
