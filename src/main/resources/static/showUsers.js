const renderUsers = (users) => {
    console.log(users)
    const $data = $('#data');
    $data.empty();
    if (users.length > 0) {
        users.forEach(u => {
            let tempRoles = ""
            u.authorities.forEach(r => {
                tempRoles += r.name + " "
            })

            const urlEdit = 'api/rest/user/' + u.id;
            const urlDelete = 'api/rest/user/' + u.id;
            const $temp = $('<tr>' +
                '<td>' + u.id + '</td>' +
                '<td>' + u.name + '</td>' +
                '<td>' + tempRoles + '</td>' +
                '<td><a href="' + urlEdit + '" ' +
                'class="btn btn-info eBtn" data-toggle="modal" data-target="#editModal">Edit</a></td>'+
                '<td><a href="' + urlDelete + '" ' +
                'class="btn btn-danger dBtn" data-toggle="modal" data-target="#deleteModal">Delete</a></td>' +
                '</tr>')

            $temp.find(".eBtn").on('click', function (event) {
                console.log("edit button was clicked");
                event.preventDefault();

                $.get(urlEdit, function (user, status){
                    $('#idEdit').val(user.id)
                    $('#nameEdit').val(user.name)
                    $('#passwordEdit').val(user.password)
                    $('#rolesEdit').val(user.roles)
                });

                $('#editModal').modal();
            });

            $temp.find(".dBtn").on('click', function (event) {
                console.log("delete button was clicked");
                event.preventDefault();

                $.get(urlDelete, function (user, status){
                    $('#idDelete').val(user.id)
                    $('#nameDelete').val(user.name)
                    $('#passwordDelete').val(user.password)
                    $('#rolesDelete').val(user.roles)
                });

                $('#deleteModal').modal();
            });

            $data.append($temp)
        });
    }
}

function show() {
// GET запрос и заполнение админ таблицы
    fetch("http://localhost:8081/api/rest/admin")
        .then(res => res.json())
        .then(data => renderUsers(data))
        .catch(error => console.error(error))
}

show()