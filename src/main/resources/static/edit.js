$(document).ready(function () {
    $("#editBtn").on('click', function (event) {

        event.preventDefault();

        const $modal = $("#editModal")
        fetch("http://localhost:8081/api/rest/update/" + $modal.find("#idEdit").val(), {
            method: "PATCH",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                id: $modal.find("#idEdit").val(),
                name: $modal.find("#nameEdit").val(),
                password: $modal.find("#passwordEdit").val(),
                roles: rolesSelected($modal.find("#rolesEdit")[0])
            })
        }).then(function() {
            $('#editModal').modal('hide');
            $(".modal-backdrop").hide();
            show();
            console.log("edit modal is closed");
        })
    })
});