package vn.letsgo.elearning.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import vn.letsgo.elearning.dto.user.UserDto;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.exception.DataNotFoundException;
import vn.letsgo.elearning.exception.user.UsernameAlreadyExistsException;
import vn.letsgo.elearning.exception.user.UsernameNotFoundCustomException;
import vn.letsgo.elearning.repository.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.letsgo.elearning.utility.UploadFile;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private final IUserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Override
    public User save(User user) {
        try {
            if (userRepository.existsByUsername(user.getUsername())) {
               throw new UsernameAlreadyExistsException(user.getUsername());
            }
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        User.class,
                        "Cannot find user with id: " + id
                        )
                );
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundCustomException {
        return findByUsername(username);
    }



    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundCustomException("User is not found"));
    }

    @Override
    public User  findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundCustomException("User is not found"));
    }

    @Override
    public void uploadAvatar(MultipartFile avatar, String username) {
        User user = userRepository.findByUsername(username).get();
        String currentAvatar = user.getAvatar();
        try {
            user.setAvatar(UploadFile.uploadFile(avatar));
            userRepository.save(user);
            if(currentAvatar != null){
                UploadFile.deleteFile(currentAvatar);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UserDto userDto, String username) {
        User user = userRepository.findByEmail(username).get();
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setBirthDate(userDto.getBirthDate());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
    }

    @Override
    public boolean checkExistPassword(String password, String username) {
        User user = this.findByUsername(username);
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void changePassword(String password, String username) {
        User user = this.findByEmail(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }



}


