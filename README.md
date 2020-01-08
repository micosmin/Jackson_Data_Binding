# Data Binding
- add Jackson as a Maven dependency

```xml
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.10.0</version>
        <scope>compile</scope>
    </dependency>
</dependencies>

```
- Jackson calls getters and setter methods of the Pojo to populate it with the JSON values

Use ObjectMapper to read values from JSON and create the POJO or write the POJO values and create the JSON

## JSON -> POJO mapping
- Jackson calls the **setter** methods behind the scene

JSON Data: sample.json
```json
{
  "id": 14,
  "firstName": "Mario",
  "lastName": "Rossi",
  "active": true
}
```

POJO - Student.class
```java
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private boolean active;

    public int getId() { return id; }
    public void setId(int id) {this.id = id; }
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public boolean isActive() {return active; }
    public void setActive(boolean active) {this.active = active;}
}
```

Data Binding:
```java

ObjectMapper mapper = new ObjectMapper();
Student myStudent = mapper.readValue(new File("sample.json"), Student.class);

System.out.println(myStudent.getId());
System.out.println(myStudent.getFirstName());
System.out.println(myStudent.getLastName());
System.out.println(myStudent.isActive());

```

- read json data from the sample file
- use Student.class as the POJO to map the values of the JSON to

## POJO -> JSON mapping
- Jackson calls the **setter** methods behind the scene
- use the classes written above

```json
 mapper.writeValue(new File("output.json"), myStudent);
```

For pretty printing enable INDENT: 
``mapper.enable(SerializationFeature.INDENT)``

## Nested Objects
- Create a separate class for the nested data in the JSON

Updated JSON: sample-full.json
```json
{
  "id": 14,
  "firstName": "Mario",
  "lastName": "Rossi",
  "active": true,
  "address": {
    "street": "100 Main St",
    "city": "Philly",
    "state": "Penn",
    "zip": "19103",
    "country": "USA"
  },
  "languages": ["Java","C#","Python","JS"],
  "company": "Acme"
}
```

Create an Address Class
```java
package dataBindingTest;

public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    public Address(){}

    public String getStreet() {return street; }

    public void setStreet(String street) {this.street = street; }

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city; }

    public String getState() { return state;}

    public void setState(String state) { this.state = state;}

    public String getZip() {return zip;}

    public void setZip(String zip) {this.zip = zip; }

    public String getCountry() {return country;  }

    public void setCountry(String country) {this.country = country;}
}
```

Update Student Class: add the following fields with corresponding getters and setters
```java
...
    private Address address;
    private String[] languages;

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
...
```

Read data:

```java

ObjectMapper mapper = new ObjectMapper();
Student myStudent = mapper.readValue(new File("sample-full.json"), Student.class);

//Print address
Address tempAddress = theStudent.getAddress();
System.out.println("Street = " + tempAddress.getStreet());
System.out.println("City = " + tempAddress.getCity());


//Print languages
for(String lang: theStudent.getLanguages()){
    System.out.println(lang);
}
```

## Ignore properties
- Useful when you do not want to bind all the properties of a JSON, or to ensure that the binding does not fail in case the JSON gets expanded

Add the following annotation to the POJO
`@IgnoreProperties(ignoreUnkown = true)`

```java
...
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private int id;
...
```