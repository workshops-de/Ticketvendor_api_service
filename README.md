# Ticketvendor API Service
Ticket Vendor API service. Provides a REST API to search for events and order event tickets.

## Modules
- ticketvendor-api: Defines the REST API for the Ticketvendor API service
  - contains the OpenAPI specification file `ticketvendor-api.yaml`
  - contains all model files corresponding to the OpenAPI specification in `ticketvendor-api/src/main/resources/model`
  - contains the `TicketVendorApi` interface using Spring Framework `@HttpExchange` annotation. 
    With the help of this annotation Http clients can be generated in a declarative way in order to call the backend implementation. 
  
- ticketvendor-service: The Ticketvendor API backend service implementation

## How to run the service
### Prerequisites
- Create a file named `env.properties` inside the `ticketvendor-service` module
- define following properties:
  - `DB_USER=<you-username>`
  - `DB_PASSWORD=<your-password>`

### Starting service from IDE
- use following run configuration in IntelliJ IDEA:
```xml
<component name="ProjectRunConfigurationManager">
  <configuration default="false" name="TicketvendorServiceApplication" type="SpringBootApplicationConfigurationType" factoryName="Spring Boot" nameIsGenerated="true">
    <module name="ticketvendor-service" />
    <option name="SPRING_BOOT_MAIN_CLASS" value="de.workshops.ticketvendorservice.TicketvendorServiceApplication" />
    <option name="WORKING_DIRECTORY" value="file://./ticketvendor-service" />
    <extension name="coverage">
      <pattern>
        <option name="PATTERN" value="de.workshops.ticketvendorservice.*" />
        <option name="ENABLED" value="true" />
      </pattern>
    </extension>
    <method v="2">
      <option name="Make" enabled="true" />
    </method>
  </configuration>
</component>
```
The important part here is the `WORKING_DIRECTORY` property.

### Starting service with Maven
- change in to `ticketvendor-service` module
- run `mvn spring-boot:run`

### Hexagonal Architecture
TBD

### Endpoints
TBD

### Database and initial data
TBD