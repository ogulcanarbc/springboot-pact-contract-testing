# pact-contract-springboot
pact consumer driven contract testing with spring boot


Docker Setup
* Run docker-compose up command to get a running Pact Broker and a clean Postgres database.

Pact Broker Usage
consumer
1. Execute mvn test (create pack json)
2. Execute mvn pact:publish

provider
1 Execute mvn pact:verify
