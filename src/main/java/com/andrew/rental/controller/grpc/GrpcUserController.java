package com.andrew.rental.controller.grpc;

import com.andrew.rental.*;
import com.andrew.rental.AddResponse;
import com.andrew.rental.AddUserRequest;
import com.andrew.rental.AllRequest;
import com.andrew.rental.AllResponse;
import com.andrew.rental.DeleteRequest;
import com.andrew.rental.DeleteResponse;
import com.andrew.rental.GetRequest;
import com.andrew.rental.UserServiceGrpc;
import com.andrew.rental.UsersShort;
import com.andrew.rental.model.User;
import com.andrew.rental.service.UserService;
import io.grpc.stub.StreamObserver;
import javassist.NotFoundException;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@GRpcService
public class GrpcUserController extends UserServiceGrpc.UserServiceImplBase {
    @Autowired
    private UserService userService;

    @Override
    public void all(AllRequest request, StreamObserver<AllResponse> responseObserver) {
        List<User> users = userService.findAll();
        List<UsersShort> convertedUsers = users.stream().
                map(User::toUsersShort).
                collect(Collectors.toList());
        AllResponse response = AllResponse.newBuilder().
                addAllUsers(convertedUsers).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void shortGet(GetRequest request, StreamObserver<UsersShort> responseObserver) throws NotFoundException {
        User user = userService.getUserById(UUID.fromString(request.getId()));
        responseObserver.onNext(user.toUsersShort());
        responseObserver.onCompleted();
    }

    @Override
    public void add(AddUserRequest request, StreamObserver<AddResponse> responseObserver) {
        userService.addUser(User.fromAddRequest(request));
        responseObserver.onNext(AddResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<DeleteResponse> responseObserver) throws NotFoundException {
        userService.deleteUserById(UUID.fromString(request.getId()));
        responseObserver.onNext(DeleteResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
