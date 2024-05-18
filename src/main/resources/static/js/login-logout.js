const logoutBtn = document.getElementById('logout');
logoutBtn.addEventListener('click', () => {
    fetch('/api/auth/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    }).then(response => {
        if (response.ok) {
            location.href = '/';
        }
    });
});

