# Pact Consumer Driven Contract Testing With Spring Boot

**Docker Setup**
* Run `docker-compose up` command to get a running Pact Broker and a clean Postgres database.

**Pact Broker Usage**

In order of;

_Consumer_
* Execute `mvn -Dtest=PactForDeliveryConsumer test` for create pact contract json
* Execute `mvn pact:publish` for publish to pact broker

_Provider_
* Execute `mvn spring-boot:run` to get provider-api application up
* Execute `mvn pact:verify` for pact verify

and all after visit to pact broker url. (exmp: localhost:80)

_Recommended Article:_
* https://docs.pact.io
* https://martinfowler.com/articles/consumerDrivenContracts.html
* https://github.com/DiUS/pact-jvm
