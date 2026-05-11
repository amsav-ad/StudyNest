package studynestfullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import studynestfullstack.dto.StudentRequestDto;
import studynestfullstack.dto.StudentResponseDto;
import studynestfullstack.response.ApiResponse;
import studynestfullstack.service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// ================= SAVE =================

	@PostMapping("/save/{userId}")
	public ResponseEntity<ApiResponse<StudentResponseDto>> saveStudent(@Valid @RequestBody StudentRequestDto dto,
			@PathVariable Integer userId) {

		StudentResponseDto student = studentService.saveStudent(dto, userId);

		ApiResponse<StudentResponseDto> response = new ApiResponse<>(true, "Student saved successfully", student);

		return ResponseEntity.ok(response);
	}

	// ================= GET ALL =================

	@GetMapping("/all/{userId}")
	public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudents(@PathVariable Integer userId) {

		List<StudentResponseDto> students = studentService.getAllStudents(userId);

		ApiResponse<List<StudentResponseDto>> response = new ApiResponse<>(true, "Students fetched successfully",
				students);

		return ResponseEntity.ok(response);
	}

	// ================= GET BY ID =================

	@GetMapping("/{studentId}")
	public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentById(@PathVariable Integer studentId) {

		StudentResponseDto student = studentService.getStudentById(studentId);

		ApiResponse<StudentResponseDto> response = new ApiResponse<>(true, "Student fetched successfully", student);

		return ResponseEntity.ok(response);
	}

	// ================= DELETE =================

	@DeleteMapping("/delete/{studentId}")
	public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable Integer studentId) {

		String result = studentService.deleteStudent(studentId);

		ApiResponse<String> response = new ApiResponse<>(true, result, null);

		return ResponseEntity.ok(response);
	}

	// ================= SEARCH =================

	@GetMapping("/search/{userId}")
	public ResponseEntity<ApiResponse<List<StudentResponseDto>>> searchStudents(@PathVariable Integer userId,
			@RequestParam String keyword) {

		List<StudentResponseDto> students = studentService.searchStudents(userId, keyword);

		ApiResponse<List<StudentResponseDto>> response = new ApiResponse<>(true, "Search completed", students);

		return ResponseEntity.ok(response);
	}

	// ================= COUNT =================

	@GetMapping("/count/{userId}")
	public ResponseEntity<ApiResponse<Long>> getStudentCount(@PathVariable Integer userId) {

		long count = studentService.getStudentCount(userId);

		ApiResponse<Long> response = new ApiResponse<>(true, "Student count fetched", count);

		return ResponseEntity.ok(response);
	}

	// ================= SORT NAME ASC =================

	@GetMapping("/sort/name-asc/{userId}")
	public ResponseEntity<ApiResponse<List<StudentResponseDto>>> sortByNameAsc(@PathVariable Integer userId) {

		List<StudentResponseDto> students = studentService.sortByNameAsc(userId);

		ApiResponse<List<StudentResponseDto>> response = new ApiResponse<>(true, "Students sorted ascending", students);

		return ResponseEntity.ok(response);
	}

	// ================= SORT NAME DESC =================

	@GetMapping("/sort/name-desc/{userId}")
	public ResponseEntity<ApiResponse<List<StudentResponseDto>>> sortByNameDesc(@PathVariable Integer userId) {

		List<StudentResponseDto> students = studentService.sortByNameDesc(userId);

		ApiResponse<List<StudentResponseDto>> response = new ApiResponse<>(true, "Students sorted descending",
				students);

		return ResponseEntity.ok(response);
	}

	// ================= LATEST STUDENTS =================

	@GetMapping("/latest/{userId}")
	public ResponseEntity<ApiResponse<List<StudentResponseDto>>> latestStudents(@PathVariable Integer userId) {

		List<StudentResponseDto> students = studentService.latestStudents(userId);

		ApiResponse<List<StudentResponseDto>> response = new ApiResponse<>(true, "Latest students fetched", students);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/update/{studentId}")
	public ResponseEntity<ApiResponse<StudentResponseDto>> updateStudent(@Valid @RequestBody StudentRequestDto dto,
			@PathVariable Integer studentId) {

		StudentResponseDto student = studentService.updateStudent(studentId, dto);

		ApiResponse<StudentResponseDto> response = new ApiResponse<>(true, "Student updated successfully", student);

		return ResponseEntity.ok(response);
	}
}