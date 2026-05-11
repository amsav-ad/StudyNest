package studynestfullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import studynestfullstack.dto.LoginRequestDto;
import studynestfullstack.dto.RegisterRequestDto;
import studynestfullstack.dto.UserResponseDto;
import studynestfullstack.response.ApiResponse;
import studynestfullstack.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	// ================= REGISTER =================

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserResponseDto>> register(@Valid @RequestBody RegisterRequestDto dto) {

		UserResponseDto user = userService.register(dto);

		ApiResponse<UserResponseDto> response = new ApiResponse<>(true, "Registration successful", user);

		return ResponseEntity.ok(response);
	}

	// ================= LOGIN =================

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserResponseDto>> login(@Valid @RequestBody LoginRequestDto dto) {

		UserResponseDto user = userService.login(dto);

		ApiResponse<UserResponseDto> response = new ApiResponse<>(true, "Login successful", user);

		return ResponseEntity.ok(response);
	}
}