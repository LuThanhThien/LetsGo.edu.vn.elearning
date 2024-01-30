package vn.letsgo.elearning.service.user;

import vn.letsgo.elearning.dto.user.UserDto;
import vn.letsgo.elearning.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService extends UserDetailsService {

    User save(User user);

    long count();

    User loadUserByUsername(String username) throws UsernameNotFoundException;

    User findByEmail(String userEmail) throws UsernameNotFoundException;

    User findByUsername(String username) throws UsernameNotFoundException;

    void uploadAvatar(MultipartFile avatar, String email);

    void update(UserDto userDto, String userEmail);

    boolean checkExistPassword(String password, String userEmail);

    void changePassword(String password, String userEmail);

    List<UserDto> findAll();

    User findById(long l);
}
