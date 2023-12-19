package com.example.socialsync.controller;


import com.example.socialsync.dto.request.UserRequestDto;
import com.example.socialsync.dto.response.UserResponseDto;
import com.example.socialsync.exceptions.FriendshipNotFound;
import com.example.socialsync.exceptions.UserAlreadyExists;
import com.example.socialsync.exceptions.UserNameExists;
import com.example.socialsync.exceptions.UserNotFoundException;
import com.example.socialsync.model.Friendship;
import com.example.socialsync.model.User;
import com.example.socialsync.service.FollowService;
import com.example.socialsync.service.FriendshipService;
import com.example.socialsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FriendshipService friendshipService;

    @Autowired
    FollowService followService;

    @PostMapping("/adduser")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){
        try{
            UserResponseDto userResponseDto =userService.addUser(userRequestDto);
            return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        }catch (UserAlreadyExists e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (UserNameExists u){
//            username shd be unique
            return new ResponseEntity<>(u.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{userEmail}")
    public ResponseEntity updateBioandProfilePic(@PathVariable String userEmail, @RequestParam(required = false) String bio, @RequestParam(required = false) String profilePicUrl){
        try{
            UserResponseDto userResponseDto = userService.updateBioandProfilePic(userEmail, bio, profilePicUrl);
            return new ResponseEntity<>(userResponseDto, HttpStatus.ACCEPTED);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sendFriendRequest")
    public ResponseEntity sendFriendRequest(@RequestParam String user1Email,@RequestParam String user2Email){
        try{
            String response = friendshipService.sendFriendRequest(user1Email, user2Email);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/acceptFriendRequest")
    public  ResponseEntity acceptFriendRequest(@RequestParam String friendshipId){
        try{
            friendshipService.acceptFriendRequest(friendshipId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (FriendshipNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/follow")
    public ResponseEntity followUser(@RequestParam String followedUserName , String followerUserName){
        try{
            String response = followService.followUser(followedUserName, followerUserName);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
