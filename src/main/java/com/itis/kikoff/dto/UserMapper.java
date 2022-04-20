//package com.itis.kikoff.dto;
//
//import org.apache.catalina.User;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class UserMapper {
//
//    public UserDtoOut toUserDtoOut(User user) {
//        UserDtoOut userDtoOut = new UserDtoOut();
//        userDtoOut.setName(user.getName());
//        return userDtoOut;
//    }
//
//    public List<UserDtoOut> toUserDtoList(List<User> users) {
//        return users
//                .stream()
//                .map(this::toUserDtoOut)
//                .collect(Collectors.toList());
//    }
//}
