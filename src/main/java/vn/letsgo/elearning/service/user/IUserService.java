package vn.letsgo.elearning.service.user;

import vn.letsgo.elearning.dto.user.UserDto;
import vn.letsgo.elearning.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import vn.letsgo.elearning.exception.user.UsernameNotFoundCustomException;

import java.util.List;

public interface IUserService extends UserDetailsService {

    User save(User user);

    long count();

    User loadUserByUsername(String username) throws UsernameNotFoundCustomException;

    User findByEmail(String userEmail) throws UsernameNotFoundCustomException;

    User findByUsername(String username) throws UsernameNotFoundCustomException;

    void uploadAvatar(MultipartFile avatar, String email);

    void update(UserDto userDto, String userEmail);

    boolean checkExistPassword(String password, String userEmail);

    void changePassword(String password, String userEmail);

    List<User> findAll();

    User findById(long l);
}
