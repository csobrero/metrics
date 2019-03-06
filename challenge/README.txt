HOW to RUN
1. mvn clean install
2. from cmd run metrics-0.0.1-SNAPSHOT.jar 

SWAGGER API:
http://localhost:8080/challenge/swagger-ui.html (swagger-ui)

API calls:
http://localhost:8080/challenge/metrics/register/{metricName} (POST register metricName)
http://localhost:8080/challenge/metrics/add/{metricName}/{value} (POST add values to a metric)
http://localhost:8080/challenge/metrics/summary/{operation}/{metricName} (GET retrieves summary operation[min,max,median,avarage])



Using ArrayList with java8 streams time complexity and capacity are the following:

OPERATION	TIME COMPLEXITY		CAPACITY COMPLEXITY
			access 	search		
MIN 		O(1)	O(n)		O(n)
MAX		O(1)	O(n)		O(n)
AVERAGE		O(1)	O(n)		O(n)
MEDIAN		O(n log n)		O(log n)
