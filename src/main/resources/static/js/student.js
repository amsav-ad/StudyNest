// ================= SAVE / UPDATE =================

async function saveStudent(event) {

    event.preventDefault();

    checkAuth();

    const userId =
        getUserId();

    const params =
        new URLSearchParams(window.location.search);

    const studentId =
        params.get("id");

    const data = {

        name:
            document.getElementById("name").value,

        course:
            document.getElementById("course").value,

        email:
            document.getElementById("email").value,

        gender:
            document.getElementById("gender").value
    };

    try {

        let response;

        // ================= UPDATE =================

        if (studentId) {

            response = await fetch(
                API.updateStudent(studentId),
                {

                    method: "PUT",

                    headers: {

                        "Content-Type":
                            "application/json"
                    },

                    body: JSON.stringify(data)
                }
            );
        }

        // ================= SAVE =================

        else {

            response = await fetch(
                API.saveStudent(userId),
                {

                    method: "POST",

                    headers: {

                        "Content-Type":
                            "application/json"
                    },

                    body: JSON.stringify(data)
                }
            );
        }

        const result =
            await response.json();

        if (result.success) {

            showMessage(
                "success",
                result.message
            );

            setTimeout(() => {

                window.location.href =
                    "allstudents.html";

            }, 1200);
        }
        else {

            handleStudentErrors(result);
        }
    }
    catch (error) {

        showMessage(
            "danger",
            "Operation failed"
        );
    }
}

// ================= LOAD STUDENTS =================

async function loadStudents() {

    checkAuth();

    const userId =
        getUserId();

    try {

        const response = await fetch(
            API.getStudents(userId)
        );

        const result =
            await response.json();

        if (result.success) {

            renderStudents(result.data);
        }
        else {

            showMessage(
                "danger",
                result.message
            );
        }
    }
    catch (error) {

        showMessage(
            "danger",
            "Unable to load students"
        );
    }
}

// ================= RENDER STUDENTS =================

function renderStudents(students) {

    const table =
        document.getElementById(
            "studentTable"
        );

    table.innerHTML = "";

    // Empty State

    if (students.length === 0) {

        table.innerHTML = `
        
            <tr>

                <td colspan="6"
                    class="text-center text-muted py-5">

                    No students found

                </td>

            </tr>
        `;

        return;
    }

    students.forEach(student => {

        table.innerHTML += `
        
            <tr>

                <td>${student.id}</td>

                <td>${student.name}</td>

                <td>${student.course}</td>

                <td>${student.email}</td>

                <td>${student.gender}</td>

                <td>

                    <div class="d-flex gap-2">

                        <a href="addstudent.html?id=${student.id}"
                           class="btn btn-sm btn-primary">

                            Edit

                        </a>

                        <button
                            class="btn btn-danger btn-sm"
                            onclick="deleteStudent(${student.id})">

                            Delete

                        </button>

                    </div>

                </td>

            </tr>
        `;
    });
}

// ================= DELETE =================

async function deleteStudent(studentId) {

    const confirmDelete =
        confirm(
            "Delete this student?"
        );

    if (!confirmDelete) {

        return;
    }

    try {

        const response = await fetch(
            API.deleteStudent(studentId),
            {

                method: "DELETE"
            }
        );

        const result =
            await response.json();

        if (result.success) {

            showMessage(
                "success",
                result.message
            );

            loadStudents();
        }
        else {

            showMessage(
                "danger",
                result.message
            );
        }
    }
    catch (error) {

        showMessage(
            "danger",
            "Unable to delete student"
        );
    }
}

// ================= SEARCH =================

async function searchStudents() {

    const keyword =
        document.getElementById(
            "searchInput"
        ).value;

    const userId =
        getUserId();

    try {

        const response = await fetch(
            API.searchStudents(
                userId,
                keyword
            )
        );

        const result =
            await response.json();

        if (result.success) {

            renderStudents(result.data);
        }
    }
    catch (error) {

        showMessage(
            "danger",
            "Search failed"
        );
    }
}

// ================= SORT ASC =================

async function sortAsc() {

    const userId =
        getUserId();

    const response = await fetch(
        API.sortStudentsAsc(userId)
    );

    const result =
        await response.json();

    renderStudents(result.data);
}

// ================= SORT DESC =================

async function sortDesc() {

    const userId =
        getUserId();

    const response = await fetch(
        API.sortStudentsDesc(userId)
    );

    const result =
        await response.json();

    renderStudents(result.data);
}

// ================= LOAD STUDENT FOR EDIT =================

async function loadStudentForEdit() {

    const params =
        new URLSearchParams(window.location.search);

    const studentId =
        params.get("id");

    // Add Mode

    if (!studentId) {

        return;
    }

    try {

        const response = await fetch(
            API.getStudentById(studentId)
        );

        const result =
            await response.json();

        if (result.success) {

            const student =
                result.data;

            document.getElementById("name")
                .value = student.name;

            document.getElementById("course")
                .value = student.course;

            document.getElementById("email")
                .value = student.email;

            document.getElementById("gender")
                .value = student.gender;

            document.getElementById("formTitle")
                .innerHTML =
                `Update <span>Student</span>`;

            document.getElementById("submitBtn")
                .innerText =
                "Update Student";
        }
    }
    catch (error) {

        showMessage(
            "danger",
            "Unable to load student"
        );
    }
}

// ================= HANDLE ERRORS =================

function handleStudentErrors(result) {

    if (
        typeof result.data === "object"
        &&
        result.data !== null
    ) {

        let errors = "";

        for (let key in result.data) {

            errors += `
            
                <div>
                    ${result.data[key]}
                </div>
            `;
        }

        showMessage(
            "danger",
            errors
        );
    }
    else {

        showMessage(
            "danger",
            result.message
        );
    }
}