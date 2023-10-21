package com.iikaliada.spring.grpc.project.service;

import com.grpc.project.stubs.employee.EmployeeRequest;
import com.grpc.project.stubs.employee.EmployeeResponse;
import com.grpc.project.stubs.employee.EmployeeServiceGrpc;
import com.iikaliada.spring.grpc.project.entity.Employee;
import com.iikaliada.spring.grpc.project.repository.EmployeeRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GrpcService
public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void getEmployeeDetails(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
        Optional<Employee> employee = employeeRepository.findById(request.getId());
        if (employee.isPresent()) {
            Employee empl = employee.get();
            EmployeeResponse.Builder builder = EmployeeResponse.newBuilder()
                    .setName(empl.getName())
                    .setUserName(empl.getUserName())
                    .setLastName(empl.getLastName())
                    .setAge(empl.getAge())
                    .setRole(empl.getRole());
            EmployeeResponse employeeResponse = builder.build();
            responseObserver.onNext(employeeResponse);
            responseObserver.onCompleted();
        }
    }

}
