function loadFile(event) {
    var reader = new FileReader();
    reader.onload = function(){
        var output = document.getElementById('avatar');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
    console.log(event.target.files[0])
}

// JavaScript
const fileInput = document.getElementById('fileInput');
fileInput.addEventListener('change', function (event) {
    var reader = new FileReader();
    reader.onload = function(){
        var output = document.getElementById('avatar');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);

    // Create a FormData object and append the file
    var formData = new FormData();
    formData.append('avatar', event.target.files[0]);

    // Send a PUT request to the API
    axios.put('/api/user/changeAvatar/' + curUser.id, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(function (response) {
        console.log(response);
        toastr.success("Cập nhật ảnh đại diện thành công");
        setTimeout(() => {
            window.location.reload();
        }, 2000);
    }).catch(function (e) {
        console.error(e);
        toastr.error(e.response.data.message);
    });
});

const formUser = document.getElementById("form-user");
const formPassword = document.getElementById("form-password");
const nameValue = document.getElementById("name");
const oldPasswordValue = document.getElementById("old-password");
const newPasswordValue = document.getElementById("new-password");
const rePasswordValue = document.getElementById("confirm-password");

document.addEventListener('DOMContentLoaded', (event) => {


    $('#form-user').validate({
        rules: {
            name: {
                required: true,
                minlength: 3
            },
        },
        messages: {
            name: {
                required: "Tên không được để trống",
                minlength: "Name must be at least 3 characters"
            },
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });


    $('#form-password').validate({
        rules: {
            oldPassword: {
                required: true,
                minlength: 3
            },
            newPassword: {
                required: true,
                minlength: 3
            },
            rePassword: {
                required: true,
                equalTo: "#newPassword"
            }
        },
        messages: {
            oldPassword: {
                required: "Vui lòng nhập mật khẩu cũ",
                minlength: "Mật khẩu phải có ít nhất 3 ký tự"
            },
            newPassword: {
                required: "Vui lòng nhập mật khẩu mới",
                minlength: "Mật khẩu phải có ít nhất 3 ký tự"
            },
            rePassword: {
                required: "Vui lòng nhập lại mật khẩu",
                equalTo: "Mật khẩu không khớp"
            }
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });


    formUser.addEventListener("submit", async (e) => {
        e.preventDefault();

        const data = nameValue.value;
        const id = curUser.id;

        console.log(data);

        try {
            let res = await axios.put("/api/user/changeInfo/" + id +"/" +data);
            toastr.success("Cập nhật thông tin thành công");
            setTimeout(() => {
                window.location.reload();
            }, 2000);
        } catch (e)
        {
            console.error(e);
            toastr.error(e.response.data.message)
        }
    });


    formPassword.addEventListener("submit", async (e) => {
        event.preventDefault();
        if ($('#form-password').valid()) {
            const data = {
                oldPassword: oldPasswordValue.value,
                newPassword: newPasswordValue.value,
                rePassword: rePasswordValue.value
            };

            try {
                let res = await axios.post("/api/user/changePassword", data);
                toastr.success("Cập nhật mật khẩu thành công");
                setTimeout(() => {
                    window.location.reload();
                }, 2000);
            } catch (e)
            {
                console.error(e);
                toastr.error(e.response.data.message)
            }
        }
    });});
