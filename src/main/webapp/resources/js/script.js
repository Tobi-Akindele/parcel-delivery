// DatePicker
$(document).ready(function () {
    var start = new Date();
    start.setFullYear(start.getFullYear() - 100);
    var end = new Date();
    end.setFullYear(end.getFullYear() - 1);
    $(".datepicker").datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: start.getFullYear() + ':' + end.getFullYear(),
        beforeShow: function () {
            setTimeout(function () {
                $('.ui-datepicker').css('z-index', 99999999999999);
            }, 0);
        }
    });
});

//Password Validation
$(document).ready(function () {
    var passwordInput = document.getElementById('userSignUpForm:password');
    var cPasswordInput = document.getElementById('userSignUpForm:confirmPassword');
    var letter = document.getElementById('letter');
    var capital = document.getElementById('capital');
    var number = document.getElementById('number');
    var length = document.getElementById('length');
    var passwordMatch = document.getElementById('passwordMatch');

    // When the user clicks on the password field, show the message box
    passwordInput.onfocus = function () {
        document.getElementById('message').style.display = 'block';
    };
    cPasswordInput.onfocus = function () {
        document.getElementById('message').style.display = 'block';
    };

    // When the user clicks outside of the password field, hide the message box
    passwordInput.onblur = function () {
        document.getElementById('message').style.display = 'none';
    };
    cPasswordInput.onblur = function () {
        document.getElementById('message').style.display = 'none';
    };

    // When the user starts to type something inside the password field
    passwordInput.onkeyup = function () {
        // Validate lowercase letters
        var lowerCaseLetters = /[a-z]/g;
        if (passwordInput.value.match(lowerCaseLetters)) {
            letter.classList.remove("invalid");
            letter.classList.add("valid");
        } else {
            letter.classList.remove("valid");
            letter.classList.add("invalid");
        }

        // Validate capital letters
        var upperCaseLetters = /[A-Z]/g;
        if (passwordInput.value.match(upperCaseLetters)) {
            capital.classList.remove("invalid");
            capital.classList.add("valid");
        } else {
            capital.classList.remove("valid");
            capital.classList.add("invalid");
        }

        // Validate numbers
        var numbers = /[0-9]/g;
        if (passwordInput.value.match(numbers)) {
            number.classList.remove("invalid");
            number.classList.add("valid");
        } else {
            number.classList.remove("valid");
            number.classList.add("invalid");
        }

        // Validate length
        if (passwordInput.value.length >= 8) {
            length.classList.remove("invalid");
            length.classList.add("valid");
        } else {
            length.classList.remove("valid");
            length.classList.add("invalid");
        }

        //Check password match
        if (passwordInput.value && cPasswordInput.value) {
            if (passwordInput.value === cPasswordInput.value) {
                passwordMatch.classList.remove("invalid");
                passwordMatch.classList.add("valid");
            } else {
                passwordMatch.classList.remove("valid");
                passwordMatch.classList.add("invalid");
            }
        }
    };

    cPasswordInput.onkeyup = function () {
        //Check password match
        if (passwordInput.value && cPasswordInput.value) {
            if (passwordInput.value === cPasswordInput.value) {
                passwordMatch.classList.remove("invalid");
                passwordMatch.classList.add("valid");
            } else {
                passwordMatch.classList.remove("valid");
                passwordMatch.classList.add("invalid");
            }
        }
    };
}); 

function dataTableSelectOneRadio(radio) {
    var radioId = radio.name.substring(radio.name.lastIndexOf(':'));
    console.log(radioId);
    console.log(radio);
    for (var i = 0; i < radio.form.elements.length; i++) {
        var element = radio.form.elements[i];
        
        if (element.name.substring(element.name.lastIndexOf(':')) === radioId) {
            element.checked = false;
        }
    }
    radio.checked = true;
}