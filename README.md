# Anima Biotech RSA Rest API home assignment - Greg Alelov
Built with Spring Boot

## How to run it
1. Clone the GitHub repository to your local machine
2. Refresh Maven
3. Run class RsaRestapiApplication.java

## API Calls
**To generate a new RSA key pair and get it's ID:**
```
POST:
http://localhost:8080/rsa-instance/
```
**To delete an existing RSA key pair**
```
DELETE:
https://localhost:8080/rsa-instance/delete/{id}
```
**To sign on a given data using the given key**
```
POST:
https://localhost:8080/rsa-instance/sign/{id}

BODY:
a String of the data you want to be signed, e.g: 
"data"
```
**To verify  given data using the given key, data, and signature**
```
POST:
https://localhost:8080/rsa-instance/verify/{id}
BODY:
JSON
{
        "signature": "your_signature_here",
         "data":"your_data_here"
}
```
**List all existing keys**
```
GET: 
https://localhost:8080/rsa-instance/
```




