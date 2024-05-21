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
        event.preventDefault();
        if (!$('#form-user').valid()) {
            return;
        }
            const data = {
                name: nameValue.value,

            };

            try {
               let res = await axios.post("/api/user/changeInfo", data);
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


