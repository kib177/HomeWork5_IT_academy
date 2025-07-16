// Клиентская валидация паролей
document.querySelector('form').addEventListener('submit', function (event) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    if (password.length < 1) {
        event.preventDefault();
        alert('Пароль должен содержать не менее 1 символов!');
        return;
    }

    if (password !== confirmPassword) {
        event.preventDefault();
        alert('Пароли не совпадают!');
    }

});

// Установка минимальной и максимальной даты для рождения
const today = new Date().toISOString().split('T')[0];
const minDate = new Date();
minDate.setFullYear(minDate.getFullYear() - 100);
document.getElementById('date_birth').setAttribute('max', today);
document.getElementById('date_birth').setAttribute('min', minDate.toISOString().split('T')[0]);