package edu.mzc.myboard.service;

import edu.mzc.myboard.dao.UserDAO;
import edu.mzc.myboard.dto.UserDTO;

public class UserService {

    private final UserDAO dao = new UserDAO();

    public UserDTO signIn(UserDTO dto) {
        return dao.findById(dto);
    }

    public void signUp(UserDTO dto) {
        dao.insert(dto);
    }
}
