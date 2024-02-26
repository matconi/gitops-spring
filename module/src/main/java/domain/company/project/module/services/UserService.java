package domain.company.project.module.services;

import domain.company.project.module.domain.entities.User;
import domain.company.project.module.dto.request.UserRequest;
import domain.company.project.module.dto.response.UserResponse;
import domain.company.project.module.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());

        return response;
    }

    public List<UserResponse> listUsers() {
        List<User> useres = userRepository.findAll();
        return useres.stream()
                .map(this::convertUserTo)
                .toList();
    }

    private UserResponse convertUserTo(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(response.getEmail());

        return response;
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User não encontrado com id" + id +" !"));
    }

    public UserResponse findUserById(Long id){
        User user = this.findById(id);
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(response.getEmail());
        return response;
    }

    public UserResponse updateUser(UserRequest request, Long id) {
        User user = this.findById(id);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());

        return response;
    }

    public void deleteUser(Long id){
        User user = this.findById(id);
        userRepository.delete(user);
    }
}
