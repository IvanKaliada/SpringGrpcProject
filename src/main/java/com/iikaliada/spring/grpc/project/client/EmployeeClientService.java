package com.iikaliada.spring.grpc.project.client;

import com.grpc.project.stubs.employee.EmployeeRequest;
import com.grpc.project.stubs.employee.EmployeeResponse;
import com.grpc.project.stubs.employee.EmployeeServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeClientService {

    @Value("${grpc.server.host}")
    private String grpcServerHost;

    @Value("${grpc.server.port}")
    private int grpcServerPort;

    public String getEmployeeRequest(int id) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcServerHost, grpcServerPort)
                .usePlaintext()
                .build();

        EmployeeServiceGrpc.EmployeeServiceBlockingStub stub
                = EmployeeServiceGrpc.newBlockingStub(channel);

        EmployeeResponse employeeResponse = stub.getEmployeeDetails(EmployeeRequest.newBuilder()
                .setId(id)
                .build());

        channel.shutdown();

        return employeeResponse.toString();
    }

}
