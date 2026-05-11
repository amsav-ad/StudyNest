package studynestfullstack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import studynestfullstack.dto.LoginRequestDto;
import studynestfullstack.dto.RegisterRequestDto;
import studynestfullstack.dto.UserResponseDto;
import studynestfullstack.entity.User;
import studynestfullstack.exception.EmailAlreadyExistsException;
import studynestfullstack.exception.InvalidCredentialsException;
import studynestfullstack.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// ================= REGISTER =================

	public UserResponseDto register(RegisterRequestDto dto) {

		if (userRepository.existsByEmail(dto.getEmail())) {

			throw new EmailAlreadyExistsException("Email already registered");
		}

		User user = new User();

		user.setName(dto.getName());

		user.setEmail(dto.getEmail());

		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		User savedUser = userRepository.save(user);

		UserResponseDto response = new UserResponseDto();

		response.setId(savedUser.getId());

		response.setName(savedUser.getName());

		response.setEmail(savedUser.getEmail());

		return response;
	}

	// ================= LOGIN =================

	public UserResponseDto login(LoginRequestDto dto) {

		Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());

		if (optionalUser.isEmpty()) {

			throw new InvalidCredentialsException("Invalid email");
		}

		User user = optionalUser.get();

		if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {

			throw new InvalidCredentialsException("Invalid password");
		}

		UserResponseDto response = new UserResponseDto();

		response.setId(user.getId());

		response.setName(user.getName());

		response.setEmail(user.getEmail());

		return response;
	}
}