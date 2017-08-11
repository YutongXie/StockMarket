1. Program name:
   Super Simple Stock Market
   
2. Program usage scope:
   J.P Morgan Java Technical Test
   
3. Requirement understanding:
	for each trade, will include below mandatory info
	1) price
	2) quantity
	3) stock
	
	and this trade will impact stock market such as p/e ratio, dividend yield, volume weighted price, etc.
	I named it as transaction index.

4. How to use:
   a. StockMarketServer.java is program startup. 
      responsible:
      --Initial Spring container
      
   b. EQTradingService.java is trade service for process each transaction.
      Responsible:
      --trade validation
      --record trade
      --calculate transaction index
      
   c.TransIndexCalculationService.java is calculator, responsible for calculating p/e ratio, dividend yield, etc
	
5. Dependence:
   --Spring 4.1.3
   --Mockito: for unit test
   --Junit:   for unit test
   --Apache common logging
   
6. Log:
   use Apache common logging + slf4j.
   
7. Unit test:
   --use Junit + Mockito
   --using Java reflection for private method
   
8. Development environment:
   --Eclipse Neon(4.6.3)
   --JDK 1.8.0_121|64
   
9. How build:
   --Maven