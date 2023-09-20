# fileStoreRepo
File Store and list with Rest API s


this is a Spring boot applicaiton 
H2 runtime database implemented 
Hibernate technology used for bind relational database tables to spring boot objects
JPA used for CRUD operations easier
Can upload , list , get and delete files 


Upload file example Curl url is -> 

curl --location 'localhost:8080/uploadFile' \
--form 'file=@"/C:/Users/user/Desktop/Tasks/VSCOde/deneme.txt"'
