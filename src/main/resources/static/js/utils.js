function showMessage(type, message) {

    const box =
        document.getElementById("messageBox");

    box.innerHTML = `
    
        <div class="alert alert-${type} alert-dismissible fade show shadow-sm">

            ${message}

            <button type="button"
                    class="btn-close"
                    data-bs-dismiss="alert">
            </button>

        </div>
    `;
}

// ================= LOGOUT =================

function logout() {

    localStorage.clear();

    window.location.href = "login.html";
}

// ================= AUTH CHECK =================

function checkAuth() {

    const userId =
        localStorage.getItem("userId");

    if (!userId) {

        window.location.href = "login.html";
    }
}

// ================= GET USER =================

function getUserId() {

    return localStorage.getItem("userId");
}

function getUserName() {

    return localStorage.getItem("userName");
}

function getUserEmail() {

    return localStorage.getItem("userEmail");
}