package org.example.lap6.Controller;

import jakarta.validation.Valid;
import org.example.lap6.Api.ApiResponse;
import org.example.lap6.Model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
   public ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Employee> getEmployee() {
        return employees;
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        employees.add(employee);
        return ResponseEntity.status(200).body("added successfully");
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity updateEmployee(@PathVariable String id, @RequestBody @Valid Employee employee, Errors errors) {
//        if (errors.hasErrors()) {
//            //String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
//        }
//        for (Employee employea : employees)
//            if(employea.getId().equalsIgnoreCase(id)){
//            employees.set(employees.indexOf(employea), employee);
//        return ResponseEntity.status(200).body(new ApiResponse("Update successfully"));
//    }
//        return ResponseEntity.status(400).body(new ApiResponse("Employee not found"));
//}


@DeleteMapping("/delete/{id}")
public ResponseEntity deleteEmployee(@PathVariable String id){
    for (Employee emplo : employees)
        if (emplo.getId().equals(id)){
            employees.remove(emplo);
            return ResponseEntity.status(200).body("Employee delete successfully");

        }
        return  ResponseEntity.status(400).body("Employee not found");
    }

    @GetMapping("/search/{position}")
    public ResponseEntity<List<Employee>> searchEmployee(@PathVariable String position) {
        List<Employee> search = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getPosition().equalsIgnoreCase(position)) {
                search.add(employee);
            }
        }
        return ResponseEntity.status(200).body(search);
    }

    @GetMapping("/agerange/{min}/{max}")
    public ResponseEntity<List<Employee>> getByAgeRange(@PathVariable int min, @PathVariable int max) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() >= min && employee.getAge() <= max) {
                result.add(employee);
            }
        }
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping("/leave/{id}")
    public ResponseEntity<String> applyForLeave(@PathVariable String id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                if (employee.isOnLeave()) return ResponseEntity.status(400).body("Employee is already on leave");
                if (employee.getAnnualLeave() < 1) return ResponseEntity.status(400).body("No annual leave remaining");

                employee.setOnLeave(true);
                employee.setAnnualLeave(employee.getAnnualLeave() - 1);
                return ResponseEntity.status(200).body("Leave applied successfully");
            }
        }
        return ResponseEntity.status(400).body("Employee not found");
    }

    @GetMapping("/noleaveemployee")
    public ResponseEntity<List<Employee>> getEmployeesNoLeave() {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAnnualLeave() == 0) {
                result.add(employee);
            }
        }
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping("/promote/{id}")
    public ResponseEntity<String> promoteEmployee(@PathVariable String id, @RequestBody @Valid String requestId, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        Employee requester = null;
        Employee employeeToPromote = null;

        for (Employee employee : employees) {
            if (employee.getId().equals(requestId)) {
                requester = employee;
            }
            if (employee.getId().equals(id)) {
                employeeToPromote = employee;
            }
        }

        if (requester == null || !requester.getPosition().equals("supervisor"))
            return ResponseEntity.status(400).body("Requester must be a supervisor");

        if (employeeToPromote == null) return ResponseEntity.status(400).body("Employee not found");

        if (employeeToPromote.getAge() < 30) return ResponseEntity.status(400).body("Employee must be at least 30 years old");

        if (employeeToPromote.isOnLeave()) return ResponseEntity.status(400).body("Employee cannot be on leave");

        employeeToPromote.setPosition("supervisor");
        return ResponseEntity.status(200).body("Employee promoted to supervisor");
    }
}