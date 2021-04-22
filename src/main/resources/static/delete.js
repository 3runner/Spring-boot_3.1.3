$(document).ready(function () {
    $("#deleteBtn").on('click', function (event) {

        event.preventDefault();

        const $modal = $("#deleteModal")
        fetch("http://localhost:8081/admin/" + $modal.find("#idDelete").val(), {
            method: "DELETE",
            headers: {
                "Content-type": "application/json"
            }
        }).then(function () {
            $('#deleteModal').modal('hide');
            $(".modal-backdrop").hide();
            show();
            console.log("delete modal is closed");
        })
    })
});