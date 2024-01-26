package letsgo.vn.elearning.service;

import letsgo.vn.elearning.dto.user.UserDto;
import letsgo.vn.elearning.entity.user.User;
import letsgo.vn.elearning.repository.interfaces.IUserRepository;
import letsgo.vn.elearning.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private final IUserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public User findByEmail(String userEmail) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found"));
    }

    @Override
    public void uploadAvatar(MultipartFile avatar, String email) {

    }

    @Override
    public void userUpdate(UserDto userDto, String userEmail) {

    }

    @Override
    public boolean checkExistPassword(String password, String userEmail) {
        return false;
    }

    @Override
    public void changePassword(String password, String userEmail) {

    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }
}


