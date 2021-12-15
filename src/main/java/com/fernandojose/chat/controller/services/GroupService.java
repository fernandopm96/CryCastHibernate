package com.fernandojose.chat.controller.services;

import com.fernandojose.chat.controller.repositories.GroupRepository;
import com.fernandojose.chat.controller.repositories.UserRepository;
import com.fernandojose.chat.exceptions.GroupCreationException;
import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class GroupService {

    public static void newGroup(String name, List<String> namesUsers) throws GroupCreationException {

        if(loadGroupByName(name) != null){
            throw new GroupCreationException("Ese grupo ya existe. ");
        }
        List<User> users = namesUsers.stream().map(UserRepository::loadUserByName).collect(Collectors.toList());
        Group group = new Group(name, users);
        GroupRepository.newGroup(group);

    }

    public static Group loadGroupByName(String name) {
        return GroupRepository.loadGroupByName(name);
    }

    /*public static List<Group> groupsUser(String name){
        User user = userRepository.loadUserByName(name);
        return repository.groupsUser(user);
    }*/

}
