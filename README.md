# CryptoEncryption



CLASS DESCRIPTION
JobCoinApplication
	Main application, uses the mixer to create addresses and complete transactions.

JobCoinServiceInt
I used an interface here to allow a stub implementation of the JobCoinService. This will remove the dependency on the JobCoin API when testing.
JobCoinServiceImpl
Creates actual calls to JobCoin API to get transaction information and also to send coins from one address to another.
JobCoinServiceStub
This class is used for testing purposes only, we can enable this by changing application.properties spring profiles flag to testing. This class reads a json file exactly like a real call response from JobCoin API. It allows us to more accurately test our code, more importantly it allows us to test our code even if JobCoin API is down.
Mixer
1.	Creates and stores addresses.
2.	Monitors a queue of addresses that needs to be updated, only updates if its not empty.
3.	Transfers funds from deposit address to house account after detection
4.	Doles the funds from house account to addresses belonging to deposit address
addressStore – map that keeps track of lists of addresses that correspond to depositAddresses
DollAddressesRunnable – checks every 10 seconds to see if there are any addresses that need to be distributed.
TransactionMonitor
This class was created to remove some responsibility from the mixer and create modularity/readability. Allows you to add addresses to a watch list where it will be monitored for any transaction changes.

JobCoinController
	Not complete but will be used as an entry point for future implementation
JobCoinApplicationTests
	getBalanceTestSuccess() – test our our application even if JobCoin API is down
	CreateDepositAddressSuccess() – Make sure our addressStore is working properly

TODO

1.	Setup DB, flyway and schema design
2.	Decouple scheduling logic from application logic for threads
3.	Add error handling
4.	Mixer Algorithm design
a.	Randomize the time of distribution
b.	Randomize amount of coin distribution
5.	Testing coverage is low but stub is setup, need to increase test cases
6.	Code organization, single responsibility principle for classes and methods
7.	Come up with ways to lower the number of api calls
8.	Review the requirement of application, number of addresses, fee strategy, scaling, etc
9.	Finally review/adjust design using the requirement
10.	Implement JobCoinController
