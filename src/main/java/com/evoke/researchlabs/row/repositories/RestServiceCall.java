package com.evoke.researchlabs.row.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.evoke.researchlabs.row.domain.Employee;

import java.util.List;

/**
 * 
 * RestFulClient implements the functionality like a postman client, it uses rest template object which
 * going to consume the service on respective URI and return the object in the form of string
 * @author P A VISHAL
 * @version 1.0
 * 
 */
@Component
@PropertySource(value = {"classpath:application.properties"})
public class RestServiceCall {
	@Autowired
	private Environment env;

	private static final Logger logger = LoggerFactory.getLogger(RestServiceCall.class);

	private String postUrl;
	
	 @Autowired
	    private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	/**
	 * function for consuming the employee services
	 * @param employeeBean
	 * @return
	 */
	public Employee postEntity(Employee employeeBean) {
		logger.info("Begin /POST request!");
		postUrl = env.getProperty("service.url1");
		Employee emp = null;
		try {
			 emp = restTemplate.postForObject(postUrl, employeeBean, Employee.class);
			 System.out.println("emp value:::::::::::::::::::::::::"+emp);
			logger.info("Response for Post Request: " + emp);
		} catch (Exception e) {
			emp = null;
			logger.error("Exception while consuming rest service" + e);
		}
		return emp;
	}
	
	/*public Employee getForObject(Employee employeeBean){
		logger.info("Begin /POST request!");
		postUrl = env.getProperty("service.url2");
		Employee employee=restTemplate.getForObject(postUrl,Employee.class);
		logger.info("Response for Post Request: " + employee);
		return employee;
	}*/
	
	public Object listAllUsers(){
        postUrl = env.getProperty("service.url2");
        RestTemplate restTemplate = new RestTemplate();
        Object usersMap = restTemplate.getForObject(postUrl, List.class);
       /* for(Employee emp:usersMap){
        
        System.out.println("emp username:::::::::::::::::::::::::::::::::::::::::"+emp.ide());
        
        }*/
       /* int i=0;
        for(Employee emp: usersMap){
        	i+=1;
        	emp.id=i;
        }*/
        return usersMap;
        }   
	
	public void deleteEmployeeById(Integer id){
		 postUrl = env.getProperty("service.url4");
	        RestTemplate restTemplate = new RestTemplate();
	        Object usersMap = restTemplate.getForObject(postUrl+id, Employee.class);
		
	}
	
	@Cacheable(value="empcache", key ="#id")
	public Employee getEmployeeById(Integer id){
		slowQuery(2000L);
		System.out.println("findByDirector is running...");
		 postUrl = env.getProperty("service.url3");
		 RestTemplate restTemplate = new RestTemplate();
		 Employee emp = restTemplate.getForObject(postUrl+id, Employee.class);
		 System.out.println(postUrl+id+"::::::::::::::::::::::::::::::::::::");
		 return emp;
	}
	  private void slowQuery(long seconds){
		    try {
	                Thread.sleep(seconds);
	            } catch (InterruptedException e) {
	                throw new IllegalStateException(e);
	            }
		}
	   
}


