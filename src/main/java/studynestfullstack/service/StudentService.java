package studynestfullstack.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studynestfullstack.dto.StudentRequestDto;
import studynestfullstack.dto.StudentResponseDto;
import studynestfullstack.entity.Student;
import studynestfullstack.entity.User;
import studynestfullstack.exception.StudentNotFoundException;
import studynestfullstack.exception.UserNotFoundException;
import studynestfullstack.repository.StudentRepository;
import studynestfullstack.repository.UserRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private UserRepository userRepository;

	// ================= SAVE =================

	public StudentResponseDto saveStudent(StudentRequestDto dto, Integer userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		Student student = new Student();

		student.setName(dto.getName());

		student.setCourse(dto.getCourse());

		student.setEmail(dto.getEmail());

		student.setGender(dto.getGender());

		student.setUser(user);

		Student savedStudent = studentRepository.save(student);

		return mapToResponse(savedStudent);
	}

	// ================= GET ALL =================

	public List<StudentResponseDto> getAllStudents(Integer userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		List<Student> students = studentRepository.findByUser(user);

		return students.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	// ================= GET BY ID =================

	public StudentResponseDto getStudentById(Integer studentId) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found"));

		return mapToResponse(student);
	}

	// ================= DELETE =================

	public String deleteStudent(Integer studentId) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found"));

		studentRepository.delete(student);

		return "Student deleted successfully";
	}

	// ================= SEARCH =================

	public List<StudentResponseDto> searchStudents(Integer userId, String keyword) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		List<Student> students = studentRepository.findByUserAndNameContainingIgnoreCase(user, keyword);

		return students.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	// ================= COUNT =================

	public long getStudentCount(Integer userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		return studentRepository.countByUser(user);
	}

	// ================= SORT NAME ASC =================

	public List<StudentResponseDto> sortByNameAsc(Integer userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		List<Student> students = studentRepository.findByUserOrderByNameAsc(user);

		return students.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	// ================= SORT NAME DESC =================

	public List<StudentResponseDto> sortByNameDesc(Integer userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		List<Student> students = studentRepository.findByUserOrderByNameDesc(user);

		return students.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	// ================= LATEST STUDENTS =================

	public List<StudentResponseDto> latestStudents(Integer userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		List<Student> students = studentRepository.findByUserOrderByIdDesc(user);

		return students.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	// ================= MAPPER =================

	private StudentResponseDto mapToResponse(Student student) {

		StudentResponseDto dto = new StudentResponseDto();

		dto.setId(student.getId());

		dto.setName(student.getName());

		dto.setCourse(student.getCourse());

		dto.setEmail(student.getEmail());

		dto.setGender(student.getGender());

		dto.setUserId(student.getUser().getId());

		return dto;
	}

	public StudentResponseDto updateStudent(Integer studentId, StudentRequestDto dto) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found"));

		student.setName(dto.getName());

		student.setCourse(dto.getCourse());

		student.setEmail(dto.getEmail());

		student.setGender(dto.getGender());

		Student updatedStudent = studentRepository.save(student);

		return mapToResponse(updatedStudent);
	}
}