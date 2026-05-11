// ================= REGISTER =================

async function registerUser(event) {

    event.preventDefault();

    const data = {

        name:
            document.getElementById("name").value,

        email:
            document.getElementById("email").value,

        password:
            document.getElementById("password").value
    };

    try {

        const response = await fetch(
            API.register,
            {

                method: "POST",

                headers: {

                    "Content-Type":
                        "application/json"
                },

                body: JSON.stringify(data)
            }
        );

        const result = await response.json();

        if (result.success) {

            showMessage(
                "success",
                result.message
            );

            setTimeout(() => {

                window.location.href =
                    "login.html";

            }, 1500);
        }
        else {

            handleErrors(result);
        }
    }
    catch (error) {

        showMessage(
            "danger",
            "Registration failed"
        );
    }
}

// ================= LOGIN =================

async function loginUser(event) {

    event.preventDefault();

    const data = {

        email:
            document.getElementById("email").value,

        password:
            document.getElementById("password").value
    };

    try {

        const response = await fetch(
            API.login,
            {

                method: "POST",

                headers: {

                    "Content-Type":
                        "application/json"
                },

                body: JSON.stringify(data)
            }
        );

        const result = await response.json();

        if (result.success) {

            localStorage.setItem(
                "userId",
                result.data.id
            );

            localStorage.setItem(
                "userName",
                result.data.name
            );

            localStorage.setItem(
                "userEmail",
                result.data.email
            );

            showMessage(
                "success",
                result.message
            );

            setTimeout(() => {

                window.location.href =
                    "dashboard.html";

            }, 1200);
        }
        else {

            handleErrors(result);
        }
    }
    catch (error) {

        showMessage(
            "danger",
            "Login failed"
        );
    }
}

// ================= HANDLE ERRORS =================

function handleErrors(result) {

    // Validation errors

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

        showMessage("danger", errors);
    }
    else {

        showMessage(
            "danger",
            result.message
        );
    }
}