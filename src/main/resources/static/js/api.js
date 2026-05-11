const API = {

    // ================= AUTH =================

    register:
        "/user/register",

    login:
        "/user/login",

    // ================= STUDENT =================

    saveStudent: (userId) =>
        `/student/save/${userId}`,

    getStudents: (userId) =>
        `/student/all/${userId}`,

    deleteStudent: (studentId) =>
        `/student/delete/${studentId}`,

    getStudentCount: (userId) =>
        `/student/count/${userId}`,

    searchStudents: (userId, keyword) =>
        `/student/search/${userId}?keyword=${keyword}`,

    sortStudentsAsc: (userId) =>
        `/student/sort/name-asc/${userId}`,

    sortStudentsDesc: (userId) =>
        `/student/sort/name-desc/${userId}`,

    latestStudents: (userId) =>
        `/student/latest/${userId}`,

    getStudentById: (studentId) =>
        `/student/${studentId}`,

    updateStudent: (studentId) =>
        `/student/update/${studentId}`
};