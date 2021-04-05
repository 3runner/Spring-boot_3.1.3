const addUserForm = document.querySelector("#newUserForm");
const newUser = document.getElementById("newLogin");
const password = document.getElementById("newPassword");
const roles = document.getElementById("newRoles");

function rolesSelected(roles) {
    let select1 = roles;
    let selectedArr = [];

    for (let i = 0; i < select1.length; i++) {
        if (select1.options[i].selected) {
            let selectedHsh = {};
            selectedHsh['id'] = +select1.options[i].value;
            selectedHsh['name'] = select1.options[i].id;
            selectedArr.push(selectedHsh);
        }
    }
    return selectedArr;
}

roles.addEventListener('click', () => {
    rolesSelected(roles);
})

addUserForm.addEventListener('submit', (e) => {
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
        .then(res => res.json())
        .then(data => {
            const dataArr = [];
            dataArr.push(data);
        });
})