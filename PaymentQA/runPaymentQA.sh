BasePath=/Users/jetal/Desktop/NewVersion/PaymentQA
BaseReport=/Users/jetal/Desktop/NewVersion/PaymentQA/target/allureresults
cd ${BasePath}
mvn test
allure serve ${BaseReport}