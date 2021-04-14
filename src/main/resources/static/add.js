const addUserForm = document.querySelector("#newUserForm");
const newUser = document.getElementById("newLogin");
const password = document.getElementById("newPassword");
const roles = document.getElementById("newRoles");

function rolesSelected(roles) {
    let id;
    let resultRoles = [];

    if (roles.value === 'Admin') {
        id = 1;
    }
    if (roles.value === 'User') {
        id = 2;
    }
    if (roles.value.length === 2) {
        resultRoles = [{'id': 1, 'name': 'Admin', 'authority': 'Admin'},
            {'id': 2, 'name': 'User', 'authority': 'User'}];
    } else {
        resultRoles = [{'id': id, 'name': roles.value, 'authority': roles.value}];
    }

    return resultRoles;
}

roles.addEventListener('click', () => {
    rolesSelected(roles);
})

addUserForm.addEventListener('submit', (e) => {
    e.preventDefault();
    fetch("http://localhost:8081/api/rest/new", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            name: newUser.value,
            password: password.value,
            roles: rolesSelected(roles)
        })
    })
        .then(function () {
            $('#tabList li:first-child a').tab('show');
            show();
        })
});