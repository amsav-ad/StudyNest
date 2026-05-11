// ================= LOAD DASHBOARD =================

async function loadDashboard() {

    checkAuth();

    const userId =
        getUserId();

    const userName =
        getUserName();

    // ================= USERNAME =================

    document.getElementById("username")
        .innerText = userName;

    // ================= STUDENT COUNT =================

    try {

        const response = await fetch(
            API.getStudentCount(userId)
        );

        const result =
            await response.json();

        if (result.success) {

            document.getElementById(
                "studentCount"
            ).innerText = result.data;
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
            "Unable to load dashboard"
        );
    }
}

// ================= PAGE LOAD =================

window.onload = loadDashboard;