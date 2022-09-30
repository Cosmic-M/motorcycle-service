# MotoService

<h3>To launch app:</h3>
- pull this project <br>
- create schema motorcycle_service in postgreSQL DB <br>
- configure file applicationi.property and make a connection
- open terminal and run 'mvn clean package' command <br>
- IMPORTANT! follow db/changelog/db.changelog-master.yaml and uncomment scripts which responsible for filling tables with data <br>
- run app <br>
- open http://localhost:8080/swagger-ui/#/ <br>
- now you can invoke GET: /orders/total-amount/{orderId} endpoint (order id is 3) <br>
- according to 'legend' the client agree with the cost, so you can change order status on 'CONFIRMED_SUCCESS' <br>
  (its automatically assign completionOrderData and fetch data into DB) <br>
- change order status on 'PAID' <br>
- now you can invoke GET: /masters/salary/{masterId} (there were two masters working on this order, so id is 1 and 2 respectively) <br>
- wou will obtain their salaries for all orders that has status 'PAID'. Needless to say if its orders includes <br>
  relevant favors, I mean favors with their id and status 'NOT_PAID'. All discounts (according origin task were implemented) <br>

<h3>DataBase structure:</h3>
<img alt="schema.png" height="500" src="schema.png" width="850"/>

<h3>Project description:</h3>
MotorcycleService will be in handy for accounting, saving data concerning clients or their discount, or etcetera... <br>
Its called to automate process of order leading, maintenance and simplify accounting order total amount <br>

MASTER CONTROLLER <br>
POST: /masters/create : just create a new master <br>
PUT: /masters/update : update it if you made a mistake <br>
GET: /masters/order-list/{masterId} : to obtain a list of all master's orders <br>
GET: /masters/salary/{masterId} : you can obtain master's salary by master_id. Mind you, after invoke this <br>
request, all relevant favors for this master will check their status on 'PAID'. You also have to take in <br>
mind, that only orders with status 'PAID' will be considered for this operation. <br>

MOTORCYCLE CONTROLLER <br>
POST: /motorcycles/create : just create a new motorcycle <br>
PUT: /motorcycles/update : update data if it, for example, changed license or etcetera <br>

OWNER CONTROLLER <br>
POST: /owners/create : just create a new owner <br>
PUT: /owners/update : update, if owner changed phone number <br>
GET: /owners/order-list/{id} : obtain a list of orders by owner's id <br>

ORDER CONTROLLER <br>
POST: /orders/create : just create a new order <br>
POST: /orders/add-moto-part : having created an order you can add some moto parts by id <br>
PUT: /orders/update : update it if some original data were changed <br>
PUT: /orders//change-status : you should invoke this endpoint if order's status changed to <br>
'CONFIRMED_SUCCESS' or 'CONFIRMED_FAIL'. It also has sense to switch status on 'PAID' after <br>
client paid his order off. Remember that only orders with status 'PAID' considered on to <br>
calculate master's salary <br>
GET: /orders/total-amount/{orderId} : calculate total amount for order by id. If there was a <br>
diagnostic procedure besides favors claimed to repair, its automatically will be switch on free <br>
'status'. I mean almost free (if client confirmed to repair, procedure of diagnostic costs 1 UAH) <br>

FAVOR CONTROLLER <br>
POST: /favors/create : create a new favor <br>
PUT: /favors/update : update favor procedure <br>
PUT: /favors/change-status : change status. But honestly it isn't sense (after calculating <br>
master's salary it will change its status automatically). So be careful with it! <br>

MOTO-PART CONTROLLER <br>
POST: /moto-parts/create : create a new moto part <br>
PUT: /moto-parts/update : update it if you made a mistake with cost, for example <br>

<h3>In this APP were used such technologies like:</h3>
- org.apache.maven, version 4.0.0<br>
- java, version 17<br>
- org.hibernate<br>
- spring boot<br>
- liquibase<br>
