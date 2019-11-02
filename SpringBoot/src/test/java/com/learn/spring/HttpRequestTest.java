package com.learn.spring;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.spring.domain.EmployeeEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllEmployeeTest() {
        JsonNode jsonNode = this.restTemplate.getForObject("http://localhost:" + port + "/employees", JsonNode.class);
        List<EmployeeEntity> employeeEntityList = jsonNodeToEmployeeList(jsonNode);

        EmployeeEntity employeeOne = employeeEntityList.get(0);
        Assert.assertEquals(employeeOne.getFirstName(), "Kusum");
        Assert.assertEquals(employeeOne.getLastName(), "Negi");
        Assert.assertEquals(employeeOne.getEmail(), "kusum@gmail.com");

        EmployeeEntity employeeTwo = employeeEntityList.get(1);
        Assert.assertEquals(employeeTwo.getFirstName(), "Mukesh");
        Assert.assertEquals(employeeTwo.getLastName(), "Joshi");
        Assert.assertEquals(employeeTwo.getEmail(), "mukesh@gmail.com");
    }

    private List<EmployeeEntity> jsonNodeToEmployeeList(JsonNode listOfEmployee) {
        return mapper.convertValue(listOfEmployee, new TypeReference<List<EmployeeEntity>>() {
        });
    }

    @Test
    public void addDeleteEmployeeTest() {
        EmployeeEntity employeeOne = new EmployeeEntity();
        employeeOne.setFirstName("ToBeRemoved");
        employeeOne.setLastName("ToBeRemoved");
        employeeOne.setEmail("toBeRemoved@gmail.com");

        EmployeeEntity createdEmployee = this.restTemplate.postForObject("http://localhost:" + port + "/employees", employeeOne, EmployeeEntity.class);

        Assert.assertEquals(employeeOne.getFirstName(), createdEmployee.getFirstName());
        Assert.assertEquals(employeeOne.getLastName(), createdEmployee.getLastName());
        Assert.assertEquals(employeeOne.getEmail(), createdEmployee.getEmail());
        Assert.assertNotEquals(employeeOne.getId(), createdEmployee.getId());

        this.restTemplate.delete("http://localhost:" + port + "/employees/" + createdEmployee.getId());

        getAllEmployeeTest();
    }

    @Test
    public void updateEmployeeTest() {
        JsonNode jsonNode = this.restTemplate.getForObject("http://localhost:" + port + "/employees", JsonNode.class);
        List<EmployeeEntity> employeeEntityList = jsonNodeToEmployeeList(jsonNode);

        EmployeeEntity employeeOne = employeeEntityList.get(0);
        employeeOne.setFirstName("UpdatedName");
        employeeOne.setLastName("UpdatedSurName");
        employeeOne.setEmail("updatedEmail@gmail.com");

        this.restTemplate.put("http://localhost:" + port + "/employees", employeeOne);

        jsonNode = this.restTemplate.getForObject("http://localhost:" + port + "/employees", JsonNode.class);
        employeeEntityList = jsonNodeToEmployeeList(jsonNode);

        employeeOne = employeeEntityList.get(0);
        Assert.assertEquals(employeeOne.getFirstName(), "UpdatedName");
        Assert.assertEquals(employeeOne.getLastName(), "UpdatedSurName");
        Assert.assertEquals(employeeOne.getEmail(), "updatedEmail@gmail.com");

        employeeOne.setFirstName("Kusum");
        employeeOne.setLastName("Negi");
        employeeOne.setEmail("kusum@gmail.com");
        this.restTemplate.put("http://localhost:" + port + "/employees", employeeOne);

        getAllEmployeeTest();
    }
}
